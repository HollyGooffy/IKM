package ru.ikm.utilsforprisonikm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @Pattern(regexp = "^\\d+$", message = "only number")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Size(max = 200)
    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caste_id")
    private Caste caste;

    public void setArticleNumber(String articleNumber) {
    }

    public void setArticleDescription(String articleDescription) {
    }


}
