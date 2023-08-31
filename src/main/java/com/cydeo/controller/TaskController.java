package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final ProjectService projectService;
    private final UserService userService;
    private final TaskService taskService;

    @GetMapping("/create")
    public String taskCreate(Model model){

        model.addAttribute("task", new TaskDTO());
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("employees", userService.findAll());
        model.addAttribute("tasksList", taskService.findAll());

        return "/task/create";

    }

    @PostMapping("/create")
    public String taskCreateSave(@ModelAttribute("task") TaskDTO task){

        taskService.save(task);

        return "redirect:/task/create";

    }

    @GetMapping("/delete/{id}")
    public String taskDelete(@PathVariable("id") Long id){

        taskService.deleteById(id);
        return "redirect:/task/create";

    }

    @GetMapping("/update/{id}")
    public String taskUpdate(@PathVariable("id") Long id, Model model){

        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("employees", userService.findAll());
        model.addAttribute("tasksList", taskService.findAll());

        return "/task/update";

    }

    @PostMapping("/update/{id}/{taskStatus}/{assignedDate}")
    public String taskUpdateSAve(@ModelAttribute("task") TaskDTO task){

        taskService.update(task);

        return "redirect:/task/create";

    }

    @GetMapping("/pending-tasks")
    public String pendingTasks(Model model){

        model.addAttribute("pendingTasks",taskService.findNotCompletedTasks());

        return "/task/pending-tasks";

    }

    @GetMapping("/task-update/{id}")
    public String taskUpdateStatus(@PathVariable("id") Long id, Model model){

        model.addAttribute("tasks",taskService.findById(id));

        return "/task/status-update";

    }


}
