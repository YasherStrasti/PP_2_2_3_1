package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;
import java.util.List;

@Controller
@RequestMapping
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/")
    public String getStartPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/users")
    public String getUserPage(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", userService.showUser(id));
        return "showUser";
    }

    @GetMapping("/new")
    public String getNewUserPage(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping("/edit")
    public String getEditUserPage(Model model, @RequestParam("id") long id) {
        model.addAttribute("user", userService.showUser(id));
        return "editUser";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam(value = "id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("id") long id) {
        userService.updateUser(id, user);
        return "redirect:/users";
    }
}
