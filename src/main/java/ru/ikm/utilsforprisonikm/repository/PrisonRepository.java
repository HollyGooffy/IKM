package ru.ikm.utilsforprisonikm.repository;

import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ikm.utilsforprisonikm.entity.Prison;

public interface PrisonRepository extends JpaRepository<Prison, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndCity(@Pattern(regexp = "^[a-zA-Zа-яА-Я0-9\\s]+$", message = "The name can only contain letters and numbers") String name, @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$", message = "Specialty must contain only letters and spaces") String city);
}