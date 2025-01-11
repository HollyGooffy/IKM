package ru.ikm.utilsforprisonikm.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "gang")
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

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "leader", nullable = false, length = 50)
    private String leader;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prison_id")
    private Prison prison;

    @Column(name = "active")
    private Boolean active;
}
