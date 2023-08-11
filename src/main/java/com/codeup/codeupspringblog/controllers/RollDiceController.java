//package com.codeup.codeupspringblog.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//
//
//@Controller
//public class RollDiceController {
//    @GetMapping("/roll-dice")
//    public String pickANumber() {
//        return "roll-dice/index";
//    }
//    @GetMapping("/roll-dice/{number}")
//    public String showResult(@PathVariable int number, Model model) {
//        int result = (int) (Math.random()*6) +1;
//        System.out.println((int) 4.9);
//        model.addAttribute("number", number);
//        model.addAttribute("result", result);
//        return "roll-dice/show";
//    }
//}
//
//




