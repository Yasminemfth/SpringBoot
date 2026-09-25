package com.iim.spring_boot.service;

import com.iim.spring_boot.model.Acteur;
import com.iim.spring_boot.repository.ActeurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActeurService {

    private final ActeurRepository acteurRepository;

    public ActeurService(ActeurRepository acteurRepository) {
        this.acteurRepository = acteurRepository;
    }

    public List<Acteur> getAll() {
        return acteurRepository.findAll();
    }

    public Acteur findByNom(String nom) {
        return acteurRepository.findFirstByNomIgnoreCase(nom).orElse(null);
    }

    public Acteur create(String nom, String couleurCheveux, String genre, int popularite, String film) {
        Acteur acteur = new Acteur(popularite, couleurCheveux, genre, nom, film);
        return acteurRepository.save(acteur); // INSERT en base
    }

    public Acteur update(Long id, String nom, String couleurCheveux,
                         String genre, Integer popularite, String film) {
        Acteur acteur = acteurRepository.findById(id).orElse(null);
        if (acteur == null) {
            return null;
        }
        if (nom != null) acteur.setNom(nom);
        if (couleurCheveux != null) acteur.setCouleurCheveux(couleurCheveux);
        if (genre != null) acteur.setGenre(genre);
        if (popularite != null) acteur.setPopularite(popularite);
        if (film != null) acteur.setFilm(film);

        return acteurRepository.save(acteur); // UPDATE en base
    }

    public Acteur polemic(String nom, String action) {
        Acteur acteur = findByNom(nom);
        if (acteur == null) {
            return null;
        }
        acteur.polemic(action);
        return acteurRepository.save(acteur);
    }

    public Acteur convention(String nom, int nombre) {
        Acteur acteur = findByNom(nom);
        if (acteur == null) {
            return null;
        }
        acteur.allerEnConvention(nombre);
        return acteurRepository.save(acteur);
    }

    public String hotTake(String nom) {
        Acteur acteur = findByNom(nom);
        if (acteur == null) {
            return null;
        }
        return acteur.HotTake(acteur.getBadBuzz());
    }
    public Acteur coolAction(String nom, String action) {
        Acteur acteur = findByNom(nom);
        if (acteur == null) {
            return null;
        }
        acteur.coolAction(action);
        return acteurRepository.save(acteur);
    }

    public String coolTake(String nom) {
        Acteur acteur = findByNom(nom);
        if (acteur == null) {
            return null;
        }
        return acteur.CoolTake(acteur.getConvention());
    }
}