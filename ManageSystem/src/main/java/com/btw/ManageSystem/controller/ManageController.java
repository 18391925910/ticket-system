package com.btw.ManageSystem.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManageController {
    @GetMapping("login")
    public String login(Model model){
        model.addAttribute("data","1");
        return "login";
    }

    @GetMapping("main")
    public String main(Model model){
        model.addAttribute("data","1");
        return "main";
    }
}
