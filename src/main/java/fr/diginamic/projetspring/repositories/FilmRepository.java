package fr.diginamic.projetspring.repositories;

import fr.diginamic.projetspring.entities.Film;
import fr.diginamic.projetspring.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * Interface repository pour l'entité Film, utilisant Spring Data JPA.
 */
public interface FilmRepository extends JpaRepository<Film, Integer> {

    // Find all films by annee de sortie
    List<Film> findAllByAnneeSortie(Integer anneeSortie);

    // Find all films by langue
    List<Film> findAllByLangue(String langue);

    // Find all films by lieu de tournage
    List<Film> findAllByLieuTournage(String lieuTournage);

    // Find all films by réalisateur nom
    List<Film> findAllByNom(String nom);

    // Find all films by pays
    List<Film> findAllByPays(String pays);

    // Find all films by rating
    List<Film> findAllByRating(String rating);

    // Find all films by resume
    List<Film> findAllByResume(String resume);

    // Find all films by URL profile
    List<Film> findAllByUrlProfile(String urlProfile);

    // Find all films by genre
    List<Film> findAllByGenresIn(Set<Genre> genres);

    Film findByIdIMDB(String idIMDB);

    List<Film> findByGenres_Type(String genreType);

    // Implementation des queries:
    // Tache 2: Extraire tous les rôles d’un film donné
    @Query("SELECT a.nom AS acteur_nom, r.personnage " +
            "FROM Film f " +
            "JOIN RoleFilm r ON f.filmId = r.film.filmId " +
            "JOIN Acteur a ON r.acteur.acteurId = a.acteurId " +
            "WHERE f.filmId = :filmId")
    List<Object[]> findActorsAndCharactersByFilmId(@Param("filmId") Integer filmId);

    //  Tache 3: Extraire les films sortis entre 2 années données
    @Query("SELECT f FROM Film f WHERE f.anneeSortie BETWEEN :startYear AND :endYear")
    List<Film> findFilmsReleasedBetweenYears(@Param("startYear") int startYear, @Param("endYear") int endYear);

    // Tache 4: Extraire les films communs à 2 acteurs ou actrices donnés.
    @Query("SELECT f.nom AS filmNom, f.anneeSortie " +
            "FROM Acteur a1 " +
            "JOIN RoleFilm r1 ON a1.acteurId = r1.acteur.acteurId " +
            "JOIN Film f ON r1.film.filmId = f.filmId " +
            "JOIN RoleFilm r2 ON f.filmId = r2.film.filmId " +
            "JOIN Acteur a2 ON r2.acteur.acteurId = a2.acteurId " +
            "WHERE a1.acteurId = :acteurId1 AND a2.acteurId = :acteurId2")
    List<Object[]> findFilmsByTwoActors(@Param("acteurId1") Integer acteurId1, @Param("acteurId2") Integer acteurId2);

    // Tache 5:  Extraire tous les films d’un genre donné
    @Query("SELECT f.nom AS filmNom, f.anneeSortie " +
            "FROM Genre g " +
            "JOIN g.films f " +       // Assuming "films" is the property name in Genre entity
            "WHERE g.genreId = :genreId")
    List<Object[]> findFilmsByGenre(@Param("genreId") Integer genreId);

    // Tache 8: Extraire les films sortis entre 2 années données et qui ont un acteur/actrice donné parmi les acteurs
    @Query("SELECT DISTINCT f.nom AS film_nom, f.anneeSortie " +
            "FROM Film f " +
            "JOIN RoleFilm r ON f.filmId = r.film.filmId " +
            "JOIN Acteur a ON r.acteur.acteurId = a.acteurId " +
            "WHERE f.anneeSortie BETWEEN :startYear AND :endYear " +
            "AND a.acteurId = :acteurId")
    List<Object[]> findFilmsBetweenYearsAndByActeur(@Param("startYear") Integer startYear,
                                                    @Param("endYear") Integer endYear,
                                                    @Param("acteurId") Integer acteurId);

}
