package com.ntt.finalProject.controller;

import com.ntt.finalProject.model.Film;
import com.ntt.finalProject.model.Media;
import com.ntt.finalProject.model.Personne;
import com.ntt.finalProject.model.Personne.TypePersonne;
import com.ntt.finalProject.service.FilmService;
import com.ntt.finalProject.service.GenreService;
import com.ntt.finalProject.service.MediaService;
import com.ntt.finalProject.service.NationaliteService;
import com.ntt.finalProject.service.PersonneService;
import com.ntt.finalProject.util.FileUploadUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("film")
public class FilmController {

	private FilmService filmService;
	private GenreService genreService;
	private PersonneService personneService;
	private NationaliteService natService;
	private MediaService mediaService;
	private final String UPLOAD_DIR = "/src/main/resources/static/photos/films/";

	@Autowired
	public void setFilmService(FilmService filmService) {
		this.filmService = filmService;
	}

	@Autowired
	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}

	@Autowired
	public void setNatService(NationaliteService natService) {
		this.natService = natService;
	}

	@Autowired
	public void setGenreService(GenreService genreService) {
		this.genreService = genreService;
	}

	@Autowired
	public void setPersonneService(PersonneService personneService) {
		this.personneService = personneService;
	}

	@GetMapping
	public String index() {
		return "redirect:/film/1";
	}

	@GetMapping(value = "/{pageNumber}")
	public String list(@PathVariable Integer pageNumber, Model model) {
		Page<Film> page = filmService.getList(pageNumber);

		System.out.println("Taille de la page : ");
		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());

		model.addAttribute("listFilms", page);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);

		return "film/list";

	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("film", new Film());
		model.addAttribute("listeNationalites", natService.getListAll());
		model.addAttribute("listPersonnes", 
				personneService.getListAll());
		
		  model.addAttribute("listRealisateurs",
		  personneService.getListAll().stream().filter( p ->
		  p.getTypePersonne().equals(TypePersonne.REALISATEUR)));
		  
		  model.addAttribute("listActeurs",
				  personneService.getListAll().stream().filter( p ->
				  p.getTypePersonne().equals(TypePersonne.ACTEUR)));
		 
		model.addAttribute("listeGenres", genreService.getListAll());
		return "film/form";

	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("film", filmService.get(id));
		model.addAttribute("listeGenres", genreService.getListAll());
		model.addAttribute("listPersonnes", personneService.getListAll());
		model.addAttribute("listeNationalites", natService.getListAll());
		return "film/form";

	}

	@PostMapping(value = "/save")
	public String save(@RequestParam("file") MultipartFile file, @RequestParam("acteurs") List<Long> acteurIds, Film film, final RedirectAttributes ra) {
	    // Check if there is a file
	    if (!file.isEmpty()) {
	        // Normalize the file path
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	        // Save the file on the local file system
	        try {
	            String uuid = UUID.randomUUID().toString();
	            String uploadDir = UPLOAD_DIR;
	            FileUploadUtil.saveFile(uploadDir, uuid + fileName, file);
	            Media media = new Media();
	            media.setMedia("/photos/films/" + uuid + fileName);
	            media.setFilm(film);
	            mediaService.save(media);
	        } catch (IOException e) {
	            System.out.println("#####\nUpload Error:\n" + e);
	            e.printStackTrace();
	        }
	    }

	    // Fetch selected actors from the database
	    List<Personne> acteurs = new ArrayList<>();
	    for (Long acteurId : acteurIds) {
	        acteurs.add(personneService.get(acteurId));
	    }
	    film.setActeurs(acteurs);
	    
	    // Save the film
	    Film savedFilm = filmService.save(film);
	    
	    ra.addFlashAttribute("success", savedFilm.getTitre() + " : Film Ajouté avec succès");
	    return "redirect:/film";
	}


	@PostMapping(value = "/addActors")
	public String addActors(Film film, Model model, final RedirectAttributes ra) {
		Film filmToUpdate = filmService.get(film.getId());
		try {
			filmToUpdate.setActeurs(film.getActeurs());
		} catch (Exception e) {
			System.out.println("#########\nAdd Actors Error:\n" + e);
			e.printStackTrace();
		}
		Film save = filmService.save(filmToUpdate);
		model.addAttribute("film", save);
		model.addAttribute("listPersonnes", personneService.getListAll());

		ra.addFlashAttribute("success", " Acteurs Ajoutés avec succès dans " + save);
		return "film/details";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {

		filmService.delete(id);
		return "redirect:/film";

	}

	@GetMapping("/details/{id}")
	public String showDetails(@PathVariable Long id, Model model) {
		model.addAttribute("film", filmService.get(id));
		model.addAttribute("listPersonnes", personneService.getListAll());
		model.addAttribute("listSeances", filmService.getListAll());

		return "film/details";

	}

	@GetMapping("/show/list")
	public String showPersons() {
		return "/film/listNG";
	}

	@GetMapping(path = "/NG/listp", produces = "application/json")
	public @ResponseBody List<Film> getAllFilms() {
		List<Film> allFilms = new ArrayList<Film>();
		allFilms = filmService.getListAll();
		System.out.println("Size of List allPersons : " + allFilms.size());
		return allFilms;
	}
}
