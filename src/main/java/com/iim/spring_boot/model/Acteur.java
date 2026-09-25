package com.iim.spring_boot.model;


import jakarta.persistence.Entity;

@Entity
public class Acteur extends Idol {

    public Acteur(int popularite, String couleurCheveux, String genre, String nom) {
        super(popularite, couleurCheveux, genre, nom);
    }

    // le cmptment du Bad Buzz est différent pour acteur
    @Override
    public String HotTake(int badBuzz) {
        if (badBuzz > 80) {
            return "L'acteur est problématique !";
        }

        return "Rumeurs légères, aucun impact sur sa carrière.";
    }

    // lacteur perd 2x  plus de popularité
    @Override
    public void baisserPopularite(int nombre, int Popularite, int BadBuzz) {
        if (BadBuzz > 80) {
            this.popularite -= (nombre * 2);
        }
    }

    // l'acteur gagne plus facilement de la popularité
    @Override
    public void augmenterPopularite(int nombre, int Popularite, int Convention) {
        if (Convention > 5) {
            this.popularite += nombre;
        }
    }
}