package pl.wartego.restauranttablesreservation.repository;

import org.springframework.data.repository.CrudRepository;
import pl.wartego.restauranttablesreservation.models.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
