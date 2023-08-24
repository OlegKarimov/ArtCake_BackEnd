package de.ait.final_project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String ingredients;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Category category;


}
