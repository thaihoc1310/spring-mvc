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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
        User user = this.userService.getUserById(id);
        model.addAttribute("id", id);
        model.addAttribute("user", user);
        return "/admin/user/user-inf";
    }

    @RequestMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "/admin/user/create-user";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String postcreateUser(Model model, @ModelAttribute("newUser") User thaihoc) {
        // System.out.println("run here" + thaihoc);
        this.userService.handleSaveUser(thaihoc);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        return "/admin/user/update-user";
    }

    @PostMapping("/admin/user/update")
    public String postUpdateUserDetail(Model model, @ModelAttribute("user") User thaihoc) {
        User userUpdate = this.userService.getUserById(thaihoc.getId());
        if (!userUpdate.equals(null)) {
            userUpdate.setFullName(thaihoc.getFullName());
            userUpdate.setPhone(thaihoc.getPhone());
            userUpdate.setAddress(thaihoc.getAddress());
            this.userService.handleSaveUser(userUpdate);
        }

        // neu save luon se mat email
        // this.userService.handleSaveUser(thaihoc);
        return "redirect:/admin/user";
    }
}
