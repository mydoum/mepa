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
        ArrayList<Investor> listinvestors = getallinvestors();
        if (listinvestors.isEmpty())
            System.out.println("toto");
        else
            printelements(listinvestors);
        model.addAttribute("investorsList", listinvestors);
        return "/investment/investment";
    }

    @RequestMapping(value = "/invest/investMoney", method = RequestMethod.POST)
    public String investMoney(ModelMap model, HttpSession session, HttpServletRequest request) {
        float moneyAmount = Float.parseFloat(request.getParameter("investAmount"));
        model.addAttribute("amount", moneyAmount);
        ArrayList<Investor> listinvestors = getallinvestors();
        model.addAttribute("investorsList", listinvestors);
        return "/investment/investment";
    }

    private ArrayList<Investor> getallinvestors() {
        ArrayList<Investment> investments = new ArrayList<Investment>(investmentService.getAllInvestments());
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

    @RequestMapping(value = "/invest/filltables", method = RequestMethod.GET)
    public String fillTables(ModelMap model, HttpSession session, HttpServletRequest request) {
        User user = new User();
        user.setFirstName("Simon");
        user.setLastName("MACE");
        user.setLogin("simon.mace@epita.fr");
        user.setPassword("123");
        user.setData("toto");
        userService.createUser(user);
        for (int i = 0; i < 30; i++) {
            Investment invest = new Investment();
            invest.setAmount(15.0f);
            invest.setProjectId(1l);
            invest.setUserId(1l);
            Date date = new Date();
            invest.setDate(date);
            investmentService.createInvestment(invest);
        }
        return "/investment/investment";
    }

    private void printelements(ArrayList<Investor> listinvestors) {
        for ( Investor investor: listinvestors) {
            System.out.println(investor.getFirstname());
            System.out.println(investor.getLastname());
            System.out.println(investor.getMoneyAmount());
            System.out.println(investor.getDateOfInvestment());
        }
    }
}
