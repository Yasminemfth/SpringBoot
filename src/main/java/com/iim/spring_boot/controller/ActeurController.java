package com.iim.spring_boot.controller;

import com.iim.spring_boot.model.Acteur;
import com.iim.spring_boot.service.ActeurService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/acteur")
public class ActeurController {

    private final ActeurService acteurService;
    @Autowired
    public ActeurController(ActeurService acteurService) {
        this.acteurService = acteurService;
    }

    // liste tt les acteurs
    @GetMapping
    public List<Acteur> getAll() {
        return acteurService.getAll();
    }

    // un acteur précis par nom
    @GetMapping("/{nom}")
    public Acteur getOne(@PathVariable String nom) {
        return acteurService.findByNom(nom);
    }

    @PostMapping
    public Acteur create(@RequestParam String nom,
                         @RequestParam String couleurCheveux,
                         @RequestParam String genre,
                         @RequestParam(defaultValue = "60") int popularite,
                         @RequestParam(required = false) String film) {
        return acteurService.create(nom, couleurCheveux, genre, popularite, film);
    }

    @PutMapping
    public Acteur update(@RequestParam Long id,
                         @RequestParam(required = false) String nom,
                         @RequestParam(required = false) String couleurCheveux,
                         @RequestParam(required = false) String genre,
                         @RequestParam(required = false) Integer popularite,
                         @RequestParam(required = false) String film) {
        return acteurService.update(id, nom, couleurCheveux, genre, popularite, film);
    }

    @PostMapping("/{nom}/polemic")
    public Acteur polemic(@PathVariable String nom,
                          @RequestParam String action) {
        return acteurService.polemic(nom, action);
    }

    @PostMapping("/{nom}/convention")
    public Acteur convention(@PathVariable String nom,
                             @RequestParam(defaultValue = "10") int nombre) {
        return acteurService.convention(nom, nombre);
    }

    @GetMapping("/{nom}/hottake")
    public String hotTake(@PathVariable String nom) {
        return acteurService.hotTake(nom);
    }

    @PostMapping("/{nom}/coolaction")
    public Acteur coolAction(@PathVariable String nom,
                             @RequestParam String action) {
        return acteurService.coolAction(nom, action);
    }

    @GetMapping("/{nom}/cooltake")
    public String coolTake(@PathVariable String nom) {
        return acteurService.coolTake(nom);
    }
}