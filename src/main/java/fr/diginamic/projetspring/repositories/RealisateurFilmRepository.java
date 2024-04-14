/**
 * Cette interface définit le repository JPA pour l'entité RealisateurFilm, utilisant Spring Data JPA.
 * Elle fournit des méthodes prédéfinies pour effectuer des opérations de base sur l'entité RealisateurFilm
 * telles que l'ajout, la mise à jour, la suppression et la recherche.
 *
 * @version 1.0
 * @since 2023-12-06
 */
package fr.diginamic.projetspring.repositories;

import fr.diginamic.projetspring.entities.RealisateurFilm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface repository pour l'entité RealisateurFilm, utilisant Spring Data JPA.
 */
public interface RealisateurFilmRepository extends JpaRepository<RealisateurFilm, Integer> {

    // Aucune méthode spécifique n'est définie ici, car JpaRepository fournit déjà des méthodes pour
    // les opérations CRUD de base. Ces méthodes peuvent être utilisées telles quelles ou personnalisées
    // selon les besoins spécifiques de l'application.

}
