package fr.diginamic.projetspring.controllers;

import fr.diginamic.projetspring.entities.Genre;
import fr.diginamic.projetspring.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des genres.
 */
@RestController
@RequestMapping("/genres") // ajouter /api/genres si besoin
public class GenreController {

    /** Service gérant la logique métier des genres. */
    @Autowired
    private GenreService genreService;

    /**
     * Constructeur du contrôleur avec injection du service.
     *
     * getAllGenres Service gérant la logique métier des genres.
     */
    // Ajoutez des endpoints pour les opérations CRUD sur les genres si nécessaire
    @GetMapping public List<Genre> getAllGenres() {
        return genreService.findAll();
    }

    @GetMapping("/{genreId}")
    public Genre getGenreById(@PathVariable Integer genreId) {
        return genreService.findById(genreId);
    }

    @PostMapping
    public Genre createGenre(@RequestBody Genre genre) {
        return genreService.createGenre(genre);
    }

    @PutMapping("/{genreId}")
    public Genre updateGenre(@PathVariable("genreId") Integer genreId, @RequestBody Genre genre) {
        return genreService.updateGenre(genreId, genre);
    }

    @DeleteMapping("/{genreId}")
    public void deleteGenre(@PathVariable("genreId") Integer genreId) {
        genreService.deleteGenre(genreId);
    }
}
