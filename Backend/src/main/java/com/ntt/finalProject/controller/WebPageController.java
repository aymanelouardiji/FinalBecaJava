package com.ntt.finalProject.controller;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ntt.finalProject.service.FilmService;
import com.ntt.finalProject.service.PersonneService;
import com.ntt.finalProject.service.SalleService;
import com.ntt.finalProject.service.SeanceService;

@Controller
public class WebPageController {
	
	private FilmService filmService;
	private SalleService salleService;
	private PersonneService personneService;
	private SeanceService seanceService;
	
	@Autowired
	public void setFilmService(FilmService filmService) {
		this.filmService = filmService;
	}
	
	@Autowired
	public void setSeanceService(SeanceService seanceService) {
		this.seanceService = seanceService;
	}

	@Autowired
	public void setPersonneService(PersonneService personneService) {
		this.personneService = personneService;
	}
	
	@Autowired
	public void setSalleService(SalleService salleService) {
		this.salleService = salleService;
	}
	
	
    @GetMapping("/wb")
    public String index(Model model) throws ParseException{
    	Integer nbFilms, nbPersonnes, nbSalles, nbSeances;
    	nbFilms = filmService.getListAll().size();
    	nbPersonnes = personneService.getListAll().size();
    	nbSalles = salleService.getListAll().size();
    	nbSeances = seanceService.getSeancesParDate(new Date()).size();
    	;
    	
    	model.addAttribute("nbFilms", nbFilms);
		model.addAttribute("nbPersonnes", nbPersonnes);
		model.addAttribute("nbSalles", nbSalles);
		model.addAttribute("nbSeances", nbSeances);
        return "dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }
}
