package com.iim.spring_boot.controller;

import com.iim.spring_boot.model.Idol;
import com.iim.spring_boot.service.IdolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/idol")
public class IdolController {

    private final IdolService idolService;

    public IdolController(IdolService idolService) {
        this.idolService = idolService;
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }

    // liste ttes les idols
    @GetMapping
    public List<Idol> getAll() {
        return idolService.getAll();
    }

    // une idol précise par nom
    @GetMapping("/{nom}")
    public ResponseEntity<Idol> getOne(@PathVariable String nom) {
        Idol idol = idolService.findByNom(nom);
        if (idol == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.ok(idol); // 200
    }


    @GetMapping("/create")
    public Idol create(@RequestParam String nom,
                       @RequestParam String couleurCheveux,
                       @RequestParam String genre,
                       @RequestParam(defaultValue = "50") int popularite,
                       @RequestParam(defaultValue = "idol") String type) {
        return idolService.create(nom, couleurCheveux, genre, popularite, type);
    }

    @GetMapping("/{nom}/polemic")
    public ResponseEntity<Idol> polemic(@PathVariable String nom,
                                        @RequestParam String action,
                                        @RequestParam int nombre) {
        Idol idol = idolService.polemic(nom, action, nombre);
        if (idol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(idol);
    }

    @GetMapping("/{nom}/convention")
    public ResponseEntity<Idol> convention(@PathVariable String nom,
                                           @RequestParam(defaultValue = "10") int nombre) {
        Idol idol = idolService.convention(nom, nombre);
        if (idol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(idol);
    }

    @GetMapping("/{nom}/hottake")
    public ResponseEntity<String> hotTake(@PathVariable String nom) {
        String result = idolService.hotTake(nom);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }


}