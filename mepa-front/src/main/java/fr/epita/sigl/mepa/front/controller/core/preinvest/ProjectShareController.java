package fr.epita.sigl.mepa.front.controller.core.preinvest;

import fr.epita.sigl.mepa.core.domain.NewsletterModel;
import fr.epita.sigl.mepa.core.domain.Project;
import fr.epita.sigl.mepa.core.service.NewsletterService;
import fr.epita.sigl.mepa.core.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by claebo_c on 26/07/16.
 */
@Controller
@RequestMapping("/core/preinvest/projectShare") // The adress of the component
@SessionAttributes({})
public class ProjectShareController {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectShareController.class);

    protected static final String NEWPROJECT= "newProject";
    protected static final String PROJECT_ATTRIBUTE = "project";
    protected static final String FACEBOOK = "facebookAllowed";
    protected static final String TWITTER = "twitterAllowed";


    @Autowired
    private ProjectDisplayController projectDisplayController;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private NewsletterService newsletterService;


    public String display(Long projectId, ModelMap modelMap)
    {
        Project p = projectService.getProjectById(projectId);
        modelMap.addAttribute(PROJECT_ATTRIBUTE, p);
        modelMap.addAttribute(NEWPROJECT, projectId);
        return "/preinvest/projectShare";
    }


    @RequestMapping(value = {"/save/{projectId}"}, method = RequestMethod.POST) // The adress to call the function
    public String save(@PathVariable long projectId, ModelMap model, HttpServletRequest request)
    {
        Project project = projectService.getProjectById(projectId);

        project.setFacebookAllowed("true" == request.getParameter("facebookAllowed"));
        project.setTwitterAllowed("true" == request.getParameter("twitterAllowed"));

        //*prosper
        NewsletterModel new_newsletermodel = new NewsletterModel();
        new_newsletermodel.setProjectid(project.getId());
        ArrayList<String> email_list = new ArrayList<>();
        new_newsletermodel.setEmails(email_list);
        new_newsletermodel.setLike_(0);
        new_newsletermodel.setProject_name(project.getName());
        this.newsletterService.createNewsletter(new_newsletermodel);



        projectService.updateProject(project);
        model.addAttribute(PROJECT_ATTRIBUTE, project);

        return projectDisplayController.projectList(model);
    }





}
