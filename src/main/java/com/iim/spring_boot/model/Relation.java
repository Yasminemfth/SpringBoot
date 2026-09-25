package com.iim.spring_boot.model;

import jakarta.persistence.*;

@Entity
public class Relation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // plusieurs relations peuvent concerner la même idol
    @ManyToOne
    Idol idol;

    // plusieurs relations peuvent concerner le même acteur
    @ManyToOne
    Acteur acteur;

    int score;

    protected Relation() {}

    // une relation démarre neutre donc à 0
    public Relation(Idol idol, Acteur acteur) {
        this.idol = idol;
        this.acteur = acteur;
        this.score = 0;
    }

    /*permet de recup les id , idol , acteur et score*/
    public Long getId() { return this.id; }
    public Idol getIdol() { return this.idol; }
    public Acteur getActeur() { return this.acteur; }
    public int getScore() { return this.score; }

    public void augmenter(int points) {
        this.score += points;
    }

    // le statut dépend du score comme HotTake avec bad buzz
    public String getStatut() {
        if (score > 50) {
            return "Proches";
        } else if (score > 10) {
            return "Amis";
        } else if (score >= -10) {
            return "Neutre";
        } else if (score >= -50) {
            return "Tendus";
        }
        return "Ennemis";
    }
}