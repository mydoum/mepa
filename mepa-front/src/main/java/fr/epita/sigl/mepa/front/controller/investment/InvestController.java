package fr.epita.sigl.mepa.front.controller.investment;

import fr.epita.sigl.mepa.core.domain.Investment;
import fr.epita.sigl.mepa.core.domain.User;
import fr.epita.sigl.mepa.core.service.InvestmentService;
import fr.epita.sigl.mepa.core.service.UserService;
import fr.epita.sigl.mepa.core.service.impl.InvestmentlServiceImpl;
import fr.epita.sigl.mepa.core.service.impl.UserServiceImpl;
import fr.epita.sigl.mepa.front.model.investment.InvestAmount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class InvestController {

    private static final Logger LOG = LoggerFactory.getLogger(InvestController.class);

    private InvestmentService investmentService = new InvestmentlServiceImpl();
    private UserService userService = new UserServiceImpl();

    @RequestMapping(value = "/invest", method = RequestMethod.GET)
    public String invest(ModelMap model, HttpSession session) {
        ArrayList listinvestors = getallinvestors();
        model.addAllAttributes(listinvestors);
        return "/investment/investment";
    }

    @RequestMapping(value = "/invest/investMoney", method = RequestMethod.POST)
    public String investMoney(ModelMap model, HttpSession session, @Valid InvestAmount investAmount,
                              BindingResult result) {
        int moneyAmount = investAmount.getMoneyAmount();
        model.addAttribute("montant", moneyAmount);
        return "/investment/investment";
    }

    @ModelAttribute("investAmount")
    public InvestAmount initinvestAmount() {
        return new InvestAmount();
    }

    private ArrayList getallinvestors() {
        ArrayList<Investment> investments = new ArrayList<Investment>(investmentService.getAllInvestments());
        User tmpUser;
        String firstname;
        String lastname;

        ArrayList listOfInvestors = new ArrayList();
        for (Investment invest : investments) {
            Date created = invest.getCreated();
            Integer amount = invest.getAmount();
            Long projectId = invest.getProjectId();
            Long userId = invest.getUserId();
            tmpUser = userService.getUserById(userId);
        }
        return listOfInvestors;
    }
}
