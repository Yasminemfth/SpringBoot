package com.iim.spring_boot.model;

import jakarta.persistence.*;
import java.util.Map;

// Acteur  a sa propre table acteur en base
@Entity
public class Acteur implements IdolInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    int popularite;
    String couleurCheveux;
    String genre;
    String nom;
    String film;
    int badBuzz;
    int Convention;


    @Transient
    Map<String, Integer> hotActions = Map.of(
            "date", 10,
            "scandale", 20,
            "polémique", 15,
            "insulte", 30,
            "mensonge", 25,
            "triche", 25,
            "comportement_controverse", 15
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

    protected Acteur() {}

    public Acteur(int popularite, String couleurCheveux, String genre, String nom, String film) {
        this.popularite = popularite;
        this.couleurCheveux = couleurCheveux;
        this.genre = genre;
        this.nom = nom;
        this.film = film;
    }

    public Long getId() { return this.id; }
    public String getNom() { return this.nom; }
    public String getCouleurCheveux() { return this.couleurCheveux; }
    public String getGenre() { return this.genre; }
    public String getFilm() { return this.film; }
    public int getPopularite() { return this.popularite; }
    public int getBadBuzz() { return this.badBuzz; }
    public int getConvention() { return this.Convention; }

    /* pour update */
    public void setNom(String nom) { this.nom = nom; }
    public void setCouleurCheveux(String couleurCheveux) { this.couleurCheveux = couleurCheveux; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setFilm(String film) { this.film = film; }
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
            return "Votre acteur est problématique";
        } else if (badBuzz > 50) {
            return "Votre acteur est dans un gros bad buzz";
        } else if (badBuzz > 20) {
            return "Votre acteur est dans une polémique";
        }
        return "Pas de bad buzz";
    }

    @Override
    public String CoolTake(int convention) {
        if (convention > 50) {
            return "Votre acteur est super cool ^^";
        } else if (convention > 20) {
            return "Votre acteur est cool !";
        } else if (convention > 10) {
            return "Votre acteur gagne de la popularité";
        }
        return "Pas de buzz ou votre acteur n'est pas cool";
    }

    @Override
    public int getPopularite(int BadBuzz, int Convention) {
        this.badBuzz = BadBuzz;
        this.Convention = Convention;
        return this.popularite;
    }

    // l'acteur perd 2x fois plus de popularité
    @Override
    public void baisserPopularite(int nombre, int Popularite, int BadBuzz) {
        if (BadBuzz > 80) {
            this.popularite -= (nombre * 2);
        }
    }

    // l'acteur gagne plus vite en convention (5 a la place de 10)
    @Override
    public void augmenterPopularite(int nombre, int Popularite, int Convention) {
        if (Convention > 5) {
            this.popularite += nombre;
        }
    }
}