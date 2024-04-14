package fr.diginamic.projetspring;


import fr.diginamic.projetspring.entities.*;
import fr.diginamic.projetspring.repositories.ActeurRepository;
import fr.diginamic.projetspring.repositories.FilmRepository;
import fr.diginamic.projetspring.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@SpringBootApplication
public class TraitementFichierApplication implements CommandLineRunner {

    @Autowired
    private ActeurService acteurService;
    @Autowired
    private RealisateurService realisateurService;
    @Autowired
    private RoleFilmService roleFilmService;
    @Autowired
    private FilmService filmService;
    @Autowired
    private RealisateurFilmService realisateurFilmService;
    @Autowired
    private GenreService genreService;

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(TraitementFichierApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = app.run();
        TraitementFichierApplication traitementFichierApplication = context.getBean(TraitementFichierApplication.class);
        traitementFichierApplication.run();
    }
    Set<Genre> convertGenres(String genresString) {
        Set<Genre> genres = new HashSet<>();
        String[] genreTypes = genresString.split(",");

        for (String genreType : genreTypes) {
            Genre genre = genreService.findOrCreateGenreByType(genreType.trim());
            genres.add(genre);
        }

        return genres;
    }

    /* Alimentation de la base de données à partir de fichiers CSV */
    @Override
    public void run(String... args) throws Exception {



        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d yyyy");
        // Création de Set pour identifer les Id unique
        Set<String> uniqueActeurIds = new HashSet<>();
        Set<String> uniqueFilmIds = new HashSet<>();
        Set<String> uniqueRealisateursIds = new HashSet<>();
        Set<String> uniqueRealisateursFilmsIds = new HashSet<>();
        Set<String> uniqueRoleFilmIds = new HashSet<>();


        /** Import du fichier acteurs.csv */
      Path pathActeurs = Paths.get("C:/dev-java/acteurs.csv");
        try {
            List<String> rowsActeurs = Files.readAllLines(pathActeurs);
            rowsActeurs.remove(0);
            for (String rowActeur : rowsActeurs) {
                System.out.println(rowActeur);
                String[] elements = rowActeur.split(";");
                String idIMDB = elements[0].trim();
                // Vérification si l'ID existe déjà
                if (!uniqueActeurIds.contains(idIMDB)) {
                    Acteur acteurs = new Acteur();
                    acteurs.setIdIMDB(idIMDB);
                    acteurs.setNom(elements[1]);
                    try {
                        Date dateNaissance = sdf.parse(elements[2]);
                        acteurs.setDateNaissance(dateNaissance);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    acteurs.setLieuNaissance(elements[3]);
                    acteurs.setUrlProfile(elements[5]);
                    try {
                        // Tentative d'enregistrement de acteurs
                        acteurService.createActeur(acteurs);
                        // Addition de l'ID dans le set d'ID uniques
                        uniqueActeurIds.add(idIMDB);
                    } catch (DataIntegrityViolationException e) {
                        System.out.println("Duplicate ID: " + idIMDB);
                    }
                } else {
                    System.out.println("Duplicate ID: " + idIMDB);
                }
            }
            System.out.println("Unique IDs Set: " + uniqueActeurIds);
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*  Import du fichier films.csv */
       Path pathFilms = Paths.get("C:/dev-java/films.csv");
        List<String> rowFilms = Files.readAllLines(pathFilms);
        rowFilms.remove(0);
        for (String rowFilm : rowFilms) {
            System.out.println(rowFilm);
            String[] elements = rowFilm.split(";");
            if (elements.length < 10) {
                System.out.println("Invalid data: " + rowFilm);
                continue;
            }
            String idIMDB = elements[0].trim();
            // Vérification si l'ID existe déjà
            if (!uniqueFilmIds.contains(idIMDB)) {
                Film films = new Film();
                films.setIdIMDB(idIMDB);
                films.setNom(elements[1]);

                // Handle anneeSortie
                if (elements.length >= 3) {
                    try {
                        films.setAnneeSortie(Integer.valueOf(elements[2]));
                    } catch (NumberFormatException e) {
                        System.out.println("Error converting film data: " + rowFilm);
                        e.printStackTrace();
                        continue;
                    }
                } else {
                    System.out.println("Année de sortie manquante");
                }
                // Handle resume
                if (elements.length >= 8) {
                    String resume = elements[8];
                    if (resume.length() > 255) {
                        resume = resume.substring(0, 255);
                    }
                    films.setResume(resume);
                } else {
                    films.setResume("");
                }
                films.setRating(elements[3]);
                films.setUrlProfile(elements[4]);
                films.setLieuTournage(elements[5]);

                // genres
                if (elements.length >= 7) {
                    String genresString = elements[6];
                    Set<Genre> genres = convertGenres(genresString);
                    films.setGenres(genres);
                }

                films.setLangue(elements[7]);
                films.setPays(elements[9]);
                try {
                    // Tentative sauvergade Entité Film
                    filmService.createFilm(films);
                    // Addition de l'ID dans le set d'ID uniques
                    uniqueFilmIds.add(idIMDB);
                } catch (DataIntegrityViolationException e) {
                    System.out.println("Duplicate ID: " + idIMDB);
                }
            } else {
                System.out.println("Duplicate ID: " + idIMDB);
            }
        }

        /* Import du fichier realisateurs.csv */
       Path pathRealisateurs = Paths.get("C:/dev-java/realisateurs.csv");
        List<String> rowRealisateurs = Files.readAllLines(pathRealisateurs);
        rowRealisateurs.remove(0);
        for (String rowRealisateur : rowRealisateurs) {
            System.out.println(rowRealisateur);
            String[] elements = rowRealisateur.split(";");
            String idIMDB = elements[0].trim();
            if (!uniqueRealisateursIds.contains(idIMDB)) {
                Realisateur realisateurs = new Realisateur();
                realisateurs.setIdIMDB(idIMDB);
                realisateurs.setNom(elements[1]);
                try {
                    Date dateNaissance = sdf.parse(elements[2]);
                    realisateurs.setDateNaissance(dateNaissance);
                } catch (ParseException e) {
                }
                realisateurs.setLieuNaissance(elements[3]);
                realisateurs.setUrlProfile(elements[4]);
                try {
                    realisateurService.createRealisateur(realisateurs);
                    uniqueRealisateursIds.add(idIMDB);
                } catch (DataIntegrityViolationException e) {
                    System.out.println("Duplicate ID: " + idIMDB);
                }
            } else {
                System.out.println("Duplicate ID: " + idIMDB);
            }
        }


        /** Import du fichier roles.csv */
        Path pathRoleFilm = Paths.get("C:/dev-java/roles.csv");
        List<String> rowRoleFilm = Files.readAllLines(pathRoleFilm);
        rowRoleFilm.remove(0);

        for (String rowRoleFilms : rowRoleFilm) {
            System.out.println(rowRoleFilms);
            String[] elements = rowRoleFilms.split(";");
            if (elements.length >= 3) {
                String acteurIdIMDB = elements[1].trim();
                String filmIdIMDB = elements[0].trim();
                String roleId = acteurIdIMDB + "_" + filmIdIMDB;
                if (!uniqueRoleFilmIds.contains(roleId)) {
                    Acteur acteur = acteurService.findByIdIMDB(acteurIdIMDB);
                    Film film = filmService.findByIdIMDB(filmIdIMDB);
                    if (acteur != null && film != null) {
                        RoleFilm role = new RoleFilm();
                        role.setActeur(acteur);
                        role.setFilm(film);
                        role.setPersonnage(elements[2]);
                        roleFilmService.createRoleFilm(role);
                        uniqueRoleFilmIds.add(roleId);
                    } else {
                        System.out.println("Invalid Acteur or Film ID");
                    }
                } else {
                    System.out.println("Duplicate Role ID: " + roleId);
                }
            } else {
                System.out.println("Insufficient elements in the CSV row");
            }
        }
        System.out.println("Unique Role IDs Set: " + uniqueRoleFilmIds);



        /** Import du fichier film_realisateurs.csv */
        Path pathRealisateurFilm = Paths.get("C:/dev-java/film_realisateurs.csv");
        List<String> rowRealisateurFilm = Files.readAllLines(pathRealisateurFilm);
        rowRealisateurFilm.remove(0);

        Set<String> uniqueRealisateurFilmIds = new HashSet<>(); // Set to track unique RealisateurFilm IDs
        for (String rowRealisateurFilms : rowRealisateurFilm) {
            System.out.println(rowRealisateurFilms);
            String[] elements = rowRealisateurFilms.split(";");

            String filmIdIMDB = elements[0].trim();
            String realisateurIdIMDB = elements[1].trim();

            String realisateurFilmId = realisateurIdIMDB + "_" + filmIdIMDB;
            if (!uniqueRealisateurFilmIds.contains(realisateurFilmId)) {
                Realisateur realisateur = realisateurService.findByIdIMDB(realisateurIdIMDB);
                Film film = filmService.findByIdIMDB(filmIdIMDB);

                if (realisateur != null && film != null) {

                    RealisateurFilm realisateurFilm = new RealisateurFilm();
                    realisateurFilm.setRealisateur(realisateur);
                    realisateurFilm.setFilm(film);
                    realisateurFilmService.createRealisateurFilm(realisateurFilm);

                    uniqueRealisateurFilmIds.add(realisateurFilmId);
                } else {
                    System.out.println("Invalid Realisateur or Film ID");
                }
            } else {
                System.out.println("Duplicate RealisateurFilm ID: " + realisateurFilmId);
            }
        }
        System.out.println("Unique RealisateurFilm IDs Set: " + uniqueRealisateurFilmIds);
        }
}



