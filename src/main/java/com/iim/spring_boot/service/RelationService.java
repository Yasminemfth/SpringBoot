package com.iim.spring_boot.service;

import com.iim.spring_boot.model.Acteur;
import com.iim.spring_boot.model.Idol;
import com.iim.spring_boot.model.Relation;
import com.iim.spring_boot.repository.ActeurRepository;
import com.iim.spring_boot.repository.IdolRepository;
import com.iim.spring_boot.repository.RelationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationService {

    private final RelationRepository relationRepository;
    private final IdolRepository idolRepository;
    private final ActeurRepository acteurRepository;

    public RelationService(RelationRepository relationRepository,
                           IdolRepository idolRepository,
                           ActeurRepository acteurRepository) {
        this.relationRepository = relationRepository;
        this.idolRepository = idolRepository;
        this.acteurRepository = acteurRepository;
    }

    public List<Relation> getAll() {
        return relationRepository.findAll();
    }

    // retrouve la relation entre une idol et un acteur (par leurs noms)
    public Relation find(String nomIdol, String nomActeur) {
        Idol idol = idolRepository.findFirstByNomIgnoreCase(nomIdol).orElse(null);
        Acteur acteur = acteurRepository.findFirstByNomIgnoreCase(nomActeur).orElse(null);
        if (idol == null || acteur == null) {
            return null;
        }
        return relationRepository.findFirstByIdolAndActeur(idol, acteur).orElse(null);
    }

    // crée une relation neutre (score 0) entre une idol et un acteur
    public Relation create(String nomIdol, String nomActeur) {
        Idol idol = idolRepository.findFirstByNomIgnoreCase(nomIdol).orElse(null);
        Acteur acteur = acteurRepository.findFirstByNomIgnoreCase(nomActeur).orElse(null);
        if (idol == null || acteur == null) {
            return null; // l'un des deux n'existe pas
        }

        // si la relation existe déjà,  la renvoie au lieu d'en créer une new
        Relation existante = relationRepository.findFirstByIdolAndActeur(idol, acteur).orElse(null);
        if (existante != null) {
            return existante;
        }

        Relation relation = new Relation(idol, acteur);
        return relationRepository.save(relation); // INSERT en base
    }

    // collab : la relation s'améliore
    public Relation collaboration(String nomIdol, String nomActeur) {
        Relation relation = find(nomIdol, nomActeur);
        if (relation == null) {
            return null;
        }
        relation.augmenter(15);
        return relationRepository.save(relation);
    }

    // si clash la relation se dégrade
    public Relation clash(String nomIdol, String nomActeur) {
        Relation relation = find(nomIdol, nomActeur);
        if (relation == null) {
            return null;
        }
        relation.augmenter(-20);
        return relationRepository.save(relation);
    }
}