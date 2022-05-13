package io.waysoning.ppmtool.services;

import io.waysoning.ppmtool.domain.Backlog;
import io.waysoning.ppmtool.domain.ProjectTask;
import io.waysoning.ppmtool.repositories.BacklogRepository;
import io.waysoning.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {

    public static final Integer DEFAULT_PROJECT_TASK_PRIORITY = 3;
    public static final String DEFAULT_PROJECT_TASK_STATUS = "TO_DO";

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier ,ProjectTask projectTask) {

        // PTs to be added to a specific project, project != null, Backlog exists
        // set the BL to the project

        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        projectTask.setBacklog(backlog);
        backlog.setPTSequence(backlog.getPTSequence() + 1);
        projectTask.setProjectSequence(projectIdentifier+"-"+backlog.getPTSequence());
        projectTask.setProjectIdentifier(projectIdentifier);

        if(projectTask.getPriority() == null || projectTask.getPriority() == 0) {
            projectTask.setPriority(DEFAULT_PROJECT_TASK_PRIORITY);
        }

        if(projectTask.getStatus() == null || "".equals(projectTask.getStatus())) {
            projectTask.setStatus(DEFAULT_PROJECT_TASK_STATUS);
        }

        return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask> findBacklogById(String backlogId) {
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlogId);
    }
}
