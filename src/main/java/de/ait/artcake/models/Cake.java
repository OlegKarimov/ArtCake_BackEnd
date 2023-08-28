package de.ait.artcake.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cake {

    public enum Category{
        CUPCAKES,
        CHEESECAKES,
        MACARONS,
        MOUSSE
    }

    public enum State{
        CREATED,
        DELETED,
        UPDATED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String ingredients;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private State state;
}
