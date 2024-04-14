package fr.diginamic.projetspring.services;

import fr.diginamic.projetspring.entities.Acteur;
import fr.diginamic.projetspring.entities.Film;
import fr.diginamic.projetspring.entities.RoleFilm;
import fr.diginamic.projetspring.repositories.RoleFilmRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service gérant les opérations liées à l'entité RoleFilm.
 */
@Service
public class RoleFilmService {
    /**
     * Constructeur du service RoleFilm.
     *
     * @param roleRepository Le repository pour les opérations liées à l'entité RoleFilm.
     */
    @Autowired
    private RoleFilmRepository roleRepository;
    @Autowired
    private ActeurService acteurService;

    @Autowired
    private FilmService filmService;

    /**
     * Récupère un rôle par son identifiant.
     *
     * @param roleId L'identifiant du rôle.
     * @return Le rôle correspondant à l'identifiant, ou un Optional vide s'il n'existe pas.
     */
    public Optional<RoleFilm> getRoleById(Integer roleId) {
        return roleRepository.findById(roleId);
    }

    /**
     * Récupère tous les rôles.
     *
     * @return Une liste de tous les rôles.
     */
    public List<RoleFilm> getAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * Enregistre un nouveau rôle dans la base de données.
     *
     * @param role Le rôle à enregistrer.
     * @return Le rôle enregistré.
     */
   @Transactional
    public RoleFilm createRoleFilm(RoleFilm role) {
       // Map IMDB IDs to database IDs
       Acteur acteur = acteurService.findByIdIMDB(role.getActeur().getIdIMDB());
       Film film = filmService.findByIdIMDB(role.getFilm().getIdIMDB());
       role.setActeur(acteur);
       role.setFilm(film);
       return roleRepository.save(role);
   }


    /**
     * Supprime un rôle par son identifiant.
     *
     * @param roleId L'identifiant du rôle à supprimer.
     */
    public void deleteRoleFilm(Integer roleId) {
        roleRepository.deleteById(roleId);
    }

    /**
     * Récupère tous les rôles associés à un film.
     *
     * @param filmId L'identifiant du film.
     * @return Une liste de rôles associés au film, ou une liste vide s'il n'y en a pas.
     */
    public List<RoleFilm> findByFilmId(Integer filmId) {
        return roleRepository.findAllByFilmId(filmId);
    }

    public List<RoleFilm> findByActeurId(Integer acteurId) {
        return roleRepository.findAllByActeurId(acteurId);
    }

    // Ajoutez d'autres méthodes en fonction des besoins
}
