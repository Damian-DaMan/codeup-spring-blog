package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    boolean existById(long id);

    List<Object> findbyId(long id);
}
