package vn.thaihoc.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import vn.thaihoc.laptopshop.domain.User;
import vn.thaihoc.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomepage(Model model) {
        List<User> arrUsers = this.userService.getAllUsersByEmail("thaihoc131005@gmail.com");
        System.out.println(arrUsers);
        model.addAttribute("test", "test");
        model.addAttribute("test2", "from controller with model");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users1", users);
        return "/admin/user/table-users";
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        model.addAttribute("newUser", new User());
        return "/admin/user/create";
    }

    @RequestMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "/admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User thaihoc) {
        System.out.println("run here" + thaihoc);
        this.userService.handleSaveUser(thaihoc);
        return "redirect:/admin/user";
    }
}
