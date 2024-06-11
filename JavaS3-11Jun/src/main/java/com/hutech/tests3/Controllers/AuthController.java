package com.hutech.tests3.Controllers;

import com.hutech.tests3.Entities.User;
import com.hutech.tests3.RequestEntities.RegisterUser;
import com.hutech.tests3.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String Register(Model model){
        RegisterUser registerUser = new RegisterUser();
        model.addAttribute("registerUser", registerUser);
        return "Layout/Auth/register";
    }
    @PostMapping("/register_submit")
    public String SubmitRegister(RegisterUser registerUser){
        User user = userService.userRegister(registerUser);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String Login(){
        return "Layout/Auth/login";
    }
}
