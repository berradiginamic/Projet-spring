package fr.diginamic.projetspring.controllers;

import fr.diginamic.projetspring.entities.Film;
import fr.diginamic.projetspring.entities.RoleFilm;
import fr.diginamic.projetspring.services.RoleFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Contrôleur REST pour la gestion des rôles dans les films.
 */
@RestController
@RequestMapping("/roles")
public class RoleFilmController {

    /** Service gérant la logique métier des rôles dans les films. */
    private final RoleFilmService roleFilmService;

    /**
     * Constructeur du contrôleur avec injection du service.
     *
     * @param roleFilmService Service gérant la logique métier des rôles dans les films.
     */
    @Autowired
    public RoleFilmController(RoleFilmService roleFilmService) {
        this.roleFilmService = roleFilmService;
    }

    /**
     * Endpoint pour obtenir un rôle par son identifiant.
     *
     * @param roleId Identifiant du rôle à récupérer.
     * @return Le rôle correspondant à l'identifiant.
     */
    @GetMapping("/{roleId}")
    public ResponseEntity<RoleFilm> getRoleById(@PathVariable("roleId") Integer roleId) {
        Optional<RoleFilm> role = roleFilmService.getRoleById(roleId);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint pour obtenir la liste de tous les rôles.
     *
     * @return La liste de tous les rôles.
     */
    @GetMapping
    public List<RoleFilm> getAllRoles() {
        return roleFilmService.getAllRoles();
    }

    /**
     * Endpoint pour créer un nouveau rôle.
     *
     * @param role Le rôle à créer.
     * @return Le rôle créé.
     */
    @PostMapping
    public RoleFilm createRoleFilm(@RequestBody RoleFilm role) {
        return roleFilmService.createRoleFilm(role);
    }

    /**
     * Endpoint pour supprimer un rôle par son identifiant.
     *
     * @param roleId Identifiant du rôle à supprimer.
     * @return Réponse indiquant le succès de l'opération.
     */
    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRoleFilm(@PathVariable("roleId") Integer roleId) {
        roleFilmService.deleteRoleFilm(roleId);
        return ResponseEntity.noContent().build();
    }

    // Ajoutez d'autres méthodes d'endpoint au besoin
    @GetMapping("/acteur/{acteurId}")
    public List<RoleFilm> findByActeurId(@PathVariable("acteurId") Integer acteurId) {
        return roleFilmService.findByActeurId(acteurId);
    }
}
