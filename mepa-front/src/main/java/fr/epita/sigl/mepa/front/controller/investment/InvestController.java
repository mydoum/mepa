package fr.epita.sigl.mepa.front.controller.investment;


import fr.epita.sigl.mepa.core.domain.Investment;
import fr.epita.sigl.mepa.core.domain.Project;
import fr.epita.sigl.mepa.core.domain.AppUser;
import fr.epita.sigl.mepa.core.domain.Reward;
import fr.epita.sigl.mepa.core.service.*;
import fr.epita.sigl.mepa.core.service.AppUserService;
import fr.epita.sigl.mepa.front.controller.core.preinvest.ProjectDisplayController;
import fr.epita.sigl.mepa.front.model.investment.Investor;
import fr.epita.sigl.mepa.front.utilities.CsvExporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static fr.epita.sigl.mepa.front.utilities.Mail.sendMail;


@Controller
public class InvestController {

    private static final Logger LOG = LoggerFactory.getLogger(InvestController.class);

    @Autowired
    private InvestmentService investmentService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private RewardService rewardService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectDisplayController projectDisplayController;

    private String displayList(ModelMap model, Project project) {
        float totalAmount = 0.00f;
        ArrayList<Investor> listinvestors = new ArrayList<Investor>();
        totalAmount = getallinvestors(listinvestors, totalAmount, project, false);
        model.addAttribute("investorsList", listinvestors);
        model.addAttribute("totalDonation", totalAmount);
        return "/investment/investment";
    }

    @RequestMapping(value = "/invest", method = RequestMethod.GET)
    public String investorsList(ModelMap model, HttpServletRequest request, Project project) {
        return displayList(model, project);
    }

    @RequestMapping(value = "/invest/comment", method = RequestMethod.GET)
    public String comment(ModelMap model, HttpSession session, Project project) {
        float totalAmount = 0.00f;
        ArrayList<Investor> listinvestors = new ArrayList<Investor>();
        totalAmount = getallinvestors(listinvestors, totalAmount, project, false);
        model.addAttribute("totalDonation", totalAmount);
        model.addAttribute("isConnected", false);
        return "/investment/comment";
    }

    @RequestMapping(value = "/invest/{projectId}/investMoney", method = RequestMethod.POST)
    public String investMoney(ModelMap model, HttpSession session, HttpServletRequest request, @PathVariable long projectId) {
        float moneyAmount = 0.00f;
        Project project = this.projectService.getProjectById(projectId);

        /**
         * Check is the user is signed-in
         */
        String errorCo = "Veuillez vous identifier pour investir dans un projet";
        if ((boolean) request.getSession().getAttribute("isCo")==false)
            return "/authentification/signin";


        /**
         * Trying if the getted input is a number or not. If the input is not a number
         * an exception is raised. Also a message is send to the user
         */
        try {
            moneyAmount = Float.parseFloat(request.getParameter("investAmount"));
            moneyAmount = (float) ((int) (moneyAmount * 100)) / 100;
        } catch (Exception e) {
            String errorMessage = "Veuillez entrer un nombre pour le montant du don.";
            model.addAttribute("errorInvest", errorMessage);
            return displayList(model, project);
        }

        if (moneyAmount <= 0) {
            String errorMessage = "Veuillez entrer un nombre positif pour le montant du don.";
            model.addAttribute("errorInvest", errorMessage);
            return displayList(model, project);
        }

        model.addAttribute("amount", moneyAmount);
        Long userId = 2L;
        boolean anonymous_id = request.getParameter("anonymous_id") != null;

        if (insertNewInvestor(moneyAmount, userId, projectId, anonymous_id) != 0L) {
            String errorMessage = "Votre donation n'a pu être prise en compte. Veuillez rééssayer ultérieurement.";
            model.addAttribute("errorInvest", errorMessage);
        }

        return projectDisplayController.projectDisplay(request, model, projectId);
    }

    private float getallinvestors(ArrayList<Investor> listOfInvestors, float totalAmount, Project project, boolean downloadCsv) {
        ArrayList<Investment> investments = new ArrayList<Investment>(investmentService.getAllInvestmentsByProjectId(1L/*project.getId()*/));
        ArrayList<String> listmailinvestor = new ArrayList<String>();
        AppUser tmpUser;
        String firstname;
        String lastname;
        String email;
        boolean investorIsPresent = false;

        for (Investment invest : investments) {
            investorIsPresent = true;
            Date created = invest.getCreated();
            Float amount = invest.getAmount();
            Long userId = invest.getUserId();
            boolean anonymous = invest.isAnonymous();
            tmpUser = appUserService.getUserById(userId);
            if (!anonymous) {
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

    private int insertNewInvestor(float moneyAmount, Long userId, Long projectId, boolean anonymous) {
        Investment newInvestment = new Investment();
        newInvestment.setAmount(moneyAmount);
        newInvestment.setProjectId(projectId);
        newInvestment.setUserId(userId);
        newInvestment.setAnonymous(anonymous);
        Date date = new Date();
        newInvestment.setDate(date);

        investmentService.createInvestment(newInvestment);

        //String mail = "simon.mace@epita.fr";
        String mail = "hugo.capes@hotmail.fr";
        String subject = "Merci pour votre contribution au projet alpha";
        String message = "Votre contribution s'élève à" + moneyAmount + " euros";

        try {
            sendMail(mail, subject, message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @RequestMapping(value = {"/invest/download"})
    public String investDownload(HttpServletResponse response, ModelMap model, Project project) {
        float totalAmount = 0.00f;
        Date actual = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(actual);
        ArrayList<Investor> investors = new ArrayList<Investor>();
        totalAmount = getallinvestors(investors, totalAmount, project, true);
        if (investors.size() > 0) {
            String fileWriter = CsvExporter.writeCsvFile(investors);
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=\"Investors_export_" + date + ".csv\"");
            try {
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(fileWriter.getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("investorsList", investors);
        model.addAttribute("totalDonation", totalAmount);
        return "/investment/investment";
    }

    @RequestMapping(value = {"/invest/{projectId}/rewardDisplay/{rewardId}"}, method = RequestMethod.GET) // The adress to call the function
    public String projectDisplay(HttpServletRequest request, ModelMap model, @PathVariable long projectId, @PathVariable long rewardId) {
        /* Code your logic here */
        Reward reward = rewardService.getRewardById(rewardId);
        if (reward == null) {
            return "/home/home";
        }
        long rewardPrice = reward.getCostStart();
        String description = reward.getDescription();
        String rewardName = reward.getName();

        model.addAttribute("rewardPrice", rewardPrice);
        model.addAttribute("description", description);
        model.addAttribute("rewardName", rewardName);
        model.addAttribute("projectId", projectId);
        model.addAttribute("rewardId", rewardId);

        String return_string = "/invest/rewardpay";
        return return_string; // The adress of the JSP coded in tiles.xml
    }

    @RequestMapping(value = "/invest/{projectId}/rewardpay/{rewardId}/invest", method = RequestMethod.POST)
    public String payReward(ModelMap model, HttpSession session, HttpServletRequest request, @PathVariable long projectId, @PathVariable long rewardId) {
        investMoney(model, session, request, projectId);
        return "/preinvest/projectDisplay";
    }

    private void printelements(ArrayList<Investor> listinvestors) {
        for (Investor investor : listinvestors) {
            System.out.println(investor.getFirstname());
            System.out.println(investor.getLastname());
            System.out.println(investor.getMoneyAmount());
            System.out.println(investor.getDateOfInvestment());
        }
    }

    private void printalluser() {
        ArrayList<AppUser> appUsers = new ArrayList<AppUser>(appUserService.getAllUsers());
        for (AppUser appUser : appUsers) {
            System.out.println(appUser.getFirstName());
            System.out.println(appUser.getLastName());
            System.out.println(appUser.getId());
        }
    }
}
