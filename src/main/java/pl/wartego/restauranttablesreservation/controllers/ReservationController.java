package pl.wartego.restauranttablesreservation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wartego.restauranttablesreservation.models.Reservation;
import pl.wartego.restauranttablesreservation.models.RestaurantTable;
import pl.wartego.restauranttablesreservation.repository.ReservationRepository;
import pl.wartego.restauranttablesreservation.repository.RestaurantTableRepository;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private ReservationRepository reservationRepository;
    private RestaurantTableRepository tableRepository;

    public ReservationController(ReservationRepository reservationRepository, RestaurantTableRepository tableRepository){
        this.reservationRepository = reservationRepository;
        this.tableRepository = tableRepository;
    }

    @GetMapping
    public String reservations (Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("reservations", reservationRepository.findAll());
        return "reservations";
    }
    @PostMapping
    public String createReservation (@Valid Reservation reservation ,
                                     Errors errors , Model model ) throws InterruptedException {
        String view;
        if (errors.hasErrors()) {
            model.addAttribute("reservations", reservationRepository.findAll());
            view = "reservations";
        } else {
            int seats = reservation.getSeats();
            LocalDateTime startTime = reservation.getStartTime();
            LocalDateTime stopTime = reservation.getStopTime();
            List<RestaurantTable> availableTables = tableRepository.getFreeTables(seats, startTime, stopTime);
            if (availableTables.size() > 0) {
                reservation.setRestaurantTable(availableTables.get(0));
                reservationRepository.save(reservation);
                view = "redirect:/reservations";
            } else {
                model.addAttribute("noTableAvailable", true);
                model.addAttribute("reservations",
                        reservationRepository.findAll());
                view = "reservations";
            }
        }
        return view;
    }
}
