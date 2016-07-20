package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Model;
import fr.epita.sigl.mepa.core.domain.Project;

import java.util.List;

public interface ProjectService {

    void createProject(Project project);

    void updateProject(Project project);

    void deleteProject(Project project);

    Project getProjectById(Long id);

    List<Project> getAllProjects();

}