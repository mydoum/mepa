package fr.epita.sigl.mepa.front.controller.admin;

import fr.epita.sigl.mepa.core.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import fr.epita.sigl.mepa.core.domain.AppUser;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes({})
public class AdminController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    private Integer clicks = 0;
    private Integer totalAmount = 0;

    @RequestMapping(value = {"/admin"}) // The address to call the function
    public String showForm(HttpServletRequest request, ModelMap modelMap) {

        AppUser userCo = (AppUser) request.getSession().getAttribute("userCo");
        Boolean isCo = (Boolean) request.getSession().getAttribute("isCo");
        if ((userCo == null) || !isCo || !userCo.getIsAdmin()){
            return "/home/home";
        }

        request.setAttribute("clicks", clicks);
        request.setAttribute("totalAmount", totalAmount);
        return "/admin"; // The address of the JSP coded in tiles.xml
    }

    @RequestMapping(value = {"/admin/addAmount"}, method = {RequestMethod.POST})
    public void addAmount(@RequestParam("amount") String amount)
    //        ModelMap model, HttpSession session, HttpServletRequest request)
    {
        Integer adding = 0;
        clicks++;
        try {
            adding = Integer.parseInt(amount);
        } catch (Exception e) {
            return;
        }
        if (adding > 0) {
            totalAmount += adding;
            return;
        }
        return;
    }
}