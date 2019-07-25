package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.security.Principal;


@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/register" /*method= RequestMethod.GET*/)
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping(value="/register"/* method=RequestMethod.POST*/)
    public String processRegistrationPage(@Valid
        @ModelAttribute("user") User user, BindingResult result,
                                          Model model){
    model.addAttribute("user", user);
    if(result.hasErrors()){
        return "registration";
    }
    else{
        userService.saveUser(user);
        model.addAttribute("message", "User Account Created");
    }
    return "redirect:/login";
    }

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/home")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/")
    public String secure(Principal principal, Model model){
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "secure";
    }



}

