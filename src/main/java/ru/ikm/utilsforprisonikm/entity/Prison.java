package ru.ikm.utilsforprisonikm.entity;

import jakarta.persistence.*;
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

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

}