package com.iim.spring_boot.repository;

import com.iim.spring_boot.model.Acteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ActeurRepository extends JpaRepository<Acteur, Long> {

    Optional<Acteur> findFirstByNomIgnoreCase(String nom);
}