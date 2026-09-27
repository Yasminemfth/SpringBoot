package com.iim.spring_boot.controller;

import com.iim.spring_boot.model.Idol;
import com.iim.spring_boot.service.IdolService;
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
    public Idol getOne(@PathVariable String nom) {
        return idolService.findByNom(nom);
    }

    @PostMapping
    public Idol create(@RequestParam String nom,
                       @RequestParam String couleurCheveux,
                       @RequestParam String genre,
                       @RequestParam(defaultValue = "50") int popularite) {
        return idolService.create(nom, couleurCheveux, genre, popularite);
    }

    @PutMapping
    public Idol update(@RequestParam Long id,
                       @RequestParam(required = false) String nom,
                       @RequestParam(required = false) String couleurCheveux,
                       @RequestParam(required = false) String genre,
                       @RequestParam(required = false) Integer popularite) {
        return idolService.update(id, nom, couleurCheveux, genre, popularite);
    }

    @PostMapping("/{nom}/polemic")
    public Idol polemic(@PathVariable String nom,
                        @RequestParam String action) {
        return idolService.polemic(nom, action);
    }

    @PostMapping("/{nom}/convention")
    public Idol convention(@PathVariable String nom,
                           @RequestParam(defaultValue = "10") int nombre) {
        return idolService.convention(nom, nombre);
    }

    @GetMapping("/{nom}/hottake")
    public String hotTake(@PathVariable String nom) {
        return idolService.hotTake(nom);
    }

    @PostMapping("/{nom}/coolaction")
    public Idol coolAction(@PathVariable String nom,
                           @RequestParam String action) {
        return idolService.coolAction(nom, action);
    }

    @GetMapping("/{nom}/cooltake")
    public String coolTake(@PathVariable String nom) {
        return idolService.coolTake(nom);
    }
}