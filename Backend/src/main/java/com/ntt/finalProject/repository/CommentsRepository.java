package com.ntt.finalProject.repository;

import com.ntt.finalProject.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin("http://localhost:4200")
@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {
}
