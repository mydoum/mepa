package fr.epita.sigl.mepa.front.controller.postinvestment;

import fr.epita.sigl.mepa.core.domain.AppUser;
import fr.epita.sigl.mepa.core.domain.Investment;
import fr.epita.sigl.mepa.core.domain.Project;
import fr.epita.sigl.mepa.core.service.InvestmentService;
import fr.epita.sigl.mepa.core.service.ProjectService;
import fr.epita.sigl.mepa.front.Service.InvestmentFrontService;
import fr.epita.sigl.mepa.front.controller.investment.InvestController;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import fr.epita.sigl.mepa.front.model.investment.Investor;

import static java.lang.Math.toIntExact;

@Controller
@SessionAttributes({})
@RequestMapping("/postinvest")
public class PostInvestmentController {

    protected static final String PROJECT_ATTRIBUTE = "project";
    protected static final String PROJECTS_LIST_ATTRIBUTE = "project_list";
    /*PostInvest Total Amount invested on Project*/
    protected static final String PROJECT_TOTAL_AMOUNT = "totalProjectAmountInvested";

    @Autowired
    private ProjectService projectService;

    @Autowired
    private InvestController investController;

    @Autowired
    private InvestmentService investmentService;

    private InvestmentFrontService InvestmentFrontService = new InvestmentFrontService();


    private static final Logger LOG = LoggerFactory.getLogger(PostInvestmentController.class);

    @RequestMapping(value = "/project-list", method = RequestMethod.GET)
    public String displayEndedProjectList(ModelMap model, HttpServletRequest request) {
        List<Project> projects = this.projectService.getAllFinishedProjects();
        ArrayList<Investor> listinvestors = new ArrayList<Investor>();
        float totalAmount = 0.00f;
        for (Project p : projects) {
            totalAmount = 0.00f;
            totalAmount = investController.getallinvestors(listinvestors, totalAmount, p, false);
            model.addAttribute("investorsList", listinvestors);
            model.addAttribute("totalDonation", totalAmount);
            Hibernate.initialize(p.getRewards());
        }
        model.addAttribute(PROJECTS_LIST_ATTRIBUTE, projects);

        return "/project-end-list";
    }

    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public String displayEndedProject(ModelMap model, HttpServletRequest request, Project project) {
        float totalAmount = 0.00f;
        ArrayList<Investor> listinvestors = new ArrayList<Investor>();
        totalAmount = investController.getallinvestors(listinvestors, totalAmount, project, false);
        model.addAttribute("goalAmount", project.getGoalAmount());
        model.addAttribute("investorsList", listinvestors);
        model.addAttribute("totalDonation", totalAmount);
        return "/project-end";
    }


    @RequestMapping(value = {"/project-end/{projectId}"}) // The adress to call the function
    public String projectDisplay(HttpServletRequest request, ModelMap modelMap, @PathVariable long projectId) {
        /* Code your logic here */
        Project project = this.projectService.getProjectById(projectId);
        modelMap.addAttribute(PROJECT_ATTRIBUTE, project);

        /*PostInvest Total Amount invested on Project*/
        Float totalProjectAmountInvested = getProjectMoneyInvested(projectId);
        modelMap.addAttribute(PROJECT_TOTAL_AMOUNT, totalProjectAmountInvested);
        /*PostInvest Total Amount invested on Project*/

        //int totalAmount = randomWithRange(0, 1000);
        ArrayList<Investor> listinvestors = new ArrayList<Investor>();
        float  totalAmount = 0.00f;
        totalAmount = investController.getallinvestors(listinvestors, totalAmount, project, false);
        modelMap.addAttribute("totalDonationDummy", totalAmount);

        int var = 100 * (int) totalAmount / toIntExact(project.getGoalAmount());
        modelMap.addAttribute("varpercentage", var);


         /*Get the current user in the session in order to know if he is
        * connected */
        AppUser userco = new AppUser();
        userco = (AppUser) request.getSession().getAttribute("userCo");
        modelMap.addAttribute("userco", userco);

        investController.investorsList(modelMap, request, project);
        /*
        List<AppCommentsModel> list = this.commentsModelService.getAllCommentsModels();

        /*Sort of the comments by the arriving tickets*
        List<AppCommentsModel>new_c_models = new ArrayList<AppCommentsModel>();
        ListIterator<AppCommentsModel> i= list.listIterator(list.size());
        while(i.hasPrevious())
        {
            new_c_models.add(i.previous());
        }

        modelMap.addAttribute("new_c_models",new_c_models);
        */
        return "/project-end"; // The adress of the JSP coded in tiles.xml
    }

    public Float getProjectMoneyInvested(long projectId) {
        ArrayList<Investment> investments = new ArrayList<Investment>(investmentService.getAllInvestments());
        Float totalProjectAmount = 0.0f;
        for (Investment inv : investments)
            if (inv.getProjectId() == projectId)
                totalProjectAmount += inv.getAmount();
        return totalProjectAmount;
    }

    public static int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }


    private int percentage(int a, int b) {
        if (a == 0) {
            return 100;
        }
        return ((b * 100) / a);
    }
}
