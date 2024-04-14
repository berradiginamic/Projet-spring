package fr.diginamic.projetspring.repositories;

import fr.diginamic.projetspring.entities.RoleFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Interface repository pour l'entité RoleFilm, utilisant Spring Data JPA.
 */
public interface RoleFilmRepository extends JpaRepository<RoleFilm, Integer> {

    // Aucune méthode spécifique déclarée ici, car JpaRepository offre déjà des méthodes génériques pour les opérations CRUD.

    // Ajoutez d'autres méthodes déclaratives en fonction des besoins
    // Find all roles by name
    /*@Query("SELECT rf FROM RoleFilm rf WHERE rf.type = :type")
    List<RoleFilm> findAllByType(@Param("type")String type);*/

    // Find all roles by film_id
    @Query("SELECT rf FROM RoleFilm rf JOIN rf.film f WHERE f.filmId = :filmId")
    List<RoleFilm> findAllByFilmId(@Param("filmId")Integer filmId);

    // Find all roles by acteur_id
    @Query("SELECT rf FROM RoleFilm rf JOIN rf.acteur a WHERE a.acteurId = :acteurId")
    List<RoleFilm> findAllByActeurId(@Param("acteurId")Integer acteurId);

}
