package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.Repositories.AdRepository;
import com.codeup.codeupspringblog.Repositories.PostRepository;
import com.codeup.codeupspringblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    private final PostRepository postDao;


    //    post index page
    @GetMapping("/posts")
    public String postsHome(Model model) {
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }


    //    View an individual post
    @GetMapping("/posts/{id}")
    public String postsHome(@PathVariable long id, Model model) {
        Post post = postDao.findPostById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String postsForm(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("heading", "Create new post.");
        return "posts/create";
    }
    @PostMapping("/posts/save")
    public String createPost(@ModelAttribute Post post) {
        Post origPost = postDao.findPostById(post.getId());
        if(origPost == null) {
            postDao.save(post);
        }
        return "redirect:/posts";
    }

}