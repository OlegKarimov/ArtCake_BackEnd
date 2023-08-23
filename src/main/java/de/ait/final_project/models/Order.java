package de.ait.final_project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orderss")
public class Order {

    public enum State {
        IN_PROCESS,
        FINISHED,
        CREATED
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double count;

    @Column(length = 200)
    private String description;

    private Double totalPrice;

    private LocalDate creationDate;

    private LocalDate deadline;

    private Integer clientId;

    private Integer cakeId;

    private String cakeName;

    private Long confectionerId;

    @Enumerated(value = EnumType.STRING)
    private State state;


    @PrePersist
    public void setCreationDate() {
        this.creationDate = LocalDate.now();
    }

}
