package ru.ikm.utilsforprisonikm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ikm.utilsforprisonikm.entity.Member;
import ru.ikm.utilsforprisonikm.entity.Prison;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByPrison(Prison prison);
}