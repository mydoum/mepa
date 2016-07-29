package fr.epita.sigl.mepa.front.controller.core.preinvest;

import fr.epita.sigl.mepa.core.domain.*;
import fr.epita.sigl.mepa.core.service.CommentsModelService;
import fr.epita.sigl.mepa.core.service.InvestmentService;
import fr.epita.sigl.mepa.core.service.NewsletterService;
import fr.epita.sigl.mepa.core.service.ProjectService;
import fr.epita.sigl.mepa.front.controller.investment.InvestController;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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

    @Autowired
    private InvestController investController;

    @Autowired
    private NewsletterService newsletterService;


    @RequestMapping(value = {"/projectDisplay/{projectId}"}) // The adress to call the function
    public String projectDisplay(HttpServletRequest request, ModelMap modelMap, @PathVariable long projectId) {
        /* Code your logic here */
        Project project = this.projectService.getProjectById(projectId);
        modelMap.addAttribute(PROJECT_ATTRIBUTE, project);

        /*PostInvest Total Amount invested on Project*/
        Float totalProjectAmountInvested = getProjectMoneyInvested(projectId);
        modelMap.addAttribute(PROJECT_TOTAL_AMOUNT, totalProjectAmountInvested);
        /*\PostInvest Total Amount invested on Project*/

         /*Get the current user in the session in order to know if he is
        * connected */

        AppUser userco = new AppUser();
        userco = (AppUser) request.getSession().getAttribute("userCo");
        modelMap.addAttribute("userco", userco);

        /* Check if the user connected is the administrator of the projet */
        if (userco != null && project != null && ((userco.getId().equals(project.getUser_id()))
                || (userco.getFirstName().compareTo("Admin") == 0)))
            request.getSession().setAttribute("isAdmin", "true");
        else
            request.getSession().setAttribute("isAdmin", "false");

        investController.investorsList(modelMap, request, project);


        int display;
        List<NewsletterModel> newsletterlist = this.newsletterService.getAllNewsletterModels();
        //boolean exist = false;
        for (NewsletterModel i : newsletterlist) {
            if (i.getProjectid() == projectId) {
                if (i.getEmails().contains(userco.getLogin())) {
                    System.out.println("\n\n JE te connais déja niga\n\n");
                    display = 1;
                } else {
                    System.out.println("\n\n JE ne te connais pas niga\n\n");
                    display = 2;
                }
                modelMap.addAttribute("display", display);
            }
        }


        return "/preinvest/projectDisplay";
    }

    @RequestMapping(value = {"/", "/projectList"}) // The adress to call the function
    public String projectList(ModelMap modelMap) {
        List<Project> projects = this.projectService.getAllUnfinishedProjects();
        int i = 0;
        for (Project p: projects)
            Hibernate.initialize(p.getRewards());
        modelMap.addAttribute(PROJECTS_LIST_ATTRIBUTE, projects);
        return "/preinvest/projectList"; // The adress of the JSP coded in tiles.xml
    }

    @RequestMapping(value = {"/projectListInclude"}) // The adress to call the function
    public String projectListInclude(HttpServletRequest request, ModelMap modelMap) {
        /* Code your logic here */

        this.projectList(modelMap);
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

    @RequestMapping(value = {"/projectDisplay/{projectId}/comment"}) // The adress to call the function
    public String projectDisplayComment(HttpServletRequest request, ModelMap modelMap, @PathVariable long projectId) {
        /* Code your logic here */
        Project project = this.projectService.getProjectById(projectId);
        modelMap.addAttribute(PROJECT_ATTRIBUTE, project);

        /*PostInvest Total Amount invested on Project*/
        Float totalProjectAmountInvested = getProjectMoneyInvested(projectId);
        modelMap.addAttribute(PROJECT_TOTAL_AMOUNT, totalProjectAmountInvested);
        /*\PostInvest Total Amount invested on Project*/

         /*Get the current user in the session in order to know if he is
        * connected */
        AppUser userco = new AppUser();
        userco = (AppUser) request.getSession().getAttribute("userCo");
        modelMap.addAttribute("userco", userco);

        /* Check if the user connected is the administrator of the projet */
        if (userco != null && userco.getId() == project.getUser_id())
            request.getSession().setAttribute("isAdmin", "true");

        else
            request.getSession().setAttribute("isAdmin", "false");

        investController.investorsList(modelMap, request, project);

        List<AppCommentsModel> list = this.commentsModelService.getAllCommentsModels();
        /*Sort of the comments by the arriving tickets*/
        List<AppCommentsModel>new_c_models = new ArrayList<AppCommentsModel>();
        ListIterator<AppCommentsModel> a= list.listIterator(list.size());
        while(a.hasPrevious())
        {
            new_c_models.add(a.previous());
        }
        modelMap.addAttribute("new_c_models",new_c_models);

        return "/preinvest/projectDisplay/Comment";

    }

    /***********************************************************************************************************/
    /**[PROSPER]*/
    /**
     * Add of this function in order to catch the user action whith the newsletter checkbox
     */
    @RequestMapping(value = {"/projectDisplay/newsletter/{projectId}"})
    public String projectNewsletter(HttpServletRequest request, @PathVariable Long projectId, ModelMap modelMap) {
        /* Code your logic here */
        Project project = this.projectService.getProjectById(projectId);
        modelMap.addAttribute(PROJECT_ATTRIBUTE, project);

        /*PostInvest Total Amount invested on Project*/
        Float totalProjectAmountInvested = getProjectMoneyInvested(projectId);
        modelMap.addAttribute(PROJECT_TOTAL_AMOUNT, totalProjectAmountInvested);
        /*\PostInvest Total Amount invested on Project*/


        /**[PROSPER]*/
        /** Get the current user and mapping him in the current project page*/
        AppUser userco = new AppUser();
        userco = (AppUser) request.getSession().getAttribute("userCo");
        modelMap.addAttribute("userco", userco);

        /**[PROSPER]*/
        /** Get of the checkbox value and add off the user to the current project newsletter */
        boolean myCheckBox = request.getParameter("check") != null;
        //if (myCheckBox == true)
        //{


        int display;
        List<NewsletterModel> newsletterlist = this.newsletterService.getAllNewsletterModels();
        //boolean exist = false;
        for (NewsletterModel i : newsletterlist) {
            if (i.getProjectid() == projectId) {
                System.out.println("\n USER LOGIN : " + userco.getLogin() + "\n");
                System.out.println("LE NOMBRE DE LIKE INITIAL SUR LE PROJ: " + project.getName() + " EST " + i.getLike_());

                if (i.getEmails().contains(userco.getLogin())) {
                    System.out.println("\n\n JE te connais déja niga\n\n");
                    i.getEmails().remove(userco.getLogin());
                    if (i.getLike_() != 0)
                        i.setLike_(i.getLike_() - 1);
                    display = 2;
                } else {
                    System.out.println("\n\n JE ne te connais pas niga\n\n");
                    i.addEmail(userco.getLogin());
                    i.setLike_(i.getLike_() + 1);
                    display = 1;
                }
                this.newsletterService.updateNewsletter(i);
                modelMap.addAttribute("display", display);
                System.out.println("LE NOMBRE DE LIKE FINAL SUR LE PROJ: " + project.getName() + " EST " + i.getLike_());
            }
        }

            /*if (exist == false)
            {
                NewsletterModel new_newsletermodel = new NewsletterModel();
                new_newsletermodel.setProjectid(projectId);
                new_newsletermodel.addEmail(userco.getLogin());
                new_newsletermodel.setLike(1);
                ArrayList<String> email_list = new ArrayList<>();
                new_newsletermodel.setEmail_list(email_list);
                this.newsletterService.createNewsletter(new_newsletermodel);
                System.out.println("LE NOMBRE DE LIKE SUR LE PROJ: " + project.getName() + " EST "+ new_newsletermodel.getLike());
            }*/

        /**[PROSPER]*/
        /** Mapping in the current project page of the list containing all the comments of any projects*/
            /*List<NewsletterModel>  newsletterlist = this.newsletterService.getAllNewsletterModels();
            modelMap.addAttribute("newsletterlist",newsletterlist) ;*/
        //}
        //J'envoi ma liste en session comme ca ils pourrons la récupérer.
        return "/preinvest/projectDisplay"; // The adress of the JSP coded in tiles.xml
    }
    //Soufiane
    @RequestMapping(value = {"/projectlist/newsletter/{projectId}"})
    public String projectNewsletter( ModelMap modelMap)
    {
        List<NewsletterModel> newslettersortedlist = this.newsletterService.getAllSorted();

        modelMap.addAttribute("sortedlist", newslettersortedlist);
        return "/preinvest/projectList";
    }

    /***********************************************************************************************************/

}
