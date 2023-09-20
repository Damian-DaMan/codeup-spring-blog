package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

}
