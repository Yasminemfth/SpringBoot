package com.iim.spring_boot.repository;
/*avant c'etait garder dans des arraylist do,c a chauqe fois qu'on relancait on les perdait alors que la le repo met dans la b ase*/

import com.iim.spring_boot.model.Idol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// spring génère tt seul le code SQL à partir du nom des méthodes (exemple polemic etc)
public interface IdolRepository extends JpaRepository<Idol, Long> {

    Optional<Idol> findFirstByNomIgnoreCase(String nom);
}
