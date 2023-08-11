package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.Repositories.PostRepository;
import com.codeup.codeupspringblog.Repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {


    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }


    //  *** OLD mapping ***
//    @GetMapping("/posts")
//    public String postsHome(Model model) {
//        ArrayList<Post> posts = new ArrayList<>();
//        posts.add(new Post(1,"Wow!", "Free Brownies in the quad!"));
//        posts.add(new Post(2,"Uh-oh...", "The brownies betrayed me..."));
//        model.addAttribute("posts", posts);
//        return "posts/index";
//    }

    @GetMapping("/posts")
    public String postsHome(Model model) {
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }


//    *** Old Mapping ***
//    @GetMapping("/posts/{id}")
//    public String postsHome(@PathVariable long id, Model model) {
//        Post post = new Post(id, "Test post", "Why do all these posts look the same?");
//        model.addAttribute("post", post);
//        return "posts/show";
//    }

    @GetMapping("/posts/{id}")
    public String postsHome(@PathVariable long id, Model model) {
        Post post = postDao.findPostById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String postsEdit(@PathVariable long id, Model model){
        Post post = postDao.findPostById(id);
        model.addAttribute("post", post);
        return "post/show";
    }

    @GetMapping("/posts/create")
    public String postsForm(Model model) {
        model.addAttribute("heading", "Create new post.");
        return "posts/create";
    }
    @PostMapping("/posts/save")
    public String createPost(@RequestParam String title, @RequestParam String body) {
        Post post = new Post(title, body);
        User user = userDao.findById(1L).get();
        post.setUser(user);
        postDao.save(post);
        emailService.sendPostEmail(post, "A post has been created", "ok, This is your body" + post.getBody());
        return "redirect:/posts";
    }

}

