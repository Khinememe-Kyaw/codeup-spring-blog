package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.models.UserWithRoles;
import com.codeup.codeupspringblog.Repositories.PostRepository;
import com.codeup.codeupspringblog.Repositories.UserRepository;
import com.codeup.codeupspringblog.service.EmailService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class PostController{

    private final EmailService emailService;

    private final PostRepository postDao;


    public PostController(PostRepository postDao, EmailService emailService){
        this.emailService = emailService;
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String index(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/create")
    public String showPostCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String CreatePost(
            @RequestParam(name="title") String title,
            @RequestParam(name="body") String body){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = new Post(title, body, user);
        postDao.save(post);

        emailService.sendPostEmail(post, "Here's your post", "Post body");
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}")
    public String postsView(@PathVariable long id, Model model) {
        Optional<Post> optionalPost = postDao.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "posts/show";
        } else {
            // Handle case where post with given ID is not found
            return "error-page"; // Replace with the appropriate error page
        }
    }


    @GetMapping("/posts/{id}/edit")
    public String editOnePost(@PathVariable long id, Model model) {
        Post post = postDao.findPostById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UserWithRoles) {
            UserWithRoles authenticatedUserWithRoles = (UserWithRoles) authentication.getPrincipal();
            User authenticatedUser = authenticatedUserWithRoles;

            if (post.getUser().getId() == authenticatedUser.getId()) {
                model.addAttribute("post", post);
                return "posts/edit";
            }else{
                return "redirect:/post/" + id;
            }
        }

        return "redirect:/posts/" + id;
    }

    @PostMapping("/posts/{id}/edit")
    public String submitOnePost(@PathVariable long id, @ModelAttribute Post post) {
        Post postToUpdate = postDao.findPostById(id);
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (postToUpdate.getUser().getId() == authenticatedUser.getId()){
            postToUpdate.setTitle(post.getTitle());
            postToUpdate.setBody(post.getBody());
            postDao.save(postToUpdate);
        }else{
            System.out.println(postToUpdate.getUser());
            System.out.println(authenticatedUser);
        }
        return "redirect:/posts/" + id;
    }
}