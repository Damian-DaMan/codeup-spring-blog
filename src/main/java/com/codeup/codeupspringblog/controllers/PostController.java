package com.codeup.codeupspringblog.controllers;


import com.codeup.codeupspringblog.models.BlogPost;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.BlogPostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {
private BlogPostRepository blogPostDao;
private UserRepository userDao;

    public PostController(BlogPostRepository blogPostDao, UserRepository userDao) {
        this.blogPostDao = blogPostDao;
        this.userDao = userDao;
    }

//    private List<BlogPost> blogPostsList = new ArrayList<>(Arrays.asList(
//            new BlogPost("A Day in the Life", "Yadda yadda yadda yaa..."),
//            new BlogPost("Another Day in the Life", "Adda yadda yadda yadda yaa..."),
//            new BlogPost("Yet Another Day in the Life", "Wadda adda yadda yadda yadda yaa...")
//    ));

    @GetMapping
    public String indexPage(Model vModel) {
        vModel.addAttribute("blogposts", blogPostDao.findAll());
        return "posts/index";
    }

    @GetMapping("/{id}")
    public String viewIndividualPost(@PathVariable long id, Model vModel) {
        if (blogPostDao.existById(id)) {
            BlogPost post = blogPostDao.findbyId(id).get();
            vModel.addAttribute("blogpost", post);
            return "posts/show";
        }
        return "redirect:/posts";
    }

    @GetMapping("/create")
    public String showCreatePostView() {
        return "posts/create";
    }

    @PostMapping("/create")
    public String createPost(
            @RequestParam("title") String title,
            @RequestParam("body") String body
    ) {
        // Hard Coded user for the exercise
        User hardCodedUser = userDao.findById(2L).get();
        BlogPost newPost = new BlogPost(String title, String body);
        blogPostDao.save(newPost);
        return "redirect:/posts";
    }

}