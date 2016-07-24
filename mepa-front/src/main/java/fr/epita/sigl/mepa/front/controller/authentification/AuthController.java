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
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
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

    @RequestMapping(value = "/filltables", method = RequestMethod.GET)
    public String fillTables(ModelMap model, HttpSession session, HttpServletRequest request) {
        String a = "";
        for (int i = 0; i < 10; i++) {
            a += "0";
            AppUser appUser = new AppUser();
            appUser.setFirstName("Tahar");
            appUser.setLastName("Sayagh");
            appUser.setLogin("tahar.sayagh" + a + "@gmail.com");
            appUser.setPassword("authent");
            Date date = new Date();
            appUser.setBirthDate(date);
            this.appUserService.createUser(appUser);
        }
        List<AppUser> appUsers = this.appUserService.getAllUsers();
        model.addAttribute("usersList", appUsers);
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
        generateMailMessage.setSubject("Greetings " + userFirstName + " " + userLastName);
        String emailBody = "This information is strictly private." + "<br> Here is your password: \""
                + recipient.getPassword() + "\". <br><br> Regards, <br>MEPA Team";
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
    public String signIn(HttpServletRequest request, ModelMap modelMap,
                         @Valid LoginUserFormBean loginUserFormBean, BindingResult result) {
        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/example/core/form";
        }
        Boolean isCo = false;
        if (this.appUserService.getUserByLogin(loginUserFormBean.getEmail()) != null) {
            AppUser newAppUser = this.appUserService.getUserById(this.appUserService.getUserByLogin(loginUserFormBean.getEmail()).getId());
            if (StringUtils.equals(loginUserFormBean.getPassword(), newAppUser.getPassword())) {
                request.getSession().setAttribute("userCo", newAppUser);
                isCo = true;
                modelMap.addAttribute("isCo", isCo);
                return "/home";
            }
        } else {
            modelMap.addAttribute("isCo", isCo);
            return "authentification/signin";
        }
        return "authentification/signin";
    }

}
