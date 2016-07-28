package fr.epita.sigl.mepa.front.controller.investment;


import fr.epita.sigl.mepa.core.domain.Investment;
import fr.epita.sigl.mepa.core.domain.Project;
import fr.epita.sigl.mepa.core.domain.AppUser;
import fr.epita.sigl.mepa.core.domain.Reward;
import fr.epita.sigl.mepa.core.service.*;
import fr.epita.sigl.mepa.core.service.AppUserService;
import fr.epita.sigl.mepa.front.Service.InvestmentFrontService;
import fr.epita.sigl.mepa.front.controller.core.preinvest.ProjectDisplayController;
import fr.epita.sigl.mepa.front.model.investment.Investor;

import fr.epita.sigl.mepa.front.utilities.Tools;
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

import static java.lang.Math.toIntExact;


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

    private InvestmentFrontService InvestmentFrontService = new InvestmentFrontService();
    private Tools tools = new Tools();

    private String displayList(ModelMap model, Project project) {
        float totalAmount = 0.00f;
        ArrayList<Investor> listinvestors = new ArrayList<Investor>();
        totalAmount = getallinvestors(listinvestors, totalAmount, project, false);
        model.addAttribute("investorsList", listinvestors);
        model.addAttribute("totalDonation", totalAmount);

        int goalAmount = toIntExact(project.getGoalAmount());
        int percentageAmount = tools.percentage(goalAmount , (int) totalAmount);

        model.addAttribute("amountSize", true);

        if (listinvestors == null || listinvestors.size() == 0)
            model.addAttribute("amountSize", false);

        model.addAttribute("projectPercentage", percentageAmount);
        model.addAttribute("projectPercentageBar", Math.min(percentageAmount, 100));

        ArrayList<Investor> listOfContributors = new ArrayList<Investor>();
        getallinvestors(listOfContributors, totalAmount, project, true);
        model.addAttribute("nbrContributos", listOfContributors.size());

        model.addAttribute("amountSize", !(listinvestors == null || listinvestors.size() == 0));
        model.addAttribute("amountCurrency", project.getCurrencyString());

        return "/investment/investment";
    }

    @RequestMapping(value = "/invest", method = RequestMethod.GET)
    public String investorsList(ModelMap model, HttpServletRequest request, Project project) {
        return displayList(model, project);
    }

    @RequestMapping(value = "/invest/{projectId}/investMoney", method = RequestMethod.POST)
    public String investMoney(ModelMap model, HttpSession session, HttpServletRequest request, @PathVariable long projectId) {
        float moneyAmount = 0.00f;
        Project project = this.projectService.getProjectById(projectId);
        AppUser tmpUser = (AppUser) request.getSession().getAttribute("userCo");

        /**
         * Check is the user is signed-in
         */
        if ((boolean) request.getSession().getAttribute("isCo") == false || tmpUser == null) {
            String errorCo = "Veuillez vous identifier pour investir dans un projet";
            model.addAttribute("messageRedirect", errorCo);
            model.addAttribute("projectId", projectId);
            return "/authentification/signin";
        }
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
        boolean anonymous_id = request.getParameter("anonymous_id") != null;

        if (insertNewInvestor(moneyAmount, tmpUser.getId(), projectId, anonymous_id) != 0L) {
            String errorMessage = "Votre donation n'a pu être prise en compte. Veuillez rééssayer ultérieurement.";
            model.addAttribute("errorInvest", errorMessage);
        }
        return projectDisplayController.projectDisplay(request, model, projectId);
    }

    private int insertNewInvestor(float moneyAmount, Long userId, Long projectId, boolean anonymous) {
        Investment newInvestment = new Investment();
        newInvestment.setAmount(moneyAmount);
        newInvestment.setProjectId(projectId);
        newInvestment.setUserId(userId);
        newInvestment.setAnonymous(anonymous);
        Date date = new Date();
        newInvestment.setDate(date);

        AppUser tmpUser = appUserService.getUserById(userId);

        investmentService.createInvestment(newInvestment);
        Project tmpProject = projectService.getProjectById(projectId);

        String mail = tmpUser.getLogin();
        String subject = "Merci pour votre contributn clion au projet " + tmpProject.getName();
        String message = "Votre contribution est de " + moneyAmount + " euros";

        try {
            tools.sendMail(mail, subject, message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        investmentService.dumpAllInvestmentsByProject(projectId);
        return 0;
    }

    @RequestMapping(value = {"/invest/download/{projectId}"})
    public String investDownload(HttpServletResponse response, ModelMap model, @PathVariable Long projectId) {
        float totalAmount = 0.00f;
        Date actual = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(actual);
        ArrayList<Investor> investors = new ArrayList<Investor>();
        Project project = projectService.getProjectById(projectId);
        totalAmount = getallinvestors(investors, totalAmount, project, true);
        if (investors != null && investors.size() > 0) {
            String fileWriter = Tools.writeCsvFile(investors);
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
        return "/core/preinvest/projectDisplay/" + projectId;
    }

    @RequestMapping(value = {"/invest/{projectId}/rewardDisplay/{rewardId}"}, method = RequestMethod.GET) // The adress to call the function
    public String projectDisplay(HttpServletRequest request, ModelMap model, @PathVariable long projectId, @PathVariable long rewardId) {

        AppUser tmpUser = (AppUser) request.getSession().getAttribute("userCo");
        /**
         * Check is the user is signed-in
         */
        if ((boolean) request.getSession().getAttribute("isCo") == false || tmpUser == null) {
            String errorCo = "Veuillez vous identifier pour investir dans un projet";
            model.addAttribute("messageRedirect", errorCo);
            return "/authentification/signin";
        }

        /**
         * We check that the selected reward exist
         * If it does not, we print an error message on the project page
         */
        Reward reward = rewardService.getRewardById(rewardId);
        if (reward == null) {
            String errorMessage = "Votre donation n'a pu être prise en compte. La contrepartie sélectionnée n'existe pas. Veuillez rééssayer ultérieurement.";
            model.addAttribute("errorInvest", errorMessage);
            return projectDisplayController.projectDisplay(request, model, projectId);
        }
        float rewardPrice = reward.getCostStart();
        String description = reward.getDescription();
        String rewardName = reward.getName();
        Project project = projectService.getProjectById(projectId);

        model.addAttribute("rewardPrice", rewardPrice);
        model.addAttribute("description", description);
        model.addAttribute("rewardName", rewardName);
        model.addAttribute("projectId", projectId);
        model.addAttribute("rewardId", rewardId);
        model.addAttribute("amountCurrency", project.getCurrencyString());


        return "/invest/rewardpay"; // The adress of the JSP coded in tiles.xml
    }

    @RequestMapping(value = "/invest/{projectId}/rewardpay/{rewardId}/invest", method = RequestMethod.POST)
    public String payReward(ModelMap model, HttpSession session, HttpServletRequest request, @PathVariable long projectId, @PathVariable long rewardId) {
        Project project = projectService.getProjectById(projectId);
        model.addAttribute("amountCurrency", project.getCurrencyString());

        float moneyAmount = Float.parseFloat(request.getParameter("investAmount"));
        Reward reward = rewardService.getRewardById(rewardId);
        if (moneyAmount < reward.getCostStart()) {
            String errorMessage = "Votre donation n'a pu être prise en compte, le montant est insuffisant. Veuillez rééssayer ultérieurement.";
            model.addAttribute("errorInvest", errorMessage);
            return projectDisplayController.projectDisplay(request, model, projectId);
        }

        investMoney(model, session, request, projectId);
        return "/preinvest/projectDisplay";
    }

    /**
     *
     * @param listOfInvestors
     * @param totalAmount
     * @param project
     * @param downloadCsv
     * @return
     */
    public float getallinvestors(ArrayList<Investor> listOfInvestors, float totalAmount, Project project, boolean downloadCsv) {
        ArrayList<Investment> investments = new ArrayList<Investment>(investmentService.getAllInvestmentsByProjectId(project.getId()));
        ArrayList<String> listmailinvestor = new ArrayList<String>();
        AppUser tmpUser;


        if (investments == null || investments.size() == 0)
            return 0.0f;

        for (Investment invest : investments) {
            tmpUser = appUserService.getUserById(invest.getUserId());
            totalAmount += InvestmentFrontService.mergeInvestor(listOfInvestors, invest, tmpUser, listmailinvestor, downloadCsv);
        }
        Collections.sort(listOfInvestors);
        return totalAmount;
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
