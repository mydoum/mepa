package fr.epita.sigl.mepa.front.controller.core.postinvest;

import fr.epita.sigl.mepa.core.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/postinvest") // The adress of the component
@SessionAttributes({})
public class AdminController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    private Integer clicks = 0;
    private Integer totalAmount = 0;

    @Autowired
    private ModelService modelService;

    @RequestMapping(value = {"/admin"}) // The address to call the function
    public String showForm(HttpServletRequest request, ModelMap modelMap) {
        /* Code your logic here */
        request.setAttribute("clicks", clicks);
        request.setAttribute("totalAmount", totalAmount);
        return "/postinvest/admin"; // The address of the JSP coded in tiles.xml
    }

    @RequestMapping(value = {"/admin/addAmount"}, method = {RequestMethod.POST})
    public String addAmount(@RequestParam("amount") String amount)
    //        ModelMap model, HttpSession session, HttpServletRequest request)
    {
        Integer adding = 0;
        clicks++;
        try {
            adding = Integer.parseInt(amount);
        }
        catch (Exception e) {
            String errorMessage = "Please enter a numerical number as donation amount.";
            return "/invalid";
        }
        if(adding > 0) {
            totalAmount += adding;
        }
        System.out.println(clicks);
        System.out.println(amount);
        System.out.println(totalAmount);
        return "/invalid";
    }
/*
    @RequestMapping(value = {"/projectCreate"}, method = RequestMethod.GET) // The adress to call the function
    public String projectCreate(ModelMap modelMap) {
        /* Code your logic here /
        Project p = new Project();
        modelMap.addAttribute(NEWPROJECT, p);
        return "/preinvest/projectCreate"; // The adress of the JSP coded in tiles.xml
    }
*/
}