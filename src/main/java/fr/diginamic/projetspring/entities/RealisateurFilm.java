/**
 * Cette classe représente l'entité RealisateurFilm, qui associe un réalisateur à un film avec un rôle spécifique.
 * La liaison entre le réalisateur et le film est établie à l'aide des annotations JPA pour la persistance des données.
 *
 * @version 1.0
 * @since 2023-12-06
 */
package fr.diginamic.projetspring.entities;

import jakarta.persistence.*;

/**
 * Entité JPA représentant la relation entre un réalisateur et un film avec un rôle spécifique.
 */
@Entity
@Table(name = "realisateur_film")
public class RealisateurFilm {

    /**
     * Identifiant unique de l'association réalisateur-film.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRealisateurFilm;

    /**
     * Réalisateur associé à l'entité RealisateurFilm.
     */
    @ManyToOne
    @JoinColumn(name = "realisateur_id", referencedColumnName = "idRealisateur")
    private Realisateur realisateur;

    /**
     * Identifiant du réalisateur (utilisé pour la lecture seulement, non insérable ni modifiable).
     */
    @Column(name = "realisateur_id", insertable = false, updatable = false)
    private Integer idRealisateur;

    /**
     * Film associé à l'entité RealisateurFilm.
     */
    @ManyToOne()
    @JoinColumn(name = "film_id", referencedColumnName = "filmId")
    private Film film;

    /**
     * Identifiant du film (utilisé pour la lecture seulement, non insérable ni modifiable).
     */
    @Column(name = "film_id", insertable = false, updatable = false)
    private Integer filmId;

    /**
     * Constructeur par défaut de l'entité RealisateurFilm.
     */
    public RealisateurFilm() {
    }

    /**
     * Constructeur avec paramètres de l'entité RealisateurFilm.
     *
     * @param idRealisateurFilm Identifiant unique de l'association réalisateur-film.
     * @param realisateur      Réalisateur associé à l'entité RealisateurFilm.
     * @param film             Film associé à l'entité RealisateurFilm.
     */
    public RealisateurFilm(Integer idRealisateurFilm, Realisateur realisateur, Film film) {
        this.idRealisateurFilm = idRealisateurFilm;
        this.idRealisateur = (realisateur != null) ? realisateur.getIdRealisateur() : null;
        this.filmId = (film != null) ? film.getFilmId() : null;
    }

    // Getters et Setters

    /**
     * Retourne l'identifiant unique de l'association réalisateur-film.
     *
     * @return L'identifiant unique de l'association réalisateur-film.
     */
    public Integer getIdRealisateurFilm() {
        return idRealisateurFilm;
    }

    /**
     * Modifie l'identifiant unique de l'association réalisateur-film.
     *
     * @param idRealisateurFilm Le nouvel identifiant unique de l'association réalisateur-film.
     */
    public void setIdRealisateurFilm(Integer idRealisateurFilm) {
        this.idRealisateurFilm = idRealisateurFilm;
    }

    /**
     * Retourne le réalisateur associé à l'entité RealisateurFilm.
     *
     * @return Le réalisateur associé à l'entité RealisateurFilm.
     */
    public Realisateur getRealisateur() {
        return realisateur;
    }

    /**
     * Modifie le réalisateur associé à l'entité RealisateurFilm.
     *
     * @param realisateur Le nouveau réalisateur associé à l'entité RealisateurFilm.
     */
    public void setRealisateur(Realisateur realisateur) {
        this.realisateur = realisateur;
    }

    /**
     * Retourne l'identifiant du réalisateur (utilisé pour la lecture seulement).
     *
     * @return L'identifiant du réalisateur.
     */
    public Integer getIdRealisateur() {
        return idRealisateur;
    }

    /**
     * Modifie l'identifiant du réalisateur (utilisé pour la lecture seulement).
     *
     * @param idRealisateur Le nouvel identifiant du réalisateur.
     */
    public void setIdRealisateur(Integer idRealisateur) {
        this.idRealisateur = idRealisateur;
    }

    /**
     * Retourne le film associé à l'entité RealisateurFilm.
     *
     * @return Le film associé à l'entité RealisateurFilm.
     */
    public Film getFilm() {
        return film;
    }

    /**
     * Modifie le film associé à l'entité RealisateurFilm.
     *
     * @param film Le nouveau film associé à l'entité RealisateurFilm.
     */
    public void setFilm(Film film) {
        this.film = film;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RealisateurFilm{" + "idRealisateurFilm= " + idRealisateurFilm;
    }
}
