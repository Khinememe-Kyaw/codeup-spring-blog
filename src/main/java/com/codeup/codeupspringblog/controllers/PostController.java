package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable int number) {
        return number + " plus one is " + (number + 1) + "!";
    }
//    post index page
    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    @ResponseBody
    public String postsIndex() {
        return "This is the posts index page";
    }
//    View an individual post
    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String postsId(@PathVariable int id){
        return "Viewing page for id : " + id;
    }
//    view the form for creating a post
    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String viewCreate(){
        return "View the form for creating a post";
    }
//    create a new post
    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String create() {
        return "Create a new post";
    }
}
