package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Model;
import fr.epita.sigl.mepa.core.domain.Project;

import java.util.List;

public interface ProjectService {

    void createModel(Project project);

    void updateModel(Project project);

    void deleteModel(Project project);

    Project getModelById(Long id);

    List<Project> getAllModels();

}