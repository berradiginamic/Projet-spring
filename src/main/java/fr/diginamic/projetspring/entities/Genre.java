package fr.diginamic.projetspring.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Représente un genre de film avec son type et la liste des films associés à ce genre.
 */
@Entity
@Table(name="genres")
public class Genre {
    /** Identifiant unique du genre. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer genreId;

    /** Type du genre (ex: Action, Comédie, Drame, etc.). */
    @Column(name="type")
    private String type;

    @ManyToMany(mappedBy = "genres")
    private Set<Film> films = new HashSet<>();

    // Constructeurs
    /**
     * Constructeur par défaut.
     */
    public Genre() {
    }

    /**
     * Constructeur avec type de genre.
     *
     * @param type Le type de genre (ex: Action, Comédie, Drame, etc.).
     */
    public Genre(String type) {
        this.type = type;
    }

    // Getters et Setters

    /**
     * Obtient l'identifiant unique du genre.
     *
     * @return L'identifiant unique du genre.
     */
    public Integer getGenreId() {
        return genreId;
    }

    /**
     * Définit l'identifiant unique du genre.
     *
     * @param genreId L'identifiant unique du genre.
     */
    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    /**
     * Obtient le type de genre.
     *
     * @return Le type de genre.
     */
    public String getType() {
        return type;
    }

    /**
     * Définit le type de genre.
     *
     * @param type Le type de genre.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Obtient la liste des films associés à ce genre.
     *
     * @return La liste des films associés à ce genre.
     */
    // Getters and Setters



    public String toString()

    {
        return

                "Genre{" +
                        "genreId=" + genreId +
                        ", nom='" + type + '\'' +
                        '}';
    }
}
