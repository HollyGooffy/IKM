package ru.ikm.utilsforprisonikm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Entity
@Table(name = "caste")

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Caste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$", message = "Specialty must contain only letters and spaces")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$", message = "Specialty must contain only letters and spaces")
    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gang_id")
    private Gang gang;

}