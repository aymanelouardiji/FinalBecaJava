package com.ntt.finalProject.service;

import com.ntt.finalProject.model.Comment;

import com.ntt.finalProject.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentsService extends AbstractService<Comment, Long>{

    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    protected JpaRepository<Comment, Long> getRepository() {
        return commentsRepository;
    }

}
