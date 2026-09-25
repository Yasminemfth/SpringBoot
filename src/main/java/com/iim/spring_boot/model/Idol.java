package com.iim.spring_boot.model;

import jakarta.persistence.*;
import java.util.Map;

@Entity
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

    @Transient
    Map<String, Integer> hotActions = Map.of(
            "date",10,
            "scandale",20,
            "polémique",15,
            "insulte",30,
            "mensonge",25,
            "triche",25,
            "comportement_controverse",15
    );

    @Transient
    Map<String, Integer> coolActions = Map.of(
            "don", 7,
            "concert_gratuit", 25,
            "charite", 10,
            "fan_meeting", 15,
            "benevolat", 10,
            "excuses_publiques", 10,
            "collaboration", 5
    );


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
    /*pour update*/
    public void setNom(String nom) { this.nom = nom; }
    public void setCouleurCheveux(String couleurCheveux) { this.couleurCheveux = couleurCheveux; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setPopularite(int popularite) { this.popularite = popularite; }

    @Override
    public void polemic(String action) {
        if (hotActions.containsKey(action)) {
            int points = hotActions.get(action);
            this.popularite -= points;
            this.badBuzz += points;
        }
    }

    // conventions : on compte, et au-delà du seuil on gagne de la popularité
    @Override
    public void allerEnConvention(int nombre) {
        this.Convention++;
        augmenterPopularite(nombre, this.popularite, this.Convention);
    }

    // bonnes actions : chaque action rapporte ses propres points
    @Override
    public void coolAction(String action) {
        if (coolActions.containsKey(action)) {
            int points = coolActions.get(action);
            this.popularite += points;
        }
    }

    @Override
    public String HotTake(int badBuzz) {
        if (badBuzz > 100) {
            return "Votre artiste est problématique";
        } else if (badBuzz > 50) {
            return "Votre artiste est dans un gros bad buzz";
        } else if (badBuzz > 20) {
            return "Votre artiste est dans une polémique";
        }
        return "Pas de bad buzz";
    }

    @Override
    public String CoolTake(int convention) {
        if (convention > 50) {
            return "Votre artiste est super cool ^^";
        } else if (convention > 20) {
            return "Votre artiste est cool !";
        } else if (convention > 10) {
            return "Votre artiste gagne de la popularité";
        }
        return "Pas de buzz ou votre artiste n'est pas cool";
    }

    @Override
    public int getPopularite(int BadBuzz,int Convention) {
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