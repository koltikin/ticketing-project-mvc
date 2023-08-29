package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("project")
public class ProjectControl {

    private  final ProjectService projectService;
    private final UserService userService;

    @GetMapping("/create")
    public String projectCreate(Model model){
        model.addAttribute("project",new ProjectDTO());
        model.addAttribute("projectList", projectService.findAll());
        model.addAttribute("managers", userService.findManagers());

        return "/project/create";
    }

    @PostMapping("/create")
    public String projectCreateSave(@ModelAttribute("project") ProjectDTO project, Model model){
        projectService.save(project);


        return "redirect:/project/create";
    }


    @GetMapping("/complete/{projectCode}")
    public String projectComplete(@PathVariable("projectCode") String projectCode){

        projectService.projectComplete(projectCode);

        return "redirect:/project/create";
    }

}
