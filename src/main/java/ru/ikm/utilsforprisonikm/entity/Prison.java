package ru.ikm.utilsforprisonikm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Entity
@Table(name = "prison")

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Prison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9\\s]+$", message = "The name can only contain letters and numbers")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$", message = "Specialty must contain only letters and spaces")
    @Column(name = "city", nullable = false, length = 50)
    private String city;

}