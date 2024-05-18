package ma.xproce.bshop.web;

import lombok.RequiredArgsConstructor;
import ma.xproce.bshop.dao.entities.UserM;
import ma.xproce.bshop.dao.repositories.UserRepository;
import ma.xproce.bshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor

public class UserController {
    @Autowired
    private UserRepository userRepository;

    private final UserService userService;

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        UserM userM=new UserM();
        model.addAttribute("user",userM);
        return "login";
    }


    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserM());
        return "register";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute UserM user) {
        userService.register(user);
        return "redirect:/login?success";
    }




}