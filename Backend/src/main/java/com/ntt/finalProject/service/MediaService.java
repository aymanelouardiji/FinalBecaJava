package com.ntt.finalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ntt.finalProject.model.Media;
import com.ntt.finalProject.repository.MediaRepository;

@Service
public class MediaService extends AbstractService<Media, Long> {

    @Autowired
    private MediaRepository mediaRepository;

    @Override
    protected JpaRepository<Media, Long> getRepository() {
        return mediaRepository;
    }

}
