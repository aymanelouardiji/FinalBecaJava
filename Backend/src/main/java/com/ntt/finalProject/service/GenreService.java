package com.ntt.finalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ntt.finalProject.model.Genre;
import com.ntt.finalProject.repository.GenreRepository;

@Service
public class GenreService extends AbstractService<Genre, Long> {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    protected JpaRepository<Genre, Long> getRepository() {
        return genreRepository;
    }

}
