package ru.ikm.utilsforprisonikm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "member")


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$", message = "Specialty must contain only letters and spaces")
    @Column(name = "member_name", nullable = false, length = 50)
    private String memberName;

    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$", message = "Specialty must contain only letters and spaces")
    @Column(name = "member_second_name", nullable = false, length = 50)
    private String memberSecondName;

    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$", message = "Specialty must contain only letters and spaces")
    @Column(name = "member_last_name", nullable = false, length = 50)
    private String memberLastName;

    @Column(name = "joined_date")
    private LocalDate joinedDate;

    @Pattern(regexp = "^\\d+$", message = "Only number")
    @Column(name = "article_number", length = 50)
    private String articleNumber;

    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$", message = "Specialty must contain only letters and spaces")
    @Column(name = "article_description", length = Integer.MAX_VALUE)
    private String articleDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caste_id")
    private Caste caste;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gang_id")
    private Gang gang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prison_id")
    private Prison prison;

    public void setArticle(Article newArticle) {
    }
}
