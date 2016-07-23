package fr.epita.sigl.mepa.front.controller.authentification;

import fr.epita.sigl.mepa.core.domain.AppUser;
import fr.epita.sigl.mepa.core.service.AppUserService;
import fr.epita.sigl.mepa.front.user.form.AddCustomUserFormBean;
import fr.epita.sigl.mepa.front.user.form.LoginUserFormBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


@RequestMapping("/authentification")
@Controller
public class AuthController {

    @Autowired
    private AppUserService appUserService;

    static private Properties mailServerProperties;
    static private Session getMailSession;
    static private MimeMessage generateMailMessage;

    @RequestMapping(value = {"/auth"}, method = {RequestMethod.GET})
    public String showAuth(HttpServletRequest request, ModelMap modelMap) {
        List<AppUser> appUsers = this.appUserService.getAllUsers();
        if (appUsers.size() > 0) {
            modelMap.addAttribute("usersList", appUsers);
        }
        return "/authentification/signup";
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String getForm(HttpServletRequest request, ModelMap modelMap,
                          @Valid AddCustomUserFormBean addCustomUserFormBean, BindingResult result) throws ParseException {
        return "/home/home";

    }

    @RequestMapping(value = {"/addUser"}, method = {RequestMethod.POST})
    public String processForm(HttpServletRequest request, ModelMap modelMap) {
        AppUser newAppUser = new AppUser();
        String bithDate = request.getParameter("birthdate");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String login = request.getParameter("email");
        String pwd = request.getParameter("password");

        newAppUser.setBirthDate(new Date());
        newAppUser.setFirstName(firstName);
        newAppUser.setLastName(lastName);
        newAppUser.setLogin(login);
        newAppUser.setPassword(pwd);

        this.appUserService.createUser(newAppUser);
        System.out.println("Created new user : " + newAppUser.getFirstName() + " " + newAppUser.getLastName());
        String msg = "Le compte a bien été créé";
        modelMap.addAttribute("userIsCreated", msg);
        List<AppUser> appUsers = this.appUserService.getAllUsers();
        modelMap.addAttribute("usersList", appUsers);
        return "/authentification/signup";
    }

    @RequestMapping(value = {"/resendPwd"}, method = {RequestMethod.GET})
    public String showPwd(HttpServletRequest request, ModelMap modelMap) throws ParseException {
        return "/authentification/resendPwd";
    }

    @RequestMapping(value = {"/resendPwd"}, method = {RequestMethod.POST})
    public String resendPwd(HttpServletRequest request, ModelMap modelMap) throws ParseException {
        Boolean isSent = false;
        String login = request.getParameter("email");
        AppUser recipient = this.appUserService.getUserByLogin(login);
        System.out.println(recipient.getFirstName());
        if (recipient != null && !recipient.getFirstName().equalsIgnoreCase("")) {
            try {
                sendMail(recipient);
                isSent = true;
                modelMap.addAttribute("isSent", isSent);
                modelMap.addAttribute("email", recipient.getLogin());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        else
            modelMap.addAttribute("isSent", isSent);
        return "/authentification/resendPwd";
    }

    private void sendMail(AppUser recipient) throws AddressException, MessagingException {
        //AppUser tmpUser = appUserService.getUserById(userId);
        String userMail = recipient.getLogin();//tmpUser.getLogin();
        String userFirstName = recipient.getFirstName(); //tmpUser.getFirstName();
        String userLastName = recipient.getLastName(); //tmpUser.getLastName();

        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(userMail));
        generateMailMessage.setSubject("Merci beaucoup " + userFirstName + " " + userLastName);
        String emailBody = "Merci pour votre don de: Lolilol" + "<br><br> Cordialement, <br>La Team MEPA";
        generateMailMessage.setContent(emailBody, "text/html");

        Transport transport = getMailSession.getTransport("smtp");

        transport.connect("smtp.gmail.com", "mepa.epita@gmail.com", "sigl2017");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }

    @RequestMapping(value = {"/signin"}, method = {RequestMethod.GET})
    public String getsignin(HttpServletRequest request, ModelMap modelMap) {
        return "/authentification/signin";
    }

    @RequestMapping(value = {"/signin"}, method = {RequestMethod.POST})
    public String signIn(HttpServletRequest request, ModelMap modelMap) {

        String login = request.getParameter("email");
        String pwd = request.getParameter("password");

        Boolean isCo = (Boolean) request.getSession().getAttribute("isCo");
        AppUser userCo = this.appUserService.getUserByLogin(login);
        if (userCo != null) {
            AppUser newAppUser = this.appUserService.getUserById(userCo.getId());
            if (StringUtils.equals(pwd, newAppUser.getPassword())) {
                request.getSession().setAttribute("userCo", newAppUser);
                isCo = true;
                request.getSession().setAttribute("isCo", true);
                modelMap.addAttribute("isCo", isCo);
                return "/home/home";
            }
        }
        else {
            modelMap.addAttribute("isCo", isCo);
            System.out.println("Identifiant / mdp incorrect");
            return "/authentification/signin";
        }

        return "/authentification/signin";
    }

    @RequestMapping(value = {"/deconnexion"}, method = {RequestMethod.GET})
    public String deconnect(HttpServletRequest request, ModelMap modelMap) {

        AppUser userCo = (AppUser) request.getSession().getAttribute("userCo");
        Boolean isCo = (Boolean) request.getSession().getAttribute("isCo");
        if (userCo != null && isCo) {
            System.out.println("User deconnexion : " + userCo.getFirstName() + " " + userCo.getLastName());
            request.getSession().removeAttribute("userCo");
            request.getSession().removeAttribute("isCo");
        }
        return "/home/home";
    }

    @RequestMapping(value = {"/editUser"}, method = {RequestMethod.GET})
    public String showEditUserPage(HttpServletRequest request, ModelMap modelMap) {
        AppUser userCo = (AppUser) request.getSession().getAttribute("userCo");
        Boolean isCo = (Boolean) request.getSession().getAttribute("isCo");
        System.out.println("inside showEditUserPage");
        if (userCo != null && isCo) {
            // tu peux afficher les données user

            return "/authentification/editUser";
        }
        return "/home/home";
//        return "redirect:/home/home"; A tester pour plus tard
    }

    @RequestMapping(value = {"/editUser"}, method = {RequestMethod.POST})
    public String editUser(HttpServletRequest request, ModelMap modelMap) {
        AppUser userCo = (AppUser) request.getSession().getAttribute("userCo");
        Boolean isCo = (Boolean) request.getSession().getAttribute("isCo");
        System.out.println("inside editUser");

        System.out.println("user pwd = " + userCo.getPassword());
        if (userCo != null && isCo) {
            AppUser user = this.appUserService.getUserByLogin(userCo.getLogin());
            if (user != null) { // the user really exist, it's not a fake
                String bithDate = request.getParameter("birthdate");
                String firstName = request.getParameter("firstname");
                String lastName = request.getParameter("lastname");
                String login = request.getParameter("email");
//                String pwd = request.getParameter("password");

                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setBirthDate(user.getBirthDate());
                user.setLogin(login);

                this.appUserService.updateUser(user);
                request.getSession().setAttribute("userCo", user);
            }
            return "/authentification/editUser";
        }
        return "/home/home";

    }

    @RequestMapping(value = {"/addFakeUser"}, method = {RequestMethod.GET})
    public String addFakeUser() {
        AppUser newAppUser = new AppUser();

        ArrayList<String> firstNames = new ArrayList<String>();
        ArrayList<String> lastNames = new ArrayList<String>();
        ArrayList<String> logins = new ArrayList<String>();
        ArrayList<String> pwds = new ArrayList<String>();
        firstNames.add("Patrick");
        firstNames.add("test");
        firstNames.add("toto");

        lastNames.add("Ear");
        lastNames.add("test");
        lastNames.add("tata");

        logins.add("patrick.ear@epita.fr");
        logins.add("test@test.fr");
        logins.add("toto.tata@tutu.fr");

        pwds.add("123456789");
        pwds.add("1234567");
        pwds.add("password");


        for (int i = 0; i < firstNames.size(); ++i) {
            newAppUser.setBirthDate(new Date());
            newAppUser.setFirstName(firstNames.get(i));
            newAppUser.setLastName(lastNames.get(i));
            newAppUser.setLogin(logins.get(i));
            newAppUser.setPassword(pwds.get(i));
            this.appUserService.createUser(newAppUser);
        }

        return "/authentification/signup";
    }
}
