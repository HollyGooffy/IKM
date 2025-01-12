package ru.ikm.utilsforprisonikm.entity;

import jakarta.persistence.*;
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

    @Column(name = "member_name", nullable = false, length = 50)
    private String memberName;

    @Column(name = "member_second_name", nullable = false, length = 50)
    private String memberSecondName;

    @Column(name = "member_last_name", nullable = false, length = 50)
    private String memberLastName;

    @Column(name = "joined_date")
    private LocalDate joinedDate;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "article_number", length = 50)
    private String articleNumber;

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

    // Getters and Setters
    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }
}
