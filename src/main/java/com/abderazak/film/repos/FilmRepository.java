package com.abderazak.film.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abderazak.film.entities.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {

}
