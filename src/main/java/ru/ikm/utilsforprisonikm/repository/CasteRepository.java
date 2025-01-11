package ru.ikm.utilsforprisonikm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ikm.utilsforprisonikm.entity.Caste;

import java.util.List;

public interface CasteRepository extends JpaRepository<Caste, Long> {
    List<Caste> id(Long id);
}