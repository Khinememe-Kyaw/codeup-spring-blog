package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.Repositories.PostRepository;
import com.codeup.codeupspringblog.Repositories.UserRepository;
import com.codeup.codeupspringblog.services.Authorization;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.*;

import java.util.Optional;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/posts")
public class PostController {
    private PostRepository postDao;
    private UserRepository userDao;
    private EmailService emailService;

    @GetMapping("/posts")
    public String posts(Model model){

        User loggedInUser = Authorization.getLoggedInUser();
        model.addAttribute("loggedInUser", loggedInUser);

        List<Post> posts = postDao.findAll();

        model.addAttribute("posts",posts);
        return "posts/index";
    }

    @GetMapping("/posts/show/{id}")
    public String showSinglePost(@PathVariable Long id, Model model){
        User loggedInUser = Authorization.getLoggedInUser();
        model.addAttribute("loggedInUser", loggedInUser);
        Optional<Post> optionalPost = postDao.findById(id);
        if(optionalPost.isEmpty()) {
            System.out.printf("Post with id " + id + " not found!");
            return "home";
        }

        model.addAttribute("post", optionalPost.get());
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showCreate(Model model) {
        User loggedInUser = Authorization.getLoggedInUser();
        if(loggedInUser.getId() == 0) {
            return "redirect:/login";
        }

        model.addAttribute("loggedInUser", loggedInUser);

        model.addAttribute("newPost", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String doCreate(@RequestParam String title, @RequestParam String body) {
        User loggedInUser = Authorization.getLoggedInUser();
        if(loggedInUser.getId() == 0) {
            return "redirect:/login";
        }

        Post post = new Post();
        post.setCreator(loggedInUser);
        postDao.save(post);

        return "redirect:/posts";
    }
    @GetMapping("posts/edit/{id}")
    public String showEdit(@PathVariable Long id, Model model) {
        User loggedInUser = Authorization.getLoggedInUser();
        model.addAttribute("loggedInUser", loggedInUser);

        Post postToEdit = postDao.getReferenceById(id);
        model.addAttribute("newPost", postToEdit);
        return "/posts/create";
    }
//    @GetMapping("/posts/edit/{id}")
//    public String createEdit(@PathVariable long id, Model model) {
//        Optional<Post> optionalPost = Optional.ofNullable(postDao.findById(id));
//
//        if (optionalPost.isPresent()) {
//            Post post = optionalPost.get();
//            model.addAttribute("post", post);
//            return "posts/edit";
//        } else {
//            // Handle the case where the post is not found
//            // You might redirect or show an error message
//            return "redirect:/posts"; // For example, redirect back to the posts list
//        }
//    }

//    @PostMapping("/posts/edit/{id}")
//    public String editPost(@ModelAttribute Post editedPost){
//        Post existingPost = postDao.findById(editedPost.getId()).orElseThrow(() -> new IllegalArgumentException("Post not found"));
//        existingPost.setTitle(editedPost.getTitle());
//        existingPost.setBody(editedPost.getBody());
//
//        postDao.save(existingPost);
//
//        return "redirect:/posts";
//    }

}

