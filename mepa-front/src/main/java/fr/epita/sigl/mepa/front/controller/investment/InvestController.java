package fr.epita.sigl.mepa.front.controller.investment;


import fr.epita.sigl.mepa.core.domain.Investment;
import fr.epita.sigl.mepa.core.domain.Project;
import fr.epita.sigl.mepa.core.domain.User;
import fr.epita.sigl.mepa.core.service.InvestmentService;
import fr.epita.sigl.mepa.core.service.ProjectService;
import fr.epita.sigl.mepa.core.service.UserService;
import fr.epita.sigl.mepa.front.model.investment.Investor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Controller
public class InvestController {

    private static final Logger LOG = LoggerFactory.getLogger(InvestController.class);

    @Autowired
    private InvestmentService investmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/invest", method = RequestMethod.GET)
    public String invest(ModelMap model, HttpSession session) {
        ArrayList listinvestors = getallinvestors();
        model.addAttribute("investorsList", listinvestors);
        return "/investment/investment";
    }

    @RequestMapping(value = "/invest/investMoney", method = RequestMethod.POST)
    public String investMoney(ModelMap model, HttpSession session, HttpServletRequest request) {
        float moneyAmount = Float.parseFloat(request.getParameter("investAmount"));
        model.addAttribute("amount", moneyAmount);
        ArrayList listinvestors = getallinvestors();
        model.addAttribute("investorsList", listinvestors);
        return "/investment/investment";
    }

    private ArrayList getallinvestors() {
        List toto = investmentService.getAllInvestments();
        if (toto == null)
            return null;
        ArrayList<Investment> investments = new ArrayList<Investment>(toto);
        User tmpUser;
        String firstname;
        String lastname;

        ArrayList<Investor> listOfInvestors = new ArrayList<Investor>();
        for (Investment invest : investments) {
            Date created = invest.getCreated();
            Float amount = invest.getAmount();
            Long userId = invest.getUserId();
            tmpUser = userService.getUserById(userId);
            firstname = tmpUser.getFirstName();
            lastname = tmpUser.getLastName();
            Investor tmpInvestor = new Investor(firstname, lastname, amount, created);
            listOfInvestors.add(tmpInvestor);
        }
        Collections.sort(listOfInvestors);
        return listOfInvestors;
    }
}
