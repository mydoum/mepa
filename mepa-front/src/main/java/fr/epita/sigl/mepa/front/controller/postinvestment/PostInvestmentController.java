package fr.epita.sigl.mepa.front.controller.postinvestment;

import fr.epita.sigl.mepa.core.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import fr.epita.sigl.mepa.front.model.investment.Investor;

@Controller
@SessionAttributes({})
public class PostInvestmentController {

    private static final Logger LOG = LoggerFactory.getLogger(PostInvestmentController.class);

    public void groupInvestors(ArrayList<Investor> listOfInvestors, Investor investor) {

        if (investor.isAnonymous()) {
            listOfInvestors.add(investor);
            return;
        }

        for (Investor i: listOfInvestors) {
            if(i.getEmail().equals(investor.getEmail())) {
                i.setMoneyAmount(i.getMoneyAmount() + investor.getMoneyAmount());
                return;
            }
        }
    }
}