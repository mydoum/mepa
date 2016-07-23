package fr.epita.sigl.mepa.front.controller.core.preinvest;

import fr.epita.sigl.mepa.core.domain.Investment;
import fr.epita.sigl.mepa.core.domain.Project;
import fr.epita.sigl.mepa.core.service.InvestmentService;

import fr.epita.sigl.mepa.core.domain.CommentsModel;
import fr.epita.sigl.mepa.core.domain.Project;
import fr.epita.sigl.mepa.core.service.CommentsModelService;

import fr.epita.sigl.mepa.core.service.ProjectService;
import fr.epita.sigl.mepa.front.model.investment.Investor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    /*PostInvest Total Amount invested on Project*/
    protected static final String PROJECT_TOTAL_AMOUNT = "totalProjectAmountInvested";

    @Autowired
    private ProjectService projectService;

    @Autowired
    private InvestmentService investmentService;

    @Autowired
    private CommentsModelService commentsModelService;


    @RequestMapping(value = {"/projectDisplay/{projectId}"}) // The adress to call the function
    public String projectDisplay(HttpServletRequest request, ModelMap modelMap, @PathVariable long projectId) {
        /* Code your logic here */
        Project project = this.projectService.getProjectById(projectId);
        modelMap.addAttribute(PROJECT_ATTRIBUTE, project);

        /*PostInvest Total Amount invested on Project*/
        Float totalProjectAmountInvested = getProjectMoneyInvested(projectId);
        modelMap.addAttribute(PROJECT_TOTAL_AMOUNT, totalProjectAmountInvested);
        /*\PostInvest Total Amount invested on Project*/

        List<CommentsModel> new_c_models = this.commentsModelService.getAllCommentsModels();
        modelMap.addAttribute("new_c_models",new_c_models);

        return "/preinvest/projectDisplay"; // The adress of the JSP coded in tiles.xml
    }

    @RequestMapping(value = {"/", "/projectList"}) // The adress to call the function
    public String projectList(HttpServletRequest request, ModelMap modelMap) {
        List<Project> projects = this.projectService.getAllProjects();

        modelMap.addAttribute(PROJECTS_LIST_ATTRIBUTE, projects);
        return "/preinvest/projectList"; // The adress of the JSP coded in tiles.xml
    }

    @RequestMapping(value = {"/projectListInclude"}) // The adress to call the function
    public String projectListInclude(HttpServletRequest request, ModelMap modelMap) {
        /* Code your logic here */

        this.projectList(request, modelMap);
        return "/preinvest/projectListInclude"; // The adress of the JSP coded in tiles.xml
    }

    /*PostInvest Total Amount invested on Project*/
    public Float getProjectMoneyInvested(long projectId) {
        ArrayList<Investment> investments = new ArrayList<Investment>(investmentService.getAllInvestments());
        Float totalProjectAmount = 0.0f;
        for (Investment inv : investments)
            if (inv.getProjectId() == projectId)
                totalProjectAmount += inv.getAmount();
        return totalProjectAmount;
    }
    /*\PostInvest Total Amount invested on Project*/
}
