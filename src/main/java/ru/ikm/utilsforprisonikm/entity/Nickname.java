package ru.ikm.utilsforprisonikm.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "nickname")

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Nickname {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "art_id")
    private Article art;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prison_id")
    private Prison prison;

    @Column(name = "active")
    private Boolean active;

}