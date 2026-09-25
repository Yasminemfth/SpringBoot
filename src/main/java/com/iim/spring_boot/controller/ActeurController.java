package com.iim.spring_boot.controller;

import com.iim.spring_boot.model.Acteur;
import com.iim.spring_boot.service.ActeurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acteur")
public class ActeurController {

    private final ActeurService acteurService;

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
    public ResponseEntity<Acteur> getOne(@PathVariable String nom) {
        Acteur acteur = acteurService.findByNom(nom);
        if (acteur == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.ok(acteur); // 200
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
    public ResponseEntity<Acteur> polemic(@PathVariable String nom,
                                          @RequestParam String action) {
        Acteur acteur = acteurService.polemic(nom, action);
        if (acteur == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(acteur);
    }

    @PostMapping("/{nom}/convention")
    public ResponseEntity<Acteur> convention(@PathVariable String nom,
                                             @RequestParam(defaultValue = "10") int nombre) {
        Acteur acteur = acteurService.convention(nom, nombre);
        if (acteur == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(acteur);
    }

    @GetMapping("/{nom}/hottake")
    public ResponseEntity<String> hotTake(@PathVariable String nom) {
        String result = acteurService.hotTake(nom);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
    @PostMapping("/{nom}/coolaction")
    public ResponseEntity<Acteur> coolAction(@PathVariable String nom,
                                           @RequestParam String action) {
        Acteur acteur = acteurService.coolAction(nom, action);
        if (acteur == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(acteur);
    }

    @GetMapping("/{nom}/cooltake")
    public ResponseEntity<String> coolTake(@PathVariable String nom) {
        String result = acteurService.coolTake(nom);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}