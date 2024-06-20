package vn.thaihoc.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import vn.thaihoc.laptopshop.domain.User;
import vn.thaihoc.laptopshop.repository.UserRepository;
import vn.thaihoc.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.ModelAttribute;
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
        model.addAttribute("newUser", new User());
        return "/admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create1", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User thaihoc) {
        System.out.println("run here" + thaihoc);
        this.userService.handleSaveUser(thaihoc);
        return "hello";
    }
}
