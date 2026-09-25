package com.iim.spring_boot.service;

import com.iim.spring_boot.model.Acteur;
import com.iim.spring_boot.model.Idol;
import com.iim.spring_boot.repository.IdolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdolService {

    private final IdolRepository idolRepository;

    public IdolService(IdolRepository idolRepository) {
        this.idolRepository = idolRepository;
    }

    public List<Idol> getAll() {
        return idolRepository.findAll();
    }

    public Idol findByNom(String nom) {
        return idolRepository.findFirstByNomIgnoreCase(nom).orElse(null);
    }

    public Idol create(String nom, String couleurCheveux, String genre, int popularite, String type) {
        Idol idol;
        if ("acteur".equalsIgnoreCase(type)) {
            idol = new Acteur(popularite, couleurCheveux, genre, nom);
        } else {
            idol = new Idol(popularite, couleurCheveux, genre, nom);
        }
        return idolRepository.save(idol); // INSERT en base
    }

    public Idol polemic(String nom, String action, int nombre) {
        Idol idol = findByNom(nom);
        if (idol == null) {
            return null;
        }
        idol.polemic(action, nombre);
        return idolRepository.save(idol); // updte en base
    }
    public Idol convention(String nom , int nombre){
        Idol idol = findByNom(nom);
        if (idol == null) {
            return null;
        }
        idol.allerEnConvention(nombre);
        return idolRepository.save(idol);
    }

    public String hotTake(String nom) {
        Idol idol = findByNom(nom);
        if (idol == null) {
            return null;
        }
        return idol.HotTake(idol.getBadBuzz());
    }
}
