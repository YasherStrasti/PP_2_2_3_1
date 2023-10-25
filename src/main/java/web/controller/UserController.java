package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import javax.validation.Valid;

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
    public String getEditUserPage(@Valid Model model, @RequestParam("id") long id) {
        model.addAttribute("user", userService.showUser(id));
        return "editUser";
    }
    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "newUser";
        }

        userService.saveUser(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam(value = "id") long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }


    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult,
                             @RequestParam("id") long id) {
        if (bindingResult.hasErrors()) {
            return "editUser";
        }
        userService.updateUser(id, user);
        return "redirect:/";
    }
}
