package com.iim.spring_boot.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Idol et Acteur dans la même table
@DiscriminatorColumn(name = "type_idol")
public class Idol implements IdolInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    int popularite;
    String couleurCheveux;
    String genre;
    String nom;
    int badBuzz;
    int Convention;

    // @Transient = pas enregistré en base
    // remplie ici pour qu'elle existe même quand JPA utilise le constructeur vide
    @Transient
    ArrayList<String> hotActions = new ArrayList<>(List.of(
            "date",
            "scandale",
            "polémique",
            "insulte",
            "mensonge",
            "triche",
            "comportement_controverse"
    ));

    // constructeur vide obligatoire pour JPA
    protected Idol() {}

    public Idol(int popularite, String couleurCheveux, String genre, String nom) {
        this.popularite = popularite;
        this.couleurCheveux = couleurCheveux;
        this.genre = genre;
        this.nom = nom;
    }

    public Long getId() { return this.id; }
    public String getNom() { return this.nom; }
    public String getCouleurCheveux() { return this.couleurCheveux; }
    public String getGenre() { return this.genre; }
    public int getPopularite() { return this.popularite; }
    public int getBadBuzz() { return this.badBuzz; }
    public int getConvention() { return this.Convention; }

    // si c'est un Idol ou un Acteur
    public String getType() { return this.getClass().getSimpleName(); }

    public void polemic(String action, int nombre) {
        if (hotActions.contains(action)) {
            nombre = 40;
            this.popularite -= nombre;
            this.badBuzz += nombre;
        }
    }

    public void allerEnConvention(int nombre) {
        this.Convention++;
        augmenterPopularite(nombre, this.popularite, this.Convention);
    }

    @Override
    public String HotTake(int badBuzz) {
        if (badBuzz > 50) {
            return "Votre artiste est dans un bad buzz";
        }
        return "Pas de bad buzz";
    }


    @Override
    public int getPopularite(int BadBuzz, int Convention) {
        this.badBuzz = BadBuzz;
        this.Convention = Convention;
        return this.popularite;
    }

    @Override
    public void baisserPopularite(int nombre, int Popularite, int BadBuzz) {
        if (BadBuzz > 50) {
            this.popularite -= nombre;
        }
    }

    @Override
    public void augmenterPopularite(int nombre, int Popularite, int Convention) {
        if (Convention > 10) {
            this.popularite += nombre;
        }
    }
}