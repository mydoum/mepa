package fr.epita.sigl.mepa.front.controller.core.preinvest;

import fr.epita.sigl.mepa.core.domain.Project;
import fr.epita.sigl.mepa.core.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/core/preinvest") // The adress of the component
@SessionAttributes({})
public class ProjectDisplayController {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectDisplayController.class);

    protected static final String PROJECT_ATTRIBUTE = "project";
    protected static final String PROJECTS_LIST_ATTRIBUTE = "project_list";

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = {"/projectDisplay"}) // The adress to call the function
    public String projectDisplay(HttpServletRequest request, ModelMap modelMap) {
        /* Code your logic here */
        Project newProject = new Project((long) 0, "Yolo", new Date());
        ArrayList<String> imageLinkList = new ArrayList<>();
        imageLinkList.add("http://www.gobadges.com/v/vspfiles/photos/CD0564-2.jpg");
        imageLinkList.add("http://www.nyan.cat/images/cat/4.gif");
        newProject.setImagesLinks(imageLinkList);

        this.projectService.createProject(newProject);



        Project project = this.projectService.getProjectById((long) 1);

        modelMap.addAttribute(PROJECT_ATTRIBUTE, project);
        return "/preinvest/projectDisplay"; // The adress of the JSP coded in tiles.xml
    }

    @RequestMapping(value = {"/", "/projectList"}) // The adress to call the function
    public String projectList(HttpServletRequest request, ModelMap modelMap) {
        /* Code your logic here */

        List<Project> projects = this.projectService.getAllProjects();

        modelMap.addAttribute(PROJECTS_LIST_ATTRIBUTE, projects);
        return "/preinvest/projectList"; // The adress of the JSP coded in tiles.xml
    }
}
