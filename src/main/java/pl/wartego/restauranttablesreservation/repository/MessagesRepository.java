package pl.wartego.restauranttablesreservation.repository;

import org.springframework.data.repository.CrudRepository;
import pl.wartego.restauranttablesreservation.models.Messages;

public interface MessagesRepository extends CrudRepository<Messages,Long> {
}
