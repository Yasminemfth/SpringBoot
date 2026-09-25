package com.iim.spring_boot.controller;

import com.iim.spring_boot.model.Relation;
import com.iim.spring_boot.service.RelationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relation")
public class RelationController {

    private final RelationService relationService;

    public RelationController(RelationService relationService) {
        this.relationService = relationService;
    }

    // liste toutes les relations
    @GetMapping
    public List<Relation> getAll() {
        return relationService.getAll();
    }

    // la relation entre une idol et un acteur précis
    @GetMapping("/{nomIdol}/{nomActeur}")
    public ResponseEntity<Relation> getOne(@PathVariable String nomIdol,
                                           @PathVariable String nomActeur) {
        Relation relation = relationService.find(nomIdol, nomActeur);
        if (relation == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.ok(relation); // 200
    }

    // crée une relation neutre entre une idol et un acteur
    @PostMapping
    public ResponseEntity<Relation> create(@RequestParam String nomIdol,
                                           @RequestParam String nomActeur) {
        Relation relation = relationService.create(nomIdol, nomActeur);
        if (relation == null) {
            return ResponseEntity.notFound().build(); // idol ou acteur introuvable
        }
        return ResponseEntity.ok(relation);
    }

    // collaboration : le score monte
    @PostMapping("/collaboration")
    public ResponseEntity<Relation> collaboration(@RequestParam String nomIdol,
                                                  @RequestParam String nomActeur) {
        Relation relation = relationService.collaboration(nomIdol, nomActeur);
        if (relation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(relation);
    }

    // clash : le score baisse
    @PostMapping("/clash")
    public ResponseEntity<Relation> clash(@RequestParam String nomIdol,
                                          @RequestParam String nomActeur) {
        Relation relation = relationService.clash(nomIdol, nomActeur);
        if (relation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(relation);
    }
}