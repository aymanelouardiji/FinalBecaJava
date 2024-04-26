package com.ntt.finalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ntt.finalProject.model.Film;
import com.ntt.finalProject.repository.FilmRepository;

@Service
public class FilmService extends AbstractService<Film, Long> {

    @Autowired
    private FilmRepository filmRepository;

    @Override
    protected JpaRepository<Film, Long> getRepository() {
        return filmRepository;
    }

}
