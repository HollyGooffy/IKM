package ru.ikm.utilsforprisonikm.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "article")

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caste_id")
    private Caste caste;

}