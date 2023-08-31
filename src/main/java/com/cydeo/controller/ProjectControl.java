package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
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

    @GetMapping("/delete/{projectCode}")
    public String projectDelete(@PathVariable("projectCode") String projectCode){

        projectService.deleteById(projectCode);

        return "redirect:/project/create";
    }

    @GetMapping("/update/{projectCode}")
    public String projectUpdate(@PathVariable("projectCode") String projectCode,Model model){

        model.addAttribute("project",projectService.findById(projectCode));
        model.addAttribute("projectList", projectService.findAll());
        model.addAttribute("managers", userService.findManagers());

        return "/project/update";
    }


    @PostMapping("/update/{projectStatus}")
    public String projectUpdateSave(@ModelAttribute("project") ProjectDTO project){

        projectService.update(project);

        return "redirect:/project/create";
    }

    @GetMapping("/manager/project-status")
    public String projectStatus(Model model){

        UserDTO manager = userService.findById("john@cydeo.com");

        model.addAttribute("projects", projectService.findCountedProjectsByManager(manager));


        return "/manager/project-status";
    }

}
