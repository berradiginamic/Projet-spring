package fr.diginamic.projetspring.controllers;

import fr.diginamic.projetspring.entities.Realisateur;
import fr.diginamic.projetspring.services.RealisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Contrôleur REST pour la gestion des réalisateurs.
 */
@RestController
@RequestMapping("/realisateurs") // ajouter /api/realisateurs si besoins
public class RealisateurController {

    /** Service gérant la logique métier des réalisateurs. */
    @Autowired
    private RealisateurService realisateurService;

    /**
     * Constructeur du contrôleur avec injection du service.
     *
     * @param realisateurService Service gérant la logique métier des réalisateurs.
     */

    /**
     * Endpoint pour obtenir la liste de tous les réalisateurs.
     *
     * @return La liste de tous les réalisateurs.
     */
    @GetMapping
    public List<Realisateur> getAllRealisateurs() {
        return realisateurService.getAllRealisateurs();
    }

    /**
     * Endpoint pour obtenir un réalisateur par son identifiant.
     *
     * @param idRealisateur Identifiant du réalisateur à récupérer.
     * @return Le réalisateur correspondant à l'identifiant.
     */
    @GetMapping("/{idRealisateur}")
    public ResponseEntity<Realisateur> getRealisateurById(@PathVariable("idRealisateur") Integer idRealisateur) {
        Optional<Realisateur> realisateur = realisateurService.getRealisateurById(idRealisateur);
        return realisateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint pour sauvegarder un nouveau réalisateur.
     *
     * @param realisateur Le réalisateur à sauvegarder.
     * @return Le réalisateur sauvegardé.
     */
    @PostMapping
    public Realisateur createRealisateur(@RequestBody Realisateur realisateur) {
        return realisateurService.createRealisateur(realisateur);
    }

    /**
     * Endpoint pour supprimer un réalisateur par son identifiant.
     *
     * @param idRealisateur Identifiant du réalisateur à supprimer.
     */

    @PutMapping("/{idRealisateur}")
    public Realisateur updateRealisateur(@PathVariable Integer idRealisateur, @RequestBody Realisateur realisateur) {
        return realisateurService.updateRealisateur(idRealisateur, realisateur);
    }
    @DeleteMapping("/{idRealisateur}")
    public void deleteRealisateurById(@PathVariable("id") Integer idRealisateur) {
        realisateurService.deleteRealisateurById(idRealisateur);
    }

    // Implementation des requetes
    // Tache 7:  Extraire tous les films d’un réalisateur donné
    @GetMapping("/{idRealisateur}/films")
    public List<Object[]> getFilmsByRealisateurId(@PathVariable("idRealisateur") Integer idRealisateur) {
        return realisateurService.findFilmsByRealisateurId(idRealisateur);
    }


    // Ajoutez d'autres méthodes d'endpoint au besoin
}
