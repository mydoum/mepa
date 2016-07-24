package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.Investment;
import fr.epita.sigl.mepa.core.domain.Project;

import java.util.List;

public interface ProjectDao {

    void create(Project project);

    void update(Project project);

    void delete(Project project);

    Project getById(Long id);

    List<Project> getAll();

    public List<Project> getAllUnfinished();
}
