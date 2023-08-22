package de.ait.final_project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * 22-08-23/0022
 * ArtCake_BackEnd
 *
 * @author Dmytro Sainozhenko (AIT TR)
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private LocalDate publishDate;

    @ManyToOne
    @JoinColumn(name = "about_id")
    private User about;
}
