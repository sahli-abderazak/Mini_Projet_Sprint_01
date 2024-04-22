package com.abderazak.film.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.abderazak.film.entities.Film;

public interface FilmService {
	Film saveFilm(Film f);
	Film updateFilm(Film f);
	void deleteFilm(Film f);
	 void deleteFilmById(Long id);
	 Film getFilm(Long id);
	List<Film> getAllFilms();
	Page<Film> getAllFilmsParPage(int page, int size);
}
