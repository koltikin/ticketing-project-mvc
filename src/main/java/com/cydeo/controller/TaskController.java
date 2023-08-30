package com.cydeo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {

    @GetMapping("/create")
    public String taskCreate(Model model){

        return "/task/create";

    }
}
