package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import org.springframework.stereotype.Service;

@Service
public interface TaskService extends CrudService<TaskDTO,Long> {

    int findUnfinishedTaskCount(ProjectDTO project);
    int findCompletedTaskCount(ProjectDTO project);

}
