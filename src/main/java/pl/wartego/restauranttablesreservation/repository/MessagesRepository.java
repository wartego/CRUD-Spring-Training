package pl.wartego.restauranttablesreservation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wartego.restauranttablesreservation.models.Messages;

@Repository
public interface MessagesRepository extends CrudRepository<Messages,Long> {
}
