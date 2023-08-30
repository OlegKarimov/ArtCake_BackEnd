package de.ait.artcake.models;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class User {

    public enum Role {
        CLIENT,
        MANAGER,
        CONFECTIONER
    }

    public enum State {
        NOT_CONFIRMED,
        CONFIRMED,
        BANNED // TODO сделать в будущем если будет время(не разрешать ему оформлять заказы или коменты если они будут)
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String hashPassword;

    @Column(nullable = false)
    private String town;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private Integer houseNumber;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private State state;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;



}
