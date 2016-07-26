package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.ModelDao;
import fr.epita.sigl.mepa.core.dao.ProjectDao;
import fr.epita.sigl.mepa.core.domain.Model;
import fr.epita.sigl.mepa.core.domain.Project;
import fr.epita.sigl.mepa.core.domain.Reward;
import fr.epita.sigl.mepa.core.service.ModelService;
import fr.epita.sigl.mepa.core.service.ProjectService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Override
    public void createProject(Project project) {
        this.projectDao.create(project);
    }

    @Override
    public void updateProject(Project project) {
        this.projectDao.update(project);
    }

    @Override
    public void deleteProject(Project project) {
        this.projectDao.delete(project);
    }

    @Override
    @Transactional(readOnly = true)
    public Project getProjectById(Long id) {
        return this.projectDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Project> getAllProjects() {

        return this.projectDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Project> getAllUnfinishedProjects() {
        return this.projectDao.getAllUnfinished();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Project> getAllFinishedProjects() {
        return this.projectDao.getAllFinished();
    }
}