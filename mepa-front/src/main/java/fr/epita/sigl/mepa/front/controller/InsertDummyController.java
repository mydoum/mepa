package fr.epita.sigl.mepa.front.controller;

/**
 * Created by Xavier on 21/07/2016.
 */
import fr.epita.sigl.mepa.core.domain.Project;
import fr.epita.sigl.mepa.core.domain.Reward;
import fr.epita.sigl.mepa.core.service.ProjectService;
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

    private Random rand = new Random();

    private Date getRandomDate()
    {
        long ms = -946771200000L + (Math.abs(rand.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));

        // Construct a date
        return new Date(ms);
    }

    @RequestMapping(value = {"/"}) // The adress to call the function
    public String insertDummy(HttpServletRequest request, ModelMap modelMap) {

        /*AUTH */


        /* PREINVEST*/

        for (int i = 0; i < 10; ++i) {
            rand.nextLong();
            Project newProject = new Project((long) 1, "Yolo", getRandomDate());
            Date d = getRandomDate();
            while (d.after(newProject.getEndDate()))
                d = getRandomDate();

            Set<Reward> rewards = new HashSet<>();

            for (int j = 0; j < 10; ++j) {
                Reward r = new Reward();
                r.setName("Coucou");
                r.setDescription("This is a description");
                r.setCostStart((long) 10);
                rewards.add(r);
            }

            newProject.setRewards(rewards);

            newProject.setStartDate(d);
            ArrayList<String> imageLinkList = new ArrayList<>();
            imageLinkList.add("http://www.gobadges.com/v/vspfiles/photos/CD0564-2.jpg");
            imageLinkList.add("http://www.nyan.cat/images/cat/4.gif");
            newProject.setImagesLinks(imageLinkList);
            newProject.setDescription("tototototototototototototototototototototo");



            this.projectService.createProject(newProject);
        }
        /* INVEST*/


        /* POSTINVEST*/


        return "/insertDummy"; // The adress of the JSP coded in tiles.xml
    }

}
