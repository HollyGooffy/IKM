package ru.ikm.utilsforprisonikm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ikm.utilsforprisonikm.entity.Prison;

public interface PrisonRepository extends JpaRepository<Prison, Long> {
}