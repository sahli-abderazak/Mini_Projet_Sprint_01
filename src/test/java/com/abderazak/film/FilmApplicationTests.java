package com.abderazak.film;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.abderazak.film.entities.Film;
import com.abderazak.film.repos.FilmRepository;
import com.abderazak.film.service.FilmService;

@SpringBootTest
class FilmApplicationTests {

	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private FilmService filmService;

	@Test
	public void testCreateFilm() {
		Film film = new Film("aka", 2.500, new Date());
		filmRepository.save(film);
	}

	@Test
	public void testFindFilm() {
		Film f = filmRepository.findById(1L).get();
		System.out.println(f);
	}

	@Test
	public void testUpdateFilm() {
		Film f = filmRepository.findById(1L).get();
		f.setRateFilm(1.0);
		filmRepository.save(f);
	}

	@Test
	public void testDeleteFilm() {
		filmRepository.deleteById(1L);;
	}

	@Test
	public void testListerTousFilms() {
		List<Film> prods = filmRepository.findAll();
		for (Film p : prods) {
			System.out.println(p);
		}
	}
	@Test
	public void testFindByNomFilmContains()
	{
	Page<Film> film = filmService.getAllFilmsParPage(0,2);
	System.out.println(film.getSize());
	System.out.println(film.getTotalElements());
	System.out.println(film.getTotalPages());
	film.getContent().forEach(f -> {System.out.println(f.toString());
	 });
	/*ou bien
	for (Produit p : prods)
	{
	System.out.println(p);
	} */
	}

}
