package com.iim.spring_boot.service;

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

    public Idol create(String nom, String couleurCheveux, String genre, int popularite) {
        Idol idol = new Idol(popularite, couleurCheveux, genre, nom);
        return idolRepository.save(idol);
    }

    public Idol update(Long id, String nom, String couleurCheveux,
                       String genre, Integer popularite) {
        Idol idol = idolRepository.findById(id).orElse(null); // on récupère l'idol qui existe
        if (idol == null) {
            return null;
        }
        if (nom != null) idol.setNom(nom);
        if (couleurCheveux != null) idol.setCouleurCheveux(couleurCheveux);
        if (genre != null) idol.setGenre(genre);
        if (popularite != null) idol.setPopularite(popularite);

        return idolRepository.save(idol); // update en base
    }

    public Idol polemic(String nom, String action) {
        Idol idol = findByNom(nom);
        if (idol == null) {
            return null;
        }
        idol.polemic(action);
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
    public Idol coolAction(String nom, String action) {
        Idol idol = findByNom(nom);
        if (idol == null) {
            return null;
        }
        idol.coolAction(action);
        return idolRepository.save(idol);
    }

    public String coolTake(String nom) {
        Idol idol = findByNom(nom);
        if (idol == null) {
            return null;
        }
        return idol.CoolTake(idol.getConvention());
    }
}
