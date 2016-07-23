package fr.epita.sigl.mepa.front.controller.core.preinvest;

/**
 * Created by Xavier on 21/07/2016.
 */
import fr.epita.sigl.mepa.core.dao.impl.ProjectDaoImpl;
import fr.epita.sigl.mepa.core.domain.Model;
import fr.epita.sigl.mepa.core.domain.Project;
import fr.epita.sigl.mepa.core.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/core/preinvest") // The adress of the component
@SessionAttributes({})
public class ProjectCreateController {
    private static final Logger LOG = LoggerFactory.getLogger(ProjectCreateController.class);

    protected static final String PROJECT_ATTRIBUTE = "project";
    protected static final String NEWPROJECT= "newProject";
    protected static final String PROJECTS_LIST_ATTRIBUTE = "project_list";

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = {"/projectCreate"}, method = RequestMethod.GET) // The adress to call the function
    public String projectCreate(ModelMap modelMap) {
        /* Code your logic here */
        Project p = new Project();
        modelMap.addAttribute(NEWPROJECT, p);
        projectService.getProjectById(1L).getEndDate();
        return "/preinvest/projectCreate"; // The adress of the JSP coded in tiles.xml
    }

    @RequestMapping(value = {"/processCreation"}, method = RequestMethod.POST) // The adress to call the function
    public String processCreation(@ModelAttribute(NEWPROJECT) Project newProject, ModelMap model)
    {
        //model.addAttribute("Retour", newProject.getName() + newProject.getEndDate() + newProject.getDescription());
        projectService.createProject(newProject);

        List<Project> projects = this.projectService.getAllProjects();

        model.addAttribute(PROJECTS_LIST_ATTRIBUTE, projects);
        return "/preinvest/projectList";
    }


}
