package fr.epita.sigl.mepa.core.service.impl;


import fr.epita.sigl.mepa.core.dao.ModelDao;
import fr.epita.sigl.mepa.core.dao.ProjectDao;
import fr.epita.sigl.mepa.core.domain.Model;
import fr.epita.sigl.mepa.core.domain.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceImpTests {

    @Mock
    ProjectDao mockedProjectDao;

    @InjectMocks
    ProjectServiceImpl projectService;

    @Test
    public void createProject_check_start_date() {
        // Given
        Project projectToCreate = new Project();
        Date now = new Date();
        long deltaInMilliseconds = 500;

        // When
        projectService.createProject(projectToCreate);

        // Then
        assertThat(projectToCreate.getStartDate()).isCloseTo(now, deltaInMilliseconds);
    }

    @Test
    public void createModel_ShouldCreateANewModel_UsingModelDao() {
        // Given
        Project projectToCreate = new Project();

        // When
        projectService.createProject(projectToCreate);

        // Then
        verify(mockedProjectDao).create(projectToCreate);
    }


}

