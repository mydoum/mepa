package fr.epita.sigl.mepa.front.controller;

/**
 * Created by Xavier on 21/07/2016.
 */
import fr.epita.sigl.mepa.core.domain.AppUser;
import fr.epita.sigl.mepa.core.domain.Investment;
import fr.epita.sigl.mepa.core.domain.Project;
import fr.epita.sigl.mepa.core.domain.Reward;
import fr.epita.sigl.mepa.core.service.AppUserService;
import fr.epita.sigl.mepa.core.service.InvestmentService;
import fr.epita.sigl.mepa.core.service.ProjectService;
import fr.epita.sigl.mepa.core.service.RewardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/insertDummy") // The adress of the component
@SessionAttributes({})
public class InsertDummyController {
    private static final Logger LOG = LoggerFactory.getLogger(InsertDummyController.class);

    protected static final String PROJECT_ATTRIBUTE = "project";
    protected static final String NEWPROJECT= "newProject";
    protected static final String PROJECTS_LIST_ATTRIBUTE = "project_list";

    @Autowired
    private ProjectService projectService;

    @Autowired
    private InvestmentService investmentService;

    @Autowired
    private RewardService rewardService;

    @Autowired
    private AppUserService appUserService;

    private Random rand = new Random();

    private Date getRandomDate()
    {
        long min_date = new Date("01/01/2016").getTime();
        long ms = min_date + (Math.abs(rand.nextLong()) % (365 * 24 * 60 * 60 * 1000 * 70L));

        // Construct a date
        return new Date(ms);
    }

    private Date getRandomDateFinishedDate()
    {
        long min_date = new Date("01/01/2016").getTime();
        long ms = min_date - (Math.abs(rand.nextLong()) % (365 * 24 * 60 * 60 * 1000 * 70L));

        // Construct a date
        return new Date(ms);
    }

    @RequestMapping(value = {"/"}) // The adress to call the function
    public String insertDummy(HttpServletRequest request, ModelMap modelMap) {

        /*AUTH */
        String a = "";
        for (int i = 0; i < 10; i++) {
            a += "0";
            AppUser appUser = new AppUser();
            appUser.setFirstName("Tahar");
            appUser.setLastName("Sayagh");
            appUser.setLogin("tahar.sayagh" + a + "@gmail.com");
            appUser.setPassword("password");
            Date date = new Date();
            appUser.setBirthDate(date);
            this.appUserService.createUser(appUser);
        }

        /* PREINVEST*/

        for (int j = 0; j < 10; ++j) {
            Reward r = new Reward();
            r.setName("Coucou");
            r.setDescription("This is a description");
            r.setCostStart((long) 10);
            rewardService.createReward(r);
        }

        for (int i = 0; i < 20; ++i) {
            rand.nextLong();
            Project newProject = new Project((long) 1, "Yolo", getRandomDate());
            Date d = getRandomDate();
            while (d.after(newProject.getEndDate()))
                d = getRandomDate();


            newProject.setStartDate(d);
            ArrayList<String> imageLinkList = new ArrayList<>();
            imageLinkList.add("http://www.gobadges.com/v/vspfiles/photos/CD0564-2.jpg");
            imageLinkList.add("http://www.nyan.cat/images/cat/4.gif");
            newProject.setImagesLinks(imageLinkList);
            newProject.setDescription("tototototototototototototototototototototo");



            this.projectService.createProject(newProject);

            Set<Reward> rewards = new HashSet<>();

            for (int j = 0; j < 10; ++j) {
                Reward r = new Reward();
                r.setName("Coucou");
                r.setDescription("This is a description");
                r.setCostStart((long) 10);
                this.rewardService.createReward(r);
                rewards.add(r);
            }

            newProject.setRewards(rewards);
            this.projectService.updateProject(newProject);

        }

        for (int i = 0; i < 20; ++i) {
            rand.nextLong();
            Project newProject = new Project((long) 1, "YoloSwag", getRandomDateFinishedDate());
            Date d = getRandomDateFinishedDate();
            while (d.after(newProject.getEndDate()))
                d = getRandomDateFinishedDate();


            newProject.setStartDate(d);
            ArrayList<String> imageLinkList = new ArrayList<>();
            imageLinkList.add("http://www.gobadges.com/v/vspfiles/photos/CD0564-2.jpg");
            imageLinkList.add("http://www.nyan.cat/images/cat/4.gif");
            newProject.setImagesLinks(imageLinkList);
            newProject.setDescription("Gildas est trop beau");



            this.projectService.createProject(newProject);

            Set<Reward> rewards = new HashSet<>();

            for (int j = 0; j < 10; ++j) {
                Reward r = new Reward();
                r.setName("Gildas est beau" + j);
                r.setDescription("This is a description");
                r.setCostStart((long) 10);
                this.rewardService.createReward(r);
                rewards.add(r);
            }

            newProject.setRewards(rewards);
            this.projectService.updateProject(newProject);

        }
        /* INVEST*/
        for (int i = 0; i < 100; i++) {
            Investment invest = new Investment();
            invest.setAmount(10.0f);
            invest.setProjectId(1L);
            invest.setUserId(1L);
            invest.setAnonymous(false);
            Date date = new Date();
            invest.setDate(date);
            investmentService.createInvestment(invest);
        }
        for (int i = 0; i < 100; i++) {
            Investment invest = new Investment();
            invest.setAmount(15.0f);
            invest.setProjectId(2L);
            invest.setUserId(1L);
            Date date = new Date();
            invest.setDate(date);
            investmentService.createInvestment(invest);
        }
        for (int i = 0; i < 100; i++) {
            Investment invest = new Investment();
            invest.setAmount(20.0f);
            invest.setProjectId(3L);
            invest.setUserId(1L);
            Date date = new Date();
            invest.setDate(date);
            investmentService.createInvestment(invest);
        }


        /* POSTINVEST*/

        return "/insertDummy"; // The adress of the JSP coded in tiles.xml
    }

    private void printAllRewards() {
        ArrayList<Reward> rewards = new ArrayList<Reward>(rewardService.getAllRewards());
        for (Reward reward : rewards) {
            System.out.println("Reward");
            System.out.println(reward.getId());
            System.out.println(reward.getCostStart());
            System.out.println(reward.getName());
            System.out.println(reward.getDescription());
        }
        System.out.println("tiutirt");
    }
}
