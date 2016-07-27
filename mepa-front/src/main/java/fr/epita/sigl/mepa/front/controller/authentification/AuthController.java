package fr.epita.sigl.mepa.front.controller.authentification;

import fr.epita.sigl.mepa.core.domain.AppUser;
import fr.epita.sigl.mepa.core.service.AppUserService;
import fr.epita.sigl.mepa.front.controller.home.HomeController;
import fr.epita.sigl.mepa.front.utilities.Tools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@RequestMapping("/authentification")
@Controller
public class AuthController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private HomeController home;

    private Tools tools = new Tools();

    @RequestMapping(value = {"/signup"}, method = {RequestMethod.GET})
    public String showSignUpPage(HttpServletRequest request, ModelMap modelMap) {
        // Checking if user is login need to handle this in the model
        AppUser userCo = (AppUser) request.getSession().getAttribute("userCo");
        Boolean isCo = (Boolean) request.getSession().getAttribute("isCo");
        if (userCo != null && isCo) { // The user in already log in
            return home.home(request);
        }

        List<AppUser> appUsers = this.appUserService.getAllUsers();
        if (appUsers.size() > 0) {
            modelMap.addAttribute("usersList", appUsers);
        }
        return "/authentification/signup";
    }

    @RequestMapping(value = {"/createUser"}, method = {RequestMethod.POST})
    public String processForm(HttpServletRequest request, ModelMap modelMap) {
        AppUser newAppUser = new AppUser();
        String birthdate = request.getParameter("birthdate");
        String firstName = request.getParameter("firstNameInput");
        String lastName = request.getParameter("lastNameInput");
        String login = request.getParameter("emailInput");
        String pwd = request.getParameter("passwordInput");

        // Change string to date
        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthdateDate = new Date();
        try {
            birthdateDate = sourceFormat.parse(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        newAppUser.setBirthDate(birthdateDate);
        newAppUser.setFirstName(firstName);
        newAppUser.setLastName(lastName);
        AppUser userTest = new AppUser();
        if (this.appUserService.getUserByLogin(login) == null)
            newAppUser.setLogin(login);
        else {
            modelMap.addAttribute("isNotCreated", true);
            return "/authentification/signup";
        }
        newAppUser.setPassword(pwd);
        newAppUser.setIsAdmin(false);

        this.appUserService.createUser(newAppUser);
        //        String msg = "Le compte a bien été créé";
//        modelMap.addAttribute("userIsCreated", msg);
        List<AppUser> appUsers = this.appUserService.getAllUsers();
        modelMap.addAttribute("usersList", appUsers);
        request.getSession().setAttribute("userCo", newAppUser);
        request.getSession().setAttribute("isCo", true);
        request.getSession().setAttribute("oneTime", true);
        modelMap.addAttribute("isCo", true);
        return home.home(request);
    }

    @RequestMapping(value = {"/resendPwd"}, method = {RequestMethod.GET})
    public String showPwd(HttpServletRequest request, ModelMap modelMap) throws ParseException {
        AppUser userCo = (AppUser) request.getSession().getAttribute("userCo");
        Boolean isCo = (Boolean) request.getSession().getAttribute("isCo");
        if (userCo != null && isCo) { // The user in already log in
            return home.home(request);
        }
        return "/authentification/resendPwd";
    }

    @RequestMapping(value = {"/resendPwd"}, method = {RequestMethod.POST})
    public String resendPwd(HttpServletRequest request, ModelMap modelMap) throws ParseException {
        Boolean isSent = false;
        String login = request.getParameter("email");
        AppUser recipient = new AppUser();
        if (this.appUserService.getUserByLogin(login) != null)
            recipient = this.appUserService.getUserByLogin(login);
        if (recipient != null) {
            try {
                String obj = "Récupération de votre mot de passe";
                String text = "Cette information est strictement privée." + "<br> Voici votre mot de passe: \""
                        + recipient.getPassword() + "\". <br><br> Cordialement, <br>l'équipe MEPA";
                isSent = tools.sendMail(recipient.getLogin(), obj, text);
                modelMap.addAttribute("isNotSent", false);
                modelMap.addAttribute("email", recipient.getLogin());
            } catch (Exception e) {
                modelMap.addAttribute("isNotSent", true);
            }
        }
        else {
            modelMap.addAttribute("isNotSent", true);
        }
        return "/authentification/resendPwd";
    }

    @RequestMapping(value = {"/signin"}, method = {RequestMethod.GET})
    public String getsignin(HttpServletRequest request, ModelMap modelMap) {
        AppUser userCo = (AppUser) request.getSession().getAttribute("userCo");
        Boolean isCo = (Boolean) request.getSession().getAttribute("isCo");
        if (userCo != null && isCo) { // The user in already log in
            return home.home(request);
        }

        List<AppUser> appUsers = this.appUserService.getAllUsers();
        if (appUsers.size() > 0) {
            modelMap.addAttribute("usersList", appUsers);
        }
        return "/authentification/signin";
    }

    @RequestMapping(value = {"/signin"}, method = {RequestMethod.POST})
    public String signIn(HttpServletRequest request, ModelMap modelMap) {
        AppUser userCo = (AppUser) request.getSession().getAttribute("userCo");
        Boolean isCo = (Boolean) request.getSession().getAttribute("isCo");
        if (userCo != null && isCo) { // The user in already log in
            return home.home(request);
        }
//        String login = request.getParameter("email");
//        String pwd = request.getParameter("password");

//        FIXME
        String inputLogin = request.getParameter("inputEmail");
        String inputPwd = request.getParameter("inputPassword");

        AppUser signinUser = this.appUserService.getUserByLogin(inputLogin);
        if (signinUser != null) { // the user exist
            AppUser newAppUser = this.appUserService.getUserById(signinUser.getId());
            if (StringUtils.equals(inputPwd, newAppUser.getPassword())) {
                request.getSession().setAttribute("userCo", newAppUser);
                isCo = true;
                request.getSession().setAttribute("isCo", true);
                request.getSession().setAttribute("oneTime", true);
                modelMap.addAttribute("isCo", isCo);
                return home.home(request);
            }
            else {
                modelMap.addAttribute("pwdFalse", true);
                return "/authentification/signin";
            }
        }
        else {
            modelMap.addAttribute("pwdFalse", true);
            return "/authentification/signin";
        }
    }

    @RequestMapping(value = {"/deconnexion"}, method = {RequestMethod.GET})
    public String deconnect(HttpServletRequest request, ModelMap modelMap) {

        AppUser userCo = (AppUser) request.getSession().getAttribute("userCo");
        Boolean isCo = (Boolean) request.getSession().getAttribute("isCo");
        if (userCo != null && isCo) {
            request.getSession().removeAttribute("userCo");
            request.getSession().removeAttribute("isCo");
        }
        if (request.getSession().getAttribute("oneTime") != null && (Boolean) request.getSession().getAttribute("oneTime")){
            request.getSession().removeAttribute("oneTime");
        }
        return home.home(request);
    }

    @RequestMapping(value = {"/editUser"}, method = {RequestMethod.GET})
    public String showEditUserPage(HttpServletRequest request, ModelMap modelMap) {
        AppUser userCo = (AppUser) request.getSession().getAttribute("userCo");
        Boolean isCo = (Boolean) request.getSession().getAttribute("isCo");
        if (userCo != null && isCo) {
            // tu peux afficher les données user
            return "/authentification/editUser";
        }
        if (request.getSession().getAttribute("oneTime") != null && (Boolean) request.getSession().getAttribute("oneTime")){
            request.getSession().removeAttribute("oneTime");
        }
        return home.home(request);
    }

    @RequestMapping(value = {"/editUser"}, method = {RequestMethod.POST})
    public String editUser(HttpServletRequest request, ModelMap modelMap) {
        AppUser userCo = (AppUser) request.getSession().getAttribute("userCo");
        Boolean isCo = (Boolean) request.getSession().getAttribute("isCo");

        if (userCo != null && isCo) {
            AppUser user = this.appUserService.getUserByLogin(userCo.getLogin());
            if (user != null) { // the user really exist, it's not a fake
                String firstName = request.getParameter("firstname");
                String lastName = request.getParameter("lastname");
                String login = request.getParameter("email");
                String description = request.getParameter("description");
                String address = request.getParameter("address");
                user.setFirstName(firstName);
                user.setLastName(lastName);
                if (this.appUserService.getUserByLogin(login) != null
                        && StringUtils.equalsIgnoreCase(login, this.appUserService.getUserByLogin(login).getLogin())
                        && !StringUtils.equalsIgnoreCase(this.appUserService.getUserByLogin(login).getLogin(), userCo.getLogin())){
                    modelMap.addAttribute("isNotEdited", true);
                    return "/authentification/editUser";
                }
                user.setLogin(login);
                user.setDescription(description);
                user.setAddress(address);

                this.appUserService.updateUser(user);
                request.getSession().setAttribute("userCo", user);
            }
            modelMap.addAttribute("isEdited", true);
            return "/authentification/editUser";
        }
        if (request.getSession().getAttribute("oneTime") != null && (Boolean) request.getSession().getAttribute("oneTime")){
            request.getSession().removeAttribute("oneTime");
        }
        return home.home(request);
    }

    @RequestMapping(value = {"/createAdmin"}, method = {RequestMethod.GET})
    public String createAdmin(HttpServletRequest request, ModelMap modelMap) {
        AppUser newAppUser = new AppUser();
        newAppUser.setFirstName("Tahar");
        newAppUser.setLastName("Sayagh");
        newAppUser.setLogin("admin@gmail.com");
        newAppUser.setPassword("authent");
        newAppUser.setIsAdmin(true);

        this.appUserService.createUser(newAppUser);
        List<AppUser> appUsers = this.appUserService.getAllUsers();
        modelMap.addAttribute("usersList", appUsers);
        request.getSession().setAttribute("userCo", newAppUser);
        request.getSession().setAttribute("isCo", true);
        request.getSession().setAttribute("oneTime", true);
        modelMap.addAttribute("isCo", true);
        return home.home(request);
    }

    @RequestMapping(value = {"/addFakeUser"}, method = {RequestMethod.GET})
    public String addFakeUser() {
        AppUser newAppUser = new AppUser();

        ArrayList<String> firstNames = new ArrayList<>();
        ArrayList<String> lastNames = new ArrayList<>();
        ArrayList<String> logins = new ArrayList<>();
        ArrayList<String> pwds = new ArrayList<>();
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

        return "/authentification/signin";
    }

    @RequestMapping(value = {"/checkUsers"}, method = {RequestMethod.GET})
    public String getCheckUsersPage(HttpServletRequest request, ModelMap modelMap) {
        AppUser userCo = (AppUser) request.getSession().getAttribute("userCo");
        Boolean isCo = (Boolean) request.getSession().getAttribute("isCo");
        if ((userCo == null) || !isCo || !userCo.getIsAdmin()){
            return home.home(request);
        }
        List<AppUser> appUsers = this.appUserService.getAllUsers();
        if (appUsers.size() > 0) {
            modelMap.addAttribute("usersList", appUsers);
        }
        return "/authentification/checkUsers";
    }
}
