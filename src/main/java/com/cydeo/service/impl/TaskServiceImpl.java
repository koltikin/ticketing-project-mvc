package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO,Long> implements TaskService {
    @Override
    public TaskDTO save(TaskDTO task) {

        if (task.getTaskStatus()==null) task.setTaskStatus(Status.OPEN);
        if (task.getAssignedDate() == null) task.setAssignedDate(LocalDate.now());
        if (task.getId()==null) task.setId(UUID.randomUUID().getMostSignificantBits());

        return super.save(task,task.getId());
    }

    @Override
    public TaskDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }

    @Override
    public void update(TaskDTO task) {
        super.update(task,task.getId());
    }

    @Override
    public int findUnfinishedTaskCount(ProjectDTO project) {
        return (int) findAll().stream().filter(tk->tk.getProject().equals(project))
                .filter(tk->!tk.getTaskStatus().equals(Status.COMPLETE)).count();
    }

    @Override
    public int findCompletedTaskCount(ProjectDTO project) {
        return (int) findAll().stream().filter(tk->tk.getProject().equals(project))
                .count()-findUnfinishedTaskCount(project);
    }

    @Override
    public List<TaskDTO> findNotCompletedTasks() {
        return findAll().stream().filter(tk->!tk.getTaskStatus().equals(Status.COMPLETE))
                .collect(Collectors.toList());
    }

    public List<TaskDTO> findCompletedTasks() {
        return findAll().stream().filter(tk->tk.getTaskStatus().equals(Status.COMPLETE))
                .collect(Collectors.toList());
    }

    @Override
    public void taskStatusUpdate(TaskDTO task) {
        findById(task.getId()).setTaskStatus(task.getTaskStatus());
        super.update(findById(task.getId()), task.getId());
    }
}
