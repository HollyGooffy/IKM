package ru.ikm.utilsforprisonikm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ikm.utilsforprisonikm.entity.Nickname;

public interface NicknameRepository extends JpaRepository<Nickname, Long> {
}