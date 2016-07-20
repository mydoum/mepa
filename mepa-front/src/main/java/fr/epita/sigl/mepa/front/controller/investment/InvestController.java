package fr.epita.sigl.mepa.front.controller.investment;

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

@Controller
public class InvestController {

    private static final Logger LOG = LoggerFactory.getLogger(InvestController.class);

    @RequestMapping(value = "/invest", method = RequestMethod.GET)
    public String invest(ModelMap model, HttpSession session) {
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
}
