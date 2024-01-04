package pl.wartego.restauranttablesreservation.models;

import jakarta.persistence.*;
import lombok.*;
import javax.validation.constraints.Min;

import java.util.List;

@Getter
@Setter
@Entity
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Min(value = 1, message = "Value must be positive")
    private Integer seats;

    @OneToMany(mappedBy = "restaurantTable")
    private List<Reservation> reservations;

}
