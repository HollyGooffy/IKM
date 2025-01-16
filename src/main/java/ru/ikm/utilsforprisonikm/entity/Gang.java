package ru.ikm.utilsforprisonikm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "gang", uniqueConstraints = {@UniqueConstraint(columnNames = "prison_id")})


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Gang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$", message = "Specialty must contain only letters and spaces")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$", message = "Specialty must contain only letters and spaces")
    @Column(name = "leader", length = 50)
    private String leader;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prison_id")
    private Prison prison;

}
