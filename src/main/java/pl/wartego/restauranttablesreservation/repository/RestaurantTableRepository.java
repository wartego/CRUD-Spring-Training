package pl.wartego.restauranttablesreservation.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.wartego.restauranttablesreservation.models.RestaurantTable;

import java.time.LocalDateTime;
import java.util.List;

public interface RestaurantTableRepository extends CrudRepository<RestaurantTable, Long> {

    @Query("select t from RestaurantTable t " +
            "where t.seats >= :minSeats and " +
            "not exists (select r from t.reservations r " +
            "where :startTime < r.stopTime " +
            "and :stopTime > r.startTime)")
    List<RestaurantTable> getFreeTables(@Param("minSeats") Integer minSeats,
                                               @Param("startTime") LocalDateTime startTime,
                                               @Param("stopTime") LocalDateTime stopTime);

}
