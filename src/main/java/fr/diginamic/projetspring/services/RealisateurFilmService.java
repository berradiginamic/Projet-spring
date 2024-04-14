/**
 * Cette classe est responsable de la gestion des opérations métier liées à l'entité RealisateurFilm.
 * Elle utilise les repositories et services associés pour effectuer des opérations telles que la création
 * d'une association entre un réalisateur et un film.
 *
 * @version 1.0
 * @since 2023-12-06
 */
package fr.diginamic.projetspring.services;

import fr.diginamic.projetspring.entities.Film;
import fr.diginamic.projetspring.entities.Realisateur;
import fr.diginamic.projetspring.entities.RealisateurFilm;
import fr.diginamic.projetspring.repositories.RealisateurFilmRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Spring gérant les opérations liées à l'entité RealisateurFilm.
 */
@Service
public class RealisateurFilmService {

    @Autowired
    private RealisateurFilmRepository realisateurFilmRepository;

    @Autowired
    private RealisateurService realisateurService;

    @Autowired
    private FilmService filmService;

    /**
     * Crée une association entre un réalisateur et un film et l'enregistre en base de données.
     *
     * @param realisateurFilm L'entité RealisateurFilm à créer.
     * @return L'entité RealisateurFilm créée et enregistrée.
     */
    @Transactional
    public RealisateurFilm createRealisateurFilm(RealisateurFilm realisateurFilm) {
        // Récupération du réalisateur à partir de son identifiant IMDB
        Realisateur realisateur = realisateurService.findByIdIMDB(realisateurFilm.getRealisateur().getIdIMDB());

        // Récupération du film à partir de son identifiant IMDB
        Film film = filmService.findByIdIMDB(realisateurFilm.getFilm().getIdIMDB());

        // Association du réalisateur et du film à l'entité RealisateurFilm
        realisateurFilm.setRealisateur(realisateur);
        realisateurFilm.setFilm(film);

        // Enregistrement de l'entité RealisateurFilm en base de données
        return realisateurFilmRepository.save(realisateurFilm);
    }
}
