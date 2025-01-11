package ru.ikm.utilsforprisonikm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ikm.utilsforprisonikm.entity.Gang;

import java.util.List;

public interface GangRepository extends JpaRepository<Gang, Long> {
    List<Gang> findByPrisonId(Long prisonId);

    boolean existsByLeader(String leader);
}