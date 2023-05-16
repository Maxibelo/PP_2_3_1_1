package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getListUser(ModelMap modelMap) {
        modelMap.addAttribute("list", userService.getAllUsers());
        return "userList";
    }

    @GetMapping(value = "/new")
    public String addUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping(value = "/new")
    public String createUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/user/";
    }

    @GetMapping(value = "/edit/{id}")
    public String editUser(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("user", userService.getById(id));
        return "editUser";
    }

    @PostMapping(value = "/edit/{id}")
    public String updateUser(@ModelAttribute User user) {
        userService.edit(user);
        return "redirect:/user/";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(userService.getById(id));
        return "redirect:/user/";
    }
}
