package fr.epita.sigl.mepa.front.controller.core.preinvest;

import fr.epita.sigl.mepa.core.domain.Model;
import fr.epita.sigl.mepa.core.domain.Project;
import fr.epita.sigl.mepa.core.service.ModelService;
import fr.epita.sigl.mepa.core.service.ProjectService;
import fr.epita.sigl.mepa.front.model.example.AddCustomModelFormBean;
import org.apache.commons.lang3.RandomStringUtils;
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
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/preinvest") // The adress of the component
@SessionAttributes({})
public class ProjectDisplayController {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectDisplayController.class);

    protected static final String PROJECT_ATTRIBUTE = "project";

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = {"/projectDisplay"}) // The adress to call the function
    public String showForm(HttpServletRequest request, ModelMap modelMap) {
        /* Code your logic here */
        Project newProject = new Project((long) 0, "Yolo", new Date());
        this.projectService.createModel(newProject);


        Project project = this.projectService.getModelById((long) 1);

        modelMap.addAttribute(PROJECT_ATTRIBUTE, project);
        return "/preinvest/projectDisplay"; // The adress of the JSP coded in tiles.xml
    }
}
