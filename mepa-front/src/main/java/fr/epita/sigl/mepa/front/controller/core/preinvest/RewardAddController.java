package fr.epita.sigl.mepa.front.controller.core.preinvest;

import fr.epita.sigl.mepa.core.domain.Project;
import fr.epita.sigl.mepa.core.domain.Reward;
import fr.epita.sigl.mepa.core.service.ProjectService;
import fr.epita.sigl.mepa.core.service.RewardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by claebo_c on 26/07/16.
 */
@Controller
@RequestMapping("/core/preinvest/rewardAdd") // The adress of the component
@SessionAttributes({})
public class RewardAddController {

    private static final Logger LOG = LoggerFactory.getLogger(RewardAddController.class);

    protected static final String NEWPROJECT= "newProject";
    protected static final String REWARD_ATTRIBUTE = "reward";
    protected static final String NEWREWARD= "newReward";
    protected static final String RAWARDS_LIST_ATTRIBUTE = "rewards_list";
    protected static final String IS_CONNECTED_ATTRIBUTE = "is_connected";


    @Autowired
    private ProjectDisplayController projectDisplayController;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RewardService rewardService;

//    @RequestMapping(value = {"/display/{projectId}"}) // The adress to call the function
    public String display(Long projectId, ModelMap modelMap)
    {
        modelMap.addAttribute(NEWPROJECT, projectId);
        return "/preinvest/rewardAdd";
    }

    @RequestMapping(value = {"/add/{projectId}"}, method = RequestMethod.GET) // The adress to call the function
    public String rewardCreate(@PathVariable long projectId, HttpServletRequest request, ModelMap modelMap) {
        /* Code your logic here */
        Reward r = new Reward();

        Boolean is_co = (Boolean) request.getSession().getAttribute("isCo");
        if (is_co == null)
            is_co = false;

        System.out.println("Is Co" + is_co);
        modelMap.addAttribute(NEWPROJECT, projectId);
        modelMap.addAttribute(NEWREWARD, r);
        modelMap.addAttribute(IS_CONNECTED_ATTRIBUTE, is_co);

        System.out.print("\n\n  create reward\n");

        return "/preinvest/rewardCreate"; // The adress of the JSP coded in tiles.xml
    }

    @RequestMapping(value = {"/processCreation/{projectId}"}, method = RequestMethod.POST) // The adress to call the function
    public String processCreation(@PathVariable long projectId, @ModelAttribute(NEWREWARD) Reward newReward, ModelMap model, HttpServletRequest request)
    {
        Project project = projectService.getProjectById(projectId);
        rewardService.createReward(newReward);

        project.addRewards(newReward);

        projectService.updateProject(project);

        return display(projectId, model);
    }



    /*
    @RequestMapping(value = {"/add/{projectId}"}) // The adress to call the function
    public String addReward(HttpServletRequest request, ModelMap modelMap)
    {
        System.out.print("\n\n reaward Add / add\n");
        return rewardCreateController.rewardCreate(request, modelMap);
    }
*/
    @RequestMapping(value = {"/projectList"})
    public String projectList(ModelMap modelMap)
    {
        System.out.print("\n\n reaward Add / projectList\n");
        return projectDisplayController.projectList(modelMap);
    }




}
