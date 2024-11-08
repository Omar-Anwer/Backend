package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;



@Controller
@RequestMapping("/signup")
public class SignupController {
    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getSignupPage() {
        return "signup";
    }

    @PostMapping
    public String postSignup(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        
        if(userService.isUsernameAvailable(user.getUsername())){    
            userService.createUser(user);
            redirectAttributes.addFlashAttribute("signupStatus", 1);
            return "redirect:/login";
        }
        else{
            redirectAttributes.addFlashAttribute("signupStatus", 2);
        }
        
        return "signup";
    }    
}
