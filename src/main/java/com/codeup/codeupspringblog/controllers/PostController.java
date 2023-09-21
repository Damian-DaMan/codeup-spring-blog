package com.codeup.codeupspringblog.controllers;


import com.codeup.codeupspringblog.models.BlogPost;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.BlogPostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    private BlogPostRepository blogPostDao;
    private UserRepository userDao;
    private final EmailService emailService;


    public PostController(BlogPostRepository blogPostDao, UserRepository userDao, EmailService emailService) {
        this.blogPostDao = blogPostDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("")
    public String indexPage(Model model) {
        System.out.println("hi");
//        Model.addAttribute("blogposts", blogPostDao.findAll());
        List<BlogPost> blogPosts = blogPostDao.findAll();
        model.addAttribute("blogPosts", blogPosts);
        return "posts/index";
    }

    @GetMapping("/{id}")
    public String viewIndividualPost(@PathVariable long id, Model model) {
        if (blogPostDao.existsById(id)) {
            BlogPost post = blogPostDao.findById(id).get();
            model.addAttribute("blogpost", post);
            return "posts/show";
        }
        return "redirect:/posts";
    }

    @GetMapping("/create")
    public String showCreatePostView(Model model) {
        model.addAttribute("blogPost", new BlogPost());
        return "posts/create";
    }

    @PostMapping("/create")
    public String createPost(@CurrentSecurityContext(expression = "authentication?.name") String username, @ModelAttribute BlogPost blogPost) {
        User user = userDao.findByUsername(username);
        BlogPost postToSave = new BlogPost(
                blogPost.getTitle(),
                blogPost.getBody(),
                user
        );
        {
            blogPostDao.save(postToSave);
            emailService.prepareAndSend(postToSave, "New Post", "You have created a new post!");
            return "redirect:/posts";
        }
    }

    @GetMapping("/{id}/edit")
    public String editPost(@PathVariable long id, Model model){
    BlogPost postToEdit = blogPostDao.findById(id).get();
    model.addAttribute("blogPost", postToEdit);
    return "posts/edit";
    }

    @PostMapping("/{id}/edit")
    public String insertEdit(@ModelAttribute BlogPost post, @PathVariable long id) {
        BlogPost postToEdit = blogPostDao.findById(id).get();
        postToEdit.setTitle(post.getTitle());
        postToEdit.setBody(post.getBody());
        blogPostDao.save(postToEdit);
        return "redirect:/posts";
    }

}