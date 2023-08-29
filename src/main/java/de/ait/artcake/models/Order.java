package de.ait.artcake.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"client", "cake"})
@ToString(exclude = {"client", "cake"})
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
    private String clientWishes;

    private Double totalPrice;

    private LocalDate creationDate;

    private LocalDate deadline;

    private Long confectionerId;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @ManyToOne
    @JoinColumn(name = "Client_id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "Cake_id")
    private Cake cake;



    @PrePersist
    public void setCreationDate() {
        this.creationDate = LocalDate.now();
    }

}
