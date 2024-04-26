package com.ntt.finalProject.dto;


import com.ntt.finalProject.model.Comment;
import com.ntt.finalProject.model.Film;
import com.ntt.finalProject.model.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "inlineComment", types = { Comment.class })

public interface InlineComment {

    Long getId();

    String content();

    Date dateOfPost();

    Film getFilm();

    User getUser();
}
