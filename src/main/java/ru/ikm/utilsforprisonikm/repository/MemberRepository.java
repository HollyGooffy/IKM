package ru.ikm.utilsforprisonikm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ikm.utilsforprisonikm.entity.Member;
import ru.ikm.utilsforprisonikm.entity.Prison;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByPrisonId(Long prisonId);
    List<Member> findByCasteId(Long casteId);
    List<Member> findByGangId(Long gangId);
    List<Member> findByArticleNumber(String articleNumber);
}