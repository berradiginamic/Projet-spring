package fr.diginamic.projetspring.repositories;

import fr.diginamic.projetspring.entities.Realisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Interface repository pour l'entité Réalisateur, utilisant Spring Data JPA.
 */
public interface RealisateurRepository extends JpaRepository<Realisateur, Integer> {

    // Aucune méthode spécifique déclarée ici, car JpaRepository offre déjà des méthodes génériques pour les opérations CRUD.

    // Ajoutez d'autres méthodes déclaratives en fonction des besoins
    // Find all réalisateurs by name
    List<Realisateur> findAllByNom(String nom);

    // Find all réalisateurs by lieu de naissance
    List<Realisateur> findAllByLieuNaissance(String lieuNaissance);

    // Find all réalisateurs by date de naissance
    List<Realisateur> findAllByDateNaissance(Date dateNaissance);

    // Find all réalisateurs by URL profile
    List<Realisateur> findAllByUrlProfile(String urlProfile);

    Realisateur findByIdIMDB(String realisateurIdIMDB);

    //Implementation des requetes:
    //  Tache 7: Extraire tous les films d’un réalisateur donné
    @Query("SELECT f.nom AS film_nom, f.anneeSortie " +
            "FROM Realisateur r " +
            "JOIN RealisateurFilm rf ON r.idRealisateur = rf.realisateur.idRealisateur " +
            "JOIN Film f ON rf.film.filmId = f.filmId " +
            "WHERE r.idRealisateur = :realisateurId")
    List<Object[]> findFilmsByRealisateurId(@Param("realisateurId") Integer realisateurId);

}
