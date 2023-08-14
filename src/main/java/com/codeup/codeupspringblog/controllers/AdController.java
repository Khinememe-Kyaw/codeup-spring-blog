//package com.codeup.codeupspringblog.controllers;
//
//import com.codeup.codeupspringblog.Repositories.AdRepository;
//import com.codeup.codeupspringblog.Repositories.AdUserRepository;
//import com.codeup.codeupspringblog.models.AdUser;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import com.codeup.codeupspringblog.models.Ad;
//
//import java.util.Objects;
//
//
//@Controller
//public class AdController {
//
//    private final AdRepository adDao;
//    private final AdUserRepository adUserDao;
//
//    public AdController(AdRepository adDao, AdUserRepository adUserDao) {
//        this.adDao = adDao;
//        this.adUserDao = adUserDao;
//    }
//
//
//    @GetMapping("/ads")
//    public String index(Model model) {
//        model.addAttribute("ads", adDao.findAll());
//        return "ads/index";
//    }
//
//    @GetMapping("/ads/create")
//    public String createAdForm() {
//        return "ads/create";
//    }
//
//    @PostMapping("/ads/create")
//    public String createAdInDB(@RequestParam(name = "title") String title, @RequestParam(name = "description") String description) {
//        AdUser adUser = adUserDao.findUserById(1L);
//        Ad ad = new Ad(title, description);
//        adDao.save(ad);
//        return "redirect:/ads";
//    }
//
//    @GetMapping("ads/{id}")
//    public String showOneAd(@PathVariable long id, Model model) {
//        Ad ad = adDao.findAdById(id);
//        model.addAttribute("ad", Objects.requireNonNullElseGet(ad, () -> new Ad("not found", "could not find that ad")));
//        return "ads/show";
//    }
//
//}