package io.waysoning.ppmtool.services;

import io.waysoning.ppmtool.domain.Project;
import io.waysoning.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        // logic to save or update project
        return projectRepository.save(project);
    }
}
