package fr.epita.sigl.mepa.front.controller.investment;


import fr.epita.sigl.mepa.core.domain.Investment;
import fr.epita.sigl.mepa.core.domain.Project;
import fr.epita.sigl.mepa.core.domain.AppUser;
import fr.epita.sigl.mepa.core.service.AppUserService;
import fr.epita.sigl.mepa.core.service.InvestmentService;
import fr.epita.sigl.mepa.core.service.ProjectService;
import fr.epita.sigl.mepa.front.model.investment.Investor;
import fr.epita.sigl.mepa.front.utilities.CsvExporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    private ProjectService projectService;

    private String displayList(ModelMap model, Project project) {
        float totalAmount = 0.00f;
        ArrayList<Investor> listinvestors = new ArrayList<Investor>();
        totalAmount = getallinvestors(listinvestors, totalAmount, project, false);
        model.addAttribute("investorsList", listinvestors);
        model.addAttribute("totalDonation", totalAmount);
        return "/investment/investment";
    }

    @RequestMapping(value = "/invest", method = RequestMethod.GET)
    public String invest(ModelMap model, HttpSession session, Project project) {
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

    @RequestMapping(value = "/invest/investMoney", method = RequestMethod.POST)
    public String investMoney(ModelMap model, HttpSession session, HttpServletRequest request, Project project) {
        float moneyAmount = 0.00f;

        /**
         * Trying if the getted input is a number or not. If the input is not a number
         * an exception is raised. Also a message is send to the user
         */
        try {
            moneyAmount = Float.parseFloat(request.getParameter("investAmount"));
            moneyAmount = (float) ((int) (moneyAmount * 100)) / 100;
        } catch (Exception e) {
            String errorMessage = "Please enter a numerical number as donation amount.";
            model.addAttribute("errorInvest", errorMessage);
            return displayList(model, project);
        }

        if (moneyAmount <= 0) {
            String errorMessage = "Please enter a positive number as donation amount.";
            model.addAttribute("errorInvest", errorMessage);
            return displayList(model, project);
        }

        model.addAttribute("amount", moneyAmount);
        Long userId = 2L;
        Long projectId = 1L;
        boolean anonymous_id = request.getParameter("anonymous_id") != null;

        if (insertNewInvestor(moneyAmount, userId, projectId, anonymous_id) != 0L) {
            String errorMessage = "Votre donation n'a pu être prise en compte. Veuillez rééssayer ultérieurement.";
            model.addAttribute("errorInvest", errorMessage);
        }
        return displayList(model, project);
    }

    private float getallinvestors(ArrayList<Investor> listOfInvestors, float totalAmount, Project project, boolean downloadCsv) {
        ArrayList<Investment> investments = new ArrayList<Investment>(investmentService.getAllInvestmentsByProjectId(1L/*project.getId()*/));
        AppUser tmpAppUser;
        String firstname;
        String lastname;
        String email;

        for (Investment invest : investments) {
            Date created = invest.getCreated();
            Float amount = invest.getAmount();
            Long userId = invest.getUserId();
            boolean anonymous = invest.isAnonymous();
            //tmpAppUser = appUserService.getUserById(userId);
            if (userId == 1L) {
                firstname = "Simon"; //tmpAppUser.getFirstName();
                email = "simon.mace@epita.fr"; //tmpAppUser.getLogin();
                lastname = "MACE"; //tmpAppUser.getLastName();
                if (!downloadCsv && anonymous)
                    firstname = email;
            } else {
                firstname = "Hugo"; //tmpAppUser.getFirstName();
                email = "hugo.capes@hotmail.fr"; //tmpAppUser.getLogin();
                lastname = "Capes"; //tmpAppUser.getLastName();
                if (!downloadCsv && anonymous)
                    firstname = email;
            }
            Investor tmpInvestor = new Investor(email, firstname, lastname, amount, created, anonymous);
            listOfInvestors.add(tmpInvestor);
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

        /*PostInvest -> Delete Doublon with same userId on a same projectId and update the new invest when a project is done*/
        Float oldAmount = 0.0f;
        Project projectSameId = new Project();
        Date endDate;
        ArrayList<Project> projects = new ArrayList<Project>(projectService.getAllProjects());
        for (Project p : projects) {
            if (p.getId() == projectId) {
                projectSameId = p;
                break;
            }
        }
        if (projectSameId.getId() != null && projectSameId.getId() == projectId) {
            endDate = projectService.getProjectById(projectId).getEndDate();
            if (endDate.before(date)) {
                ArrayList<Investment> investments = new ArrayList<Investment>(investmentService.getAllInvestments());
                if (!investments.isEmpty()) {
                    oldAmount = newInvestment.getAmount();
                    for (Investment inv : investments) {
                        if (inv.getUserId() == userId && inv.getProjectId() == projectId) {
                            oldAmount += inv.getAmount();
                            newInvestment.setAmount(oldAmount);
                            investmentService.deleteInvestment(inv);
                        }
                    }
                }
            }
        }
        /*\PostInvest -> Delete Doublon with same userId on a same projectId and update the new invest*/

        investmentService.createInvestment(newInvestment);

        // sendmail

        String mail = "simon.mace@epita.fr";
        // String mail = "hugo.capes@hotmail.fr";
        String subject = "Thanks for investing in the project alpha";
        String message = "blop" + moneyAmount + " €";

        try {
            sendMail(mail, subject, message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @RequestMapping(value = "/invest/filltables", method = RequestMethod.GET)
    public String fillTables(ModelMap model, HttpSession session, HttpServletRequest request, Project project) {
        for (int i = 0; i < 100; i++) {
            Investment invest = new Investment();
            invest.setAmount(10.0f);
            invest.setProjectId(1L);
            invest.setUserId(1L);
            invest.setAnonymous(false);
            Date date = new Date();
            invest.setDate(date);
            investmentService.createInvestment(invest);
        }
        for (int i = 0; i < 100; i++) {
            Investment invest = new Investment();
            invest.setAmount(15.0f);
            invest.setProjectId(2L);
            invest.setUserId(1L);
            Date date = new Date();
            invest.setDate(date);
            investmentService.createInvestment(invest);
        }
        for (int i = 0; i < 100; i++) {
            Investment invest = new Investment();
            invest.setAmount(20.0f);
            invest.setProjectId(3L);
            invest.setUserId(1L);
            Date date = new Date();
            invest.setDate(date);
            investmentService.createInvestment(invest);
        }

        return displayList(model, project);
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
