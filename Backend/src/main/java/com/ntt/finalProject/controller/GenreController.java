package com.ntt.finalProject.controller;

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

import com.ntt.finalProject.model.Customers;
import com.ntt.finalProject.model.Film;
import com.ntt.finalProject.model.Genre;
import com.ntt.finalProject.model.Media;
import com.ntt.finalProject.model.Salle;
import com.ntt.finalProject.service.GenreService;
import com.ntt.finalProject.service.SalleService;
import com.ntt.finalProject.util.FileUploadUtil;

@Controller
@RequestMapping("genre")
public class GenreController {
	
	private GenreService genreService;
	
	
	@Autowired
	public void setGenreService(GenreService genreService) {
		this.genreService = genreService;
	}

	@GetMapping(value="")
	public String index() {
		return "redirect:/genre/1";
	}

	@GetMapping(value = "/{pageNumber}")
	public String list(@PathVariable Integer pageNumber, Model model) {
		Page<Genre> page = genreService.getList(pageNumber);
        System.out.println("**************************\nTaille de la page : "+page.getNumber());
		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());

		model.addAttribute("listGenres", page);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);

		return "genre/listGenre";

	}
	
	
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("genre", new Genre());
		
		/*
		 * model.addAttribute("listPersonnes",
		 * personneService.getListAll().stream().filter( p ->
		 * p.getTypePersonne().equals(TypePersonne.REALISATEUR)));
		 */
		model.addAttribute("listeGenres", genreService.getListAll());
		return "genre/form";

	}
	
	@PostMapping(value = "/save")
    public String save(Genre genre, final RedirectAttributes ra) {

        Genre save = genreService.save(genre);
        ra.addFlashAttribute("successFlash", "Genre foi salvo com sucesso.");
        return "redirect:/genre";

    }
	
	@GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        model.addAttribute("genre", genreService.get(id));
        return "genre/form";

    }
	
	@GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        genreService.delete(id);
        return "redirect:/genre";

    }

//	@GetMapping("/edit/{id}")
//	public String edit(@PathVariable Long id, Model model) {
//		model.addAttribute("genre", genreService.get(id));
//		model.addAttribute("listeGenres", genreService.getListAll());
//		model.addAttribute("listPersonnes", personneService.getListAll());
//		model.addAttribute("listeNationalites", natService.getListAll());
//		return "film/form";
//
//	}
//
//	@PostMapping(value = "/save")
//	public String save(@RequestParam("file") MultipartFile file, Film film, final RedirectAttributes ra) {
//		// check if is there a file
//		if (!file.isEmpty()) {
//			// normalize the file path
//			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//			// save the file on the local file system
//			try {
//				String uuid = UUID.randomUUID().toString();
//				String uploadDir = UPLOAD_DIR;
//				FileUploadUtil.saveFile(uploadDir, uuid + fileName, file);
//				Media media = new Media();
//				media.setMedia("/photos/films/" + uuid + fileName);
//				media.setFilm(film);
//				mediaService.save(media);
//			} catch (IOException e) {
//				System.out.println("#####\nUpload Error:\n" + e);
//				e.printStackTrace();
//			}
//		}
//
//		Film save = filmService.save(film);
//		ra.addFlashAttribute("success", save + " : Film Ajouté avec succès");
//		return "redirect:/film";
//	}
//
//	@PostMapping(value = "/addActors")
//	public String addActors(Film film, Model model, final RedirectAttributes ra) {
//		Film filmToUpdate = filmService.get(film.getId());
//		try {
//			filmToUpdate.setActeurs(film.getActeurs());
//		} catch (Exception e) {
//			System.out.println("#########\nAdd Actors Error:\n" + e);
//			e.printStackTrace();
//		}
//		Film save = filmService.save(filmToUpdate);
//		model.addAttribute("film", save);
//		model.addAttribute("listPersonnes", personneService.getListAll());
//
//		ra.addFlashAttribute("success", " Acteurs Ajoutés avec succès dans " + save);
//		return "film/details";
//	}
//
//	@GetMapping("/delete/{id}")
//	public String delete(@PathVariable Long id) {
//
//		filmService.delete(id);
//		return "redirect:/film";
//
//	}
//
//	@GetMapping("/details/{id}")
//	public String showDetails(@PathVariable Long id, Model model) {
//		model.addAttribute("film", filmService.get(id));
//		model.addAttribute("listPersonnes", personneService.getListAll());
//		return "film/details";
//	}
//
//	@GetMapping("/show/list")
//	public String showPersons() {
//		return "/film/listNG";
//	}

	@GetMapping(path = "/NG/listp", produces = "application/json")
	public @ResponseBody List<Genre> getAllGenres() {
		List<Genre> allGenres = new ArrayList<Genre>();
		allGenres = genreService.getListAll();
		System.out.println("Size of List allGenres : " + allGenres.size());
		return allGenres;
	}
	
	

}
