package com.capgemini.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Constructor for creating a user without an ID.
     *
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param birthdate the birthdate of the user
     * @param email     the email of the user
     */
    public User(
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }

    /**
     * Constructor for creating a user with an ID.
     *
     * @param id        the ID of the user
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param birthdate the birthdate of the user
     * @param email     the email of the user
     */
    public User(
            final Long id,
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }
}

