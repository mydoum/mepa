package fr.epita.sigl.mepa.front.controller.investment;


import com.sun.mail.smtp.SMTPTransport;
import fr.epita.sigl.mepa.core.domain.Investment;
import fr.epita.sigl.mepa.core.domain.User;
import fr.epita.sigl.mepa.core.service.InvestmentService;
import fr.epita.sigl.mepa.core.service.ProjectService;
import fr.epita.sigl.mepa.core.service.UserService;
import fr.epita.sigl.mepa.core.utils.Mail;
import fr.epita.sigl.mepa.front.model.investment.Investor;
import fr.epita.sigl.mepa.front.utilities.CsvExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import java.util.Properties;


@Controller
public class InvestController {

    private static final Logger LOG = LoggerFactory.getLogger(InvestController.class);

    @Autowired
    private InvestmentService investmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    static private Properties mailServerProperties;
    static private Session getMailSession;
    static private MimeMessage generateMailMessage;

    @RequestMapping(value = "/invest", method = RequestMethod.GET)
    public String invest(ModelMap model, HttpSession session) {
        float totalAmount = 0f;
        ArrayList<Investor> listinvestors = getallinvestors(totalAmount);
        model.addAttribute("investorsList", listinvestors);
        model.addAttribute("totaldonation", totalAmount);
        return "/investment/investment";
    }


    @RequestMapping(value = "/invest/investMoney", method = RequestMethod.POST)
    public String investMoney(ModelMap model, HttpSession session, HttpServletRequest request)  {
        float totalAmount = 0f;

        float moneyAmount = Float.parseFloat(request.getParameter("investAmount"));
        model.addAttribute("amount", moneyAmount);
        Long userId = 1L;
        Long projectId = 1L;

        if (insertNewInvestor(moneyAmount, userId, projectId) == 0)
            return "/investment/investment";

        ArrayList<Investor> listinvestors = getallinvestors(totalAmount);
        model.addAttribute("investorsList", listinvestors);
        model.addAttribute("totaldonation", totalAmount);

        return "/investment/investment";
    }


    private ArrayList<Investor> getallinvestors(float totalAmount) {
        ArrayList<Investment> investments = new ArrayList<Investment>(investmentService.getAllInvestments());
        User tmpUser;
        String firstname;
        String lastname;
        String email;
        ArrayList<Investor> listOfInvestors = new ArrayList<Investor>();

        for (Investment invest : investments) {
            Date created = invest.getCreated();
            Float amount = invest.getAmount();
            Long userId = invest.getUserId();
            tmpUser = userService.getUserById(userId);
            firstname = tmpUser.getFirstName();
            lastname = tmpUser.getLastName();
            email = tmpUser.getLogin();
            Investor tmpInvestor = new Investor(email, firstname, lastname, amount, created);
            listOfInvestors.add(tmpInvestor);
            totalAmount += amount;
        }
        Collections.sort(listOfInvestors);
        return listOfInvestors;
    }

    private int insertNewInvestor (float moneyAmount, Long userId, Long projectId) {
        Investment newInvestment = new Investment();
        newInvestment.setAmount(moneyAmount);
        newInvestment.setProjectId(projectId);
        newInvestment.setUserId(userId);
        Date date = new Date();
        newInvestment.setDate(date);
        investmentService.createInvestment(newInvestment);

        try {
            sendMail(userId, moneyAmount);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return 1;
    }
    private void sendMail (Long userId, float amountMoney) throws AddressException, MessagingException {
        User tmpUser = userService.getUserById(userId);
        String userMail = tmpUser.getLogin();
        String userFirstName = tmpUser.getFirstName();
        String userLastName = tmpUser.getLastName();

        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(userMail));
        generateMailMessage.setSubject("Greetings " + userFirstName + " " + userLastName);
        String emailBody = "Thank you for donating. " + amountMoney + "<br><br> Regards, <br>MEPA Team";
        generateMailMessage.setContent(emailBody, "text/html");

        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");

        transport.connect("smtp.gmail.com", "mepa.epita@gmail.com", "sigl2017");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }

    @RequestMapping(value = "/invest/filltables", method = RequestMethod.GET)
    public String fillTables(ModelMap model, HttpSession session, HttpServletRequest request) {
        User user = new User();
        user.setFirstName("Simon");
        user.setLastName("MACE");
        user.setLogin("simon.mace@epita.fr");
        user.setPassword("123");
        user.setData("toto");
        user.setId(1L);
        userService.createUser(user);
        User user2 = new User();
        user2.setFirstName("Hugo");
        user2.setLastName("CAPES");
        user2.setLogin("hugo.capes@epita.fr");
        user2.setPassword("123");
        user2.setData("toto");
        user2.setId(2L);
        userService.createUser(user2);
        for (int i = 0; i < 30; i++) {
            Investment invest = new Investment();
            invest.setAmount(15.0f);
            invest.setProjectId(1L);
            invest.setUserId(1L);
            Date date = new Date();
            invest.setDate(date);
            investmentService.createInvestment(invest);
        }
        float totalAmount = 0f;
        ArrayList<Investor> listinvestors = getallinvestors(totalAmount);
        model.addAttribute("investorsList", listinvestors);
        model.addAttribute("totaldonation", totalAmount);
        return "/investment/investment";
    }

    @RequestMapping(value = {"/invest/download"})
    public String investDownload(HttpServletResponse response, ModelMap model) {
        float totalAmount = 0f;
        Date actual = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(actual);
        date.replace("-", "_");
        ArrayList<Investor> investors = getallinvestors(totalAmount);
        if (investors != null && investors.size() > 0) {
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
        model.addAttribute("totaldonation", totalAmount);
        return "/invest/invest";
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
        ArrayList<User> users = new ArrayList<User>(userService.getAllUsers());
        for ( User user: users) {
            System.out.println(user.getFirstName());
            System.out.println(user.getLastName());
            System.out.println(user.getId());
        }
    }
}
