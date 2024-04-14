package fr.diginamic.projetspring.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Représente un acteur participant à des films.
 */
@Entity
public class Acteur {

    /** Identifiant unique de l'acteur. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer acteurId;

    /** Nom de l'acteur. */
    @Column(unique = true)
    private String idIMDB;
    private String nom;
    // Indique le mapping de 'Date' au type SQL 'DATE' coté BDD
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private String lieuNaissance;
    private String urlProfile;

    /** Liste des rôles que l'acteur a joués dans des films. */
    @OneToMany(mappedBy = "acteur")
    private List<RoleFilm> rolefilm;

    // Constructeurs
    /**
     * Constructeur par défaut.
     */
    public Acteur() {
    }

    /**
     * Constructeur avec nom, prénom et film dans lequel l'acteur a joué.
     *
     * @param nom    Nom de l'acteur.
     *  film   Film dans lequel l'acteur a joué.
     */
    public Acteur(String acteurIdStr, String lieuNaissance, Date dateNaissance, String nom, String urlProfile) {
        this.acteurId = Integer.parseInt(acteurIdStr); // Convert acteurId to integer
        this.lieuNaissance = lieuNaissance;
        this.dateNaissance = dateNaissance;
        this.nom = nom;
        this.urlProfile = urlProfile;
    }

    // Getters et setters

    /**
     * Obtient l'identifiant unique de l'acteur dans notre base de données.
     *
     * @return L'identifiant unique de l'acteur dans notre base de données.
     */
    public Integer getActeurId() {
        return acteurId;
    }

    /**
     * Définit l'identifiant unique de l'acteur dans notre base de données.
     *
     * @param acteurId L'identifiant unique de l'acteur dans notre base de données.
     */
    public void setActeurId(Integer acteurId) {
        this.acteurId = acteurId;
    }

    /**
     * Obtient l'identifiant imdb de l'acteur.
     *
     * @return L'identifiant unique de l'acteur.
     */
    public String getIdIMDB() {
        return idIMDB;
    }

    /**
     * Définit l'identifiant imdb de l'acteur.
     *
     * @param idIMDB L'identifiant imdb de l'acteur
     */
    public void setIdIMDB(String idIMDB) {
        this.idIMDB = idIMDB;
    }

    /**
     * Obtient le nom de l'acteur.
     *
     * @return Le nom de l'acteur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de l'acteur.
     *
     * @param nom Le nom de l'acteur.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getLieuNaissance() {
        return lieuNaissance;
    }
    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getUrlProfile() {
        return urlProfile;
    }
    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    /**
     * Obtient le film dans lequel l'acteur a joué.
     *
     * @return Le film dans lequel l'acteur a joué.
     */


    /**
     * Définit le film dans lequel l'acteur a joué.
     *
     *  Le film dans lequel l'acteur a joué.
     */


    /**
     * Obtient la date de naissance de l'acteur.
     *
     * @return La date de naissance de l'acteur.
     */
    public Date getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Définit la date de naissance de l'acteur.
     *
     * @param dateNaissance La date de naissance de l'acteur.
     */
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Obtient la liste des rôles que l'acteur a joués dans des films.
     *
     * @return La liste des rôles que l'acteur a joués.
     */
    public List<RoleFilm> getRolefilm() {
        return rolefilm;
    }
    public void setRolefilm(List<RoleFilm> rolefilm) {
        this.rolefilm = rolefilm;
    }

    // Autres méthodes
    // (Ajoutez ici d'autres méthodes pertinentes avec des commentaires JavaDoc)

    @Override
    public String toString() {
        return "Acteur{" +
                "acteurId=" + acteurId +
                ", lieuNaissance='" + lieuNaissance + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", nom='" + nom + '\'' +
                ", urlProfile='" + urlProfile + '\'' +
                ", roles=" + rolefilm +
                '}';
    }
}