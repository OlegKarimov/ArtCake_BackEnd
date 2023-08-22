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
public class Cakes {

    public enum Category{
        CATEGORY_1,
        CATEGORY_2,
        CATEGORY_3,
        CATEGORY_4
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String recipe;

    @Column(nullable = false)
    private String description;

    private Double price;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Category category;

}
