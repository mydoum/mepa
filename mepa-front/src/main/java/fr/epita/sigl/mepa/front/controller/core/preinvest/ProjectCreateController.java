package fr.epita.sigl.mepa.front.controller.core.preinvest;

/**
 * Created by Xavier on 21/07/2016.
 */
import fr.epita.sigl.mepa.core.domain.AppUser;
import fr.epita.sigl.mepa.core.domain.Project;
import fr.epita.sigl.mepa.core.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/core/preinvest") // The adress of the component
@SessionAttributes({})
public class ProjectCreateController {
    private static final Logger LOG = LoggerFactory.getLogger(ProjectCreateController.class);

    protected static final String PROJECT_ATTRIBUTE = "project";
    protected static final String NEWPROJECT= "newProject";
    protected static final String PROJECTS_LIST_ATTRIBUTE = "project_list";
    protected static final String IS_CONNECTED_ATTRIBUTE = "is_connected";
    protected static final String IS_UNIQUE_ATTRIBUTE = "is_unique";
    protected static final String IS_NULL_ATTRIBUTE = "is_null";
    protected static final String IS_DATE_ATTRIBUTE = "is_date";

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RewardAddController rewardAddController;

    @RequestMapping(value = {"/projectCreate"}, method = RequestMethod.GET) // The adress to call the function
    public String projectCreate(HttpServletRequest request, ModelMap modelMap) {
        /* Code your logic here */
        Project p = new Project(1);

        Boolean is_co = (Boolean) request.getSession().getAttribute("isCo");
        if (is_co == null)
            is_co = false;

        System.out.println("Is Co" + is_co);
        modelMap.addAttribute(NEWPROJECT, p);
        modelMap.addAttribute(IS_CONNECTED_ATTRIBUTE, is_co);

        return "/preinvest/projectCreate"; // The adress of the JSP coded in tiles.xml
    }

    @RequestMapping(value = {"/processCreation"}, method = RequestMethod.POST) // The adress to call the function
    public String processCreation(@ModelAttribute(NEWPROJECT) Project newProject, ModelMap model, HttpServletRequest request)
    {
        //model.addAttribute("Retour", newProject.getName() + newProject.getEndDate() + newProject.getDescription());

        boolean is_unique = false;
        boolean is_null = false;
        boolean is_date = false;
        AppUser connectedUser = (AppUser) request.getSession().getAttribute("userCo");
        newProject.setUser_id(connectedUser.getId());
        newProject.setImagesLinks(new ArrayList<>());
        newProject.getImagesLinks().add(request.getParameter("imageUrl"));
        projectService.createProject(newProject);
        List<Project> projects = this.projectService.getAllProjects();

        if (newProject.getName().equals(""))
        {
            is_null = true;
        }
        if (newProject.getEndDate().before(newProject.getStartDate()))
        {
            is_date =  true;
        }
        int project_name = 0;
        for (int i = 0; i < projects.size() - 1; i++)
        {
            if (projects.get(i).getName().equals(newProject.getName()))
            {
                project_name++;
                if (project_name > 1) {
                    is_unique = true;
                }
            }
        }
        model.addAttribute(IS_UNIQUE_ATTRIBUTE, is_unique);
        model.addAttribute(IS_NULL_ATTRIBUTE, is_null);
        model.addAttribute(IS_DATE_ATTRIBUTE, is_date);
        if (is_date || is_null || is_unique)
        {
            projectService.deleteProject(newProject);
            return this.projectCreate(request, model);
        }
        return this.rewardAddController.display(newProject.getId(), model);
    }

}
