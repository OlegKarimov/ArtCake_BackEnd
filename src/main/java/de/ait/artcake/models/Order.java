package de.ait.artcake.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    public enum State {

        CREATED,
        IN_PROCESS,
        CANT_FINISH,
        FINISHED

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer count = 1;

    @Column(length = 200)
    private String description;

    private Double totalPrice;

    private LocalDate creationDate;

    private LocalDate deadline;

    private Long clientId;

    private Long cakeId;

    private String cakeName;

    private Long confectionerId;

    private Long confectionerIdOtKAZALSJA;

    @Enumerated(value = EnumType.STRING)
    private State state;


    @PrePersist
    public void setCreationDate() {
        this.creationDate = LocalDate.now();
    }

}
