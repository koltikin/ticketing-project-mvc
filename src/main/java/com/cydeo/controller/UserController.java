package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
     private final RoleService roleService;
     private final UserService userService;
    @GetMapping("/create")
    public String userCreate(Model model){
        model.addAttribute("user",new UserDTO());
        model.addAttribute("roles",roleService.findAll());
        model.addAttribute("userList",userService.findAll());
        return "user/create";
    }
    @PostMapping("/save")
    public String userSave(@ModelAttribute("user") UserDTO user){
        userService.save(user);
        return "redirect:/user/create";
    }

    @GetMapping("/delete/{userName}")
    public String userDelete(@PathVariable("userName") String username){
        userService.deleteById(username);
        return "redirect:/user/create";
    }
    @GetMapping("/update/{userName}")
    public String userUpdate(@PathVariable("userName") String username, Model model){

        model.addAttribute("user",userService.findById(username));
        model.addAttribute("roles",roleService.findAll());
        model.addAttribute("userList",userService.findAll());

        return "/user/update";
    }

    @PostMapping("/update-save")
    public String userUpdate(@ModelAttribute("user") UserDTO user){

        userService.update(user);

        return "redirect:/user/create";
    }


}
