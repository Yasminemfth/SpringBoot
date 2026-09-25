package com.iim.spring_boot.repository;

import com.iim.spring_boot.model.Acteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActeurRepository extends JpaRepository<Acteur, Long> {

    Optional<Acteur> findFirstByNomIgnoreCase(String nom);
}