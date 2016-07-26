package fr.epita.sigl.mepa.front.Service;

import fr.epita.sigl.mepa.core.domain.AppUser;
import fr.epita.sigl.mepa.core.domain.Investment;
import fr.epita.sigl.mepa.core.domain.Project;
import fr.epita.sigl.mepa.core.service.AppUserService;
import fr.epita.sigl.mepa.core.service.InvestmentService;
import fr.epita.sigl.mepa.front.model.investment.Investor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Simon on 26/07/2016.
 */
public class InvestmentFrontService {
    @Autowired
    private InvestmentService investmentService;
    @Autowired
    private AppUserService appUserService;

    public float getallinvestors(ArrayList<Investor> listOfInvestors, float totalAmount, Project project, boolean downloadCsv) {
        ArrayList<Investment> investments = new ArrayList<Investment>(investmentService.getAllInvestmentsByProjectId(project.getId()));
        ArrayList<String> listmailinvestor = new ArrayList<String>();
        AppUser tmpUser;
        String firstname;
        String lastname;
        String email;
        boolean investorIsPresent = false;

        if (investments == null || investments.size() == 0)
            return 0.0f;

        for (Investment invest : investments) {
            investorIsPresent = true;
            Date created = invest.getCreated();
            Float amount = invest.getAmount();
            Long userId = invest.getUserId();
            boolean anonymous = invest.isAnonymous();
            tmpUser = appUserService.getUserById(userId);
            if (!anonymous || downloadCsv) {
                firstname = tmpUser.getFirstName();
                lastname = tmpUser.getLastName();
                if (listmailinvestor.indexOf(tmpUser.getLogin()) == -1) {
                    listmailinvestor.add(tmpUser.getLogin());
                    investorIsPresent = false;
                }
            } else {
                firstname = "Anonyme";
                lastname = "Anonyme";
                listmailinvestor.add("anonyme");
            }
            email = tmpUser.getLogin();
            Investor tmpInvestor = new Investor(email, firstname, lastname, amount, created, anonymous);
            /*if (project.isfinished && investorIsPresent) {
                insertinto(listOfInvestors, tmpInvestor);
            } else {*/
            listOfInvestors.add(tmpInvestor);
            //}
            totalAmount += amount;
        }
        Collections.sort(listOfInvestors);
        return totalAmount;
    }
}
