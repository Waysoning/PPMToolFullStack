package io.waysoning.ppmtool.services;

import io.waysoning.ppmtool.domain.Backlog;
import io.waysoning.ppmtool.domain.Project;
import io.waysoning.ppmtool.domain.ProjectTask;
import io.waysoning.ppmtool.exceptions.ProjectNotFoundException;
import io.waysoning.ppmtool.repositories.BacklogRepository;
import io.waysoning.ppmtool.repositories.ProjectRepository;
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

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier ,ProjectTask projectTask) {

        try{

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
        } catch (Exception e) {
            throw new ProjectNotFoundException("Project ID '" + projectIdentifier + "' not found");
        }

    }

    public Iterable<ProjectTask> findBacklogById(String backlogId) {

        Project project = projectRepository.findByProjectIdentifier(backlogId);
        if(project == null) {
            throw new ProjectNotFoundException("Project ID '" + backlogId + "' not found");
        }

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlogId);
    }

    public ProjectTask findProjectTaskByProjectSequence(String backlogId, String projectSequence) {

        Project project = projectRepository.findByProjectIdentifier(backlogId);
        if(project == null) {
            throw new ProjectNotFoundException("Project ID '" + backlogId + "' not found");
        }

        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectSequence);
        if(projectTask == null) {
            throw new ProjectNotFoundException("Project Task '" + projectSequence + "' not found");
        }

        if(!projectTask.getProjectIdentifier().equals(backlogId)) {
            throw new ProjectNotFoundException("Project Task '" + projectSequence + "' does not exist in project: " + backlogId);
        }

        return projectTask;
    }

    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlogId, String projectSequence) {

        // validate project
        findProjectTaskByProjectSequence(backlogId, projectSequence);

        return projectTaskRepository.save(updatedTask);
    }
}
