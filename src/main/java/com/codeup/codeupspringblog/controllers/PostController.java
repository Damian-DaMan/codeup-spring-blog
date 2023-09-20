package com.codeup.codeupspringblog.controllers;


import com.codeup.codeupspringblog.models.BlogPost;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.BlogPostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    private BlogPostRepository blogPostDao;
    private UserRepository userDao;


    public PostController(BlogPostRepository blogPostDao, UserRepository userDao) {
        this.blogPostDao = blogPostDao;
        this.userDao = userDao;
    }

    @GetMapping("")
    public String indexPage(Model model) {
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
    public String createPost(@ModelAttribute BlogPost blogPost) {
        User user = userDao.findById(1L).get();
        BlogPost postToSave = new BlogPost(
                blogPost.getTitle(),
                blogPost.getBody(),
                user
        );
        {
            blogPostDao.save(postToSave);
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