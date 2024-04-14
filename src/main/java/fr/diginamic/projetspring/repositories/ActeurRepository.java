package fr.diginamic.projetspring.repositories;

import fr.diginamic.projetspring.entities.Acteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Interface repository pour l'entité Acteur, utilisant Spring Data JPA.
 */
public interface ActeurRepository extends JpaRepository<Acteur, Integer> {

    // Find all actors by name
    List<Acteur> findAllByNom(String nom);

    // Find all actors by lieu de naissance
    List<Acteur> findAllByLieuNaissance(String lieuNaissance);

    // Find all actors by date de naissance
    List<Acteur> findAllByDateNaissance(Date dateNaissance);

    // Find all actors by URL profile
    List<Acteur> findAllByUrlProfile(String urlProfile);
    Acteur findByIdIMDB(String idIMDB);

    /** Création des requetes pour les extractions */
    // Implementation des Queries pour les extractions:
    // Tache 1: Extraire tous les films (nom et années de sortie) d’un acteur donné
    @Query("SELECT f.nom AS film_nom, f.anneeSortie " +
            "FROM Acteur a " +
            "JOIN RoleFilm r ON a.acteurId = r.acteur.acteurId " +
            "JOIN Film f ON r.film.filmId = f.filmId " +
            "WHERE a.acteurId = :acteurId")
    List<Object[]> findFilmsByActeurId(@Param("acteurId") Integer acteurId);

    //Tache 6:  Extraire les acteurs communs à 2 films donnés
    @Query("SELECT a.nom AS acteurNom " +
            "FROM Film f1 " +
            "JOIN RoleFilm r1 ON f1.filmId = r1.film.filmId " +
            "JOIN Acteur a ON r1.acteur.acteurId = a.acteurId " +
            "JOIN RoleFilm r2 ON a.acteurId = r2.acteur.acteurId " +
            "JOIN Film f2 ON r2.film.filmId = f2.filmId " +
            "WHERE f1.filmId = :filmId1 AND f2.filmId = :filmId2")
    List<Object[]> findActeursInFilms(@Param("filmId1") Integer filmId1,
                                      @Param("filmId2") Integer filmId2);

}

