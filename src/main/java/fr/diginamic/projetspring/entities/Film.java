package fr.diginamic.projetspring.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Représente un film avec ses caractéristiques et les personnes impliquées dans sa création.
 */
@Entity
@Table(name = "films")
public class Film {

    /**
     * Identifiant unique du film.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer filmId;

    /**
     * Nom du film.
     */
    private String nom;
    /**
     * identifiant IMDB du film
     */
    @Column(unique = true)
    private String idIMDB;
    /**
     * Date de sortie du film.
     */
    private Integer anneeSortie;
    private String rating;
    private String urlProfile;
    /**
     * Lieu de tournage du film.
     */
    private String lieuTournage;
    /**
     * Langue du film.
     */
    private String langue;
    /**
     * Résumé du film.
     */
    private String resume;
    /**
     * Pays d'origine du film.
     */
    private String pays;

    /**
     * Réalisateur du film.
     */
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RealisateurFilm> realisateurFilms;

    /** Liste des rôles que l'acteur a joués dans des films. */
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoleFilm> rolefilm;

    @ManyToMany
    @JoinTable(name = "film_genre",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    // Constructeurs

    /**
     * Constructeur par défaut.
     */
    public Film() {
    }

    public Film(Integer anneeSortie, String langue, String lieuTournage, String nom, String pays, String rating, String resume, String urlProfile, Set genres) {
        this.anneeSortie = anneeSortie;
        this.langue = langue;
        this.lieuTournage = lieuTournage;
        this.nom = nom;
        this.pays = pays;
        this.rating = rating;
        this.resume = resume;
        this.urlProfile = urlProfile;
        this.genres = genres;
    }


    // Getters et setters

    /**
     * Obtient l'identifiant unique du film.
     *
     * @return L'identifiant unique du film.
     */
    public Integer getFilmId() {
        return filmId;
    }

    /**
     * Définit l'identifiant unique du film.
     *
     * @param filmId L'identifiant unique du film.
     */
    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    /**
     * Obtient l'identifiant IMDB du film
     *
     * @return l'identifiant IMDB du film
     */
    public String getIdIMDB() {
        return idIMDB;
    }

    /**
     * Définit l'identifiant du film.
     *
     * @param idIMDB Le nom du film.
     */
    public void setIdIMDB(String idIMDB) {
        this.idIMDB = idIMDB;
    }

    /**
     * Obtient le nom du film.
     *
     * @return Le nom du film.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du film.
     *
     * @param nom Le nom du film.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient la date de sortie du film.
     *
     * @return La date de sortie du film.
     */
    public Integer getAnneeSortie() {
        return anneeSortie;
    }

    /**
     * Définit la date de sortie du film.
     *
     * @param anneeSortie La date de sortie du film.
     */
    public void setAnneeSortie(Integer anneeSortie) {
        this.anneeSortie = anneeSortie;
    }


    /**
     * Obtient la note du film.
     *
     * @return La note du film.
     */
    public String getRating() {
        return rating;
    }

    /**
     * Définit la note du film.
     *
     * @param rating La note du film.
     */
    public void setRating(String rating) {
        this.rating = rating;
    }


    /**
     * Obtient l'URL du profil du film.
     *
     * @return L'URL du profil du film.
     */
    public String getUrlProfile() {
        return urlProfile;
    }

    /**
     * Définit l'URL du profil du film.
     *
     * @param urlProfile L'URL du profil du film.
     */
    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    /**
     * Obtient le lieu de tournage du film.
     *
     * @return Le lieu de tournage du film.
     */
    public String getLieuTournage() {
        return lieuTournage;
    }

    /**
     * Définit le lieu de tournage du film.
     *
     * @param lieuTournage Le lieu de tournage du film.
     */
    public void setLieuTournage(String lieuTournage) {
        this.lieuTournage = lieuTournage;
    }


    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    /**
     * Obtient la langue du film.
     *
     * @return La langue du film.
     */
    public String getLangue() {
        return langue;
    }

    /**
     * Définit la langue du film.
     *
     * @param langue La langue du film.
     */
    public void setLangue(String langue) {
        this.langue = langue;
    }

    /**
     * Obtient le résumé du film.
     *
     * @return Le résumé du film.
     */
    public String getResume() {
        return resume;
    }

    /**
     * Définit le résumé du film.
     *
     * @param resume Le résumé du film.
     */
    public void setResume(String resume) {
        this.resume = resume;
    }

    /**
     * Obtient le pays d'origine du film.
     *
     * @return Le pays d'origine du film.
     */
    public String getPays() {
        return pays;
    }

    /**
     * Définit le pays d'origine du film.
     *
     * @param pays Le pays d'origine du film.
     */
    public void setPays(String pays) {
        this.pays = pays;
    }





    /**
     * Obtient la liste des acteurs qui ont joué dans le film.
     *
     * @return La liste des acteurs du film.
     */

    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", idIMDB='" + idIMDB + '\'' +
                ", anneeSortie=" + anneeSortie +
                ", langue='" + langue + '\'' +
                ", lieuTournage='" + lieuTournage + '\'' +
                ", nom='" + nom + '\'' +
                ", pays='" + pays + '\'' +
                ", rating=" + rating +
                ", resume='" + resume + '\'' +
                ", urlProfile='" + urlProfile + '\'' +
                ", genre='" + genres + '\'';
    }
}