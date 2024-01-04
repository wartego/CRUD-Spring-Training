package pl.wartego.restauranttablesreservation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wartego.restauranttablesreservation.models.Reservation;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
