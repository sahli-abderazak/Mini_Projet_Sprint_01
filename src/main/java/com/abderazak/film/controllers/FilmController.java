package com.abderazak.film.controllers;

import org.springframework.stereotype.Controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abderazak.film.entities.Film;
import com.abderazak.film.service.FilmService;

@Controller
public class FilmController {
	@Autowired
	FilmService filmService;

	@RequestMapping("/ListeFilms")
	public String listeFilms(ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size) 
	{
		Page<Film> film = filmService.getAllFilmsParPage(page, size);
		modelMap.addAttribute("films", film);
		modelMap.addAttribute("pages", new int[film.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "listeFilms";
	}

	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createFilm";
	}

	@RequestMapping("/saveFilm")
	public String saveFilm(@ModelAttribute("film") Film film, @RequestParam("date") String date, ModelMap modelMap)
			throws ParseException {
		// conversion de la date
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateSortie = dateformat.parse(String.valueOf(date));
		film.setDateSortie(dateSortie);

		Film saveFilm = filmService.saveFilm(film);
		String msg = "film enregistré avec Id " + saveFilm.getIdFilm();
		modelMap.addAttribute("msg", msg);
		return "createFilm";
	}

	@RequestMapping("/supprimerFilm")
	public String supprimerFilm(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size
			) {
		filmService.deleteFilmById(id);
		
		
		Page<Film> film = filmService.getAllFilmsParPage(page,size);
		modelMap.addAttribute("films", film);
		modelMap.addAttribute("pages", new int[film.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "listeFilms";
	}

	@RequestMapping("/modifierFilm")
	public String editerFilm(@RequestParam("id") Long id, ModelMap modelMap) {
		Film f = filmService.getFilm(id);
		modelMap.addAttribute("film", f);
		return "editerFilm";
	}

	@RequestMapping("/updateFilm")
	public String updateFilm(@ModelAttribute("film") Film film, @RequestParam("date") String date, ModelMap modelMap)
			throws ParseException {
//conversion de la date
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateSortie = dateformat.parse(String.valueOf(date));
		film.setDateSortie(dateSortie);

		filmService.updateFilm(film);
		List<Film> film1 = filmService.getAllFilms();
		modelMap.addAttribute("films", film1);
		return "listeProduits";
	}

}
