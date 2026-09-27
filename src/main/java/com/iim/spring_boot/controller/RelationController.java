package com.iim.spring_boot.controller;

import com.iim.spring_boot.model.Relation;
import com.iim.spring_boot.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relation")
public class RelationController {

    private final RelationService relationService;
    @Autowired
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
    public Relation getOne(@PathVariable String nomIdol,
                           @PathVariable String nomActeur) {
        return relationService.find(nomIdol, nomActeur);
    }

    // crée une relation neutre entre une idol et un acteur
    @PostMapping
    public Relation create(@RequestParam String nomIdol,
                           @RequestParam String nomActeur) {
        return relationService.create(nomIdol, nomActeur);
    }

    // collaboration : le score monte
    @PostMapping("/collaboration")
    public Relation collaboration(@RequestParam String nomIdol,
                                  @RequestParam String nomActeur) {
        return relationService.collaboration(nomIdol, nomActeur);
    }

    // clash : le score baisse
    @PostMapping("/clash")
    public Relation clash(@RequestParam String nomIdol,
                          @RequestParam String nomActeur) {
        return relationService.clash(nomIdol, nomActeur);
    }
}