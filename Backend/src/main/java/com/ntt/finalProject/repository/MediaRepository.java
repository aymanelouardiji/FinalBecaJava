package com.ntt.finalProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ntt.finalProject.dto.InlineGenre;
import com.ntt.finalProject.dto.InlineMedia;
import com.ntt.finalProject.model.Media;


@CrossOrigin("http://localhost:4200")
@Repository
@RepositoryRestResource(excerptProjection = InlineMedia.class)
public interface MediaRepository extends JpaRepository<Media, Long> {

}
