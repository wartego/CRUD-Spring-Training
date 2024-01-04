package pl.wartego.restauranttablesreservation.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private RestaurantTable restaurantTable;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startTime;
    private LocalDateTime stopTime;

    @Min(value = 30)
    @Max(value = 120)
    private Integer duration = 15;
    @Min(value = 1)
    private Integer seats = 1;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @PrePersist
    private void prePersist(){
        stopTime = startTime.plusMinutes(duration);
    }

    public LocalDateTime getStopTime() {
        return startTime.plusMinutes(duration);
    }


}
