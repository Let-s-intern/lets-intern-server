package com.letsintern.letsintern.domain.banner.domain.popup.repository;

import com.letsintern.letsintern.domain.banner.domain.popup.domain.Popup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PopupRepository extends JpaRepository<Popup, Long>, PopupRepositoryCustom {

    Optional<Popup> findById(Long id);

}
