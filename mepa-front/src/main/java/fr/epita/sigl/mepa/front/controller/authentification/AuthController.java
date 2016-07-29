package fr.epita.sigl.mepa.front.controller.authentification;

import fr.epita.sigl.mepa.core.domain.AppUser;
import fr.epita.sigl.mepa.core.domain.PasswordResetToken;
import fr.epita.sigl.mepa.core.service.AppUserService;
import fr.epita.sigl.mepa.core.service.PasswordResetTokenService;
import fr.epita.sigl.mepa.core.service.impl.PasswordResetTokenServiceImpl;
import fr.epita.sigl.mepa.front.Service.AuthentificationFrontService;
import fr.epita.sigl.mepa.front.controller.core.preinvest.ProjectDisplayController;
import fr.epita.sigl.mepa.front.controller.home.HomeController;
import fr.epita.sigl.mepa.front.utilities.Tools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/authentification")
@Controller
public class AuthController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    PasswordResetTokenService pwdResetTokenService;

    @Autowired
    private HomeController home;

    @Autowired
    private ProjectDisplayController projectDisplayController;

    private Tools tools = new Tools();
    private AuthentificationFrontService authentificationFrontService = new AuthentificationFrontService();

    private int NB_VIEWINSCRIPTION = 1;
    private int NB_INSCRIPTION = 1;
    private int NB_VIEWLOGIN = 1;
    private int NB_LOGIN = 1;
    private int NB_VIEWFORGET = 1;
    private int NB_FORGET = 1;
    private int NB_VIEWEDITUSER = 1;
    private int NB_EDITUSER = 1;

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
        if (birthdate != "") {
            Date birthdateDate = new Date();
            try {
                birthdateDate = sourceFormat.parse(birthdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            newAppUser.setBirthDate(birthdateDate);
//            System.out.println("birthday = " + birthdateDate.toString() +  " !!!!!!!!!!!!! ");
        }

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
        System.out.print("user id at creation ======== " + newAppUser.getId());
        //        String msg = "Le compte a bien été créé";
//        modelMap.addAttribute("userIsCreated", msg);
        List<AppUser> appUsers = this.appUserService.getAllUsers();
        modelMap.addAttribute("usersList", appUsers);
        request.getSession().setAttribute("userCo", newAppUser);
        request.getSession().setAttribute("isCo", true);
        request.getSession().setAttribute("oneTime", true);
        modelMap.addAttribute("isCo", true);
        request.getSession().setAttribute("nbInscription", NB_INSCRIPTION++);
        request.getSession().setAttribute("nbLogin", NB_LOGIN++);
        return home.home(request);
    }

    @RequestMapping(value = {"/resendPwd"}, method = {RequestMethod.GET})
    public String showPwd(HttpServletRequest request, ModelMap modelMap) throws ParseException {
        AppUser userCo = (AppUser) request.getSession().getAttribute("userCo");
        Boolean isCo = (Boolean) request.getSession().getAttribute("isCo");
        if (userCo != null && isCo) { // The user in already log in
            return home.home(request);
        }
        request.getSession().setAttribute("nbViewForget", NB_VIEWFORGET++);
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
        } else {
            modelMap.addAttribute("isNotSent", true);
        }
        request.getSession().setAttribute("nbForget", NB_FORGET++);
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
        request.getSession().setAttribute("nbViewLogin", NB_VIEWLOGIN++);
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
        if (authentificationFrontService.isUserValid(signinUser, inputPwd, request, modelMap)) {
            request.getSession().setAttribute("nbLogin", NB_LOGIN++);
            return home.home(request);
        } else {
            return "/authentification/signin";
        }
    }

    @RequestMapping(value = "/signin/project/{projectId}", method = RequestMethod.POST)
    public String signInProject(HttpServletRequest request, ModelMap modelMap, @PathVariable long projectId) {
        AppUser userCo = (AppUser) request.getSession().getAttribute("userCo");
        Boolean isCo = (Boolean) request.getSession().getAttribute("isCo");
        if (userCo != null && isCo) { // The user in already log in
            return home.home(request);
        }

        String inputLogin = request.getParameter("inputEmail");
        String inputPwd = request.getParameter("inputPassword");

        AppUser signinUser = this.appUserService.getUserByLogin(inputLogin);
        if (authentificationFrontService.isUserValid(signinUser, inputPwd, request, modelMap)) {
            request.getSession().setAttribute("nbLogin", NB_LOGIN++);
            return projectDisplayController.projectDisplay(request, modelMap, projectId);
        } else {
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
        if (request.getSession().getAttribute("oneTime") != null && (Boolean) request.getSession().getAttribute("oneTime")) {
            request.getSession().removeAttribute("oneTime");
        }
        return home.home(request);
    }

    @RequestMapping(value = {"/editUser"}, method = {RequestMethod.GET})
    public String showEditUserPage(HttpServletRequest request, ModelMap modelMap) {
        AppUser userCo = (AppUser) request.getSession().getAttribute("userCo");
        Boolean isCo = (Boolean) request.getSession().getAttribute("isCo");

        Date date = userCo.getBirthDate();
        if (date != null) {
            Format formatter = new SimpleDateFormat("dd/MM/yyyy");
            String birthday = formatter.format(date);
            System.out.println(birthday);
            modelMap.addAttribute("formatedBirthday", birthday);
        } else {
            modelMap.addAttribute("formatedBirthday", "");
        }

        if (userCo != null && isCo) {
            // tu peux afficher les données user
            request.getSession().setAttribute("nbViewEditUser", NB_VIEWEDITUSER++);
            return "/authentification/editUser";
        }
        if (request.getSession().getAttribute("oneTime") != null && (Boolean) request.getSession().getAttribute("oneTime")) {
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
                String birthdate = request.getParameter("birthdate");

                if (this.appUserService.getUserByLogin(login) != null
                        && StringUtils.equalsIgnoreCase(login, this.appUserService.getUserByLogin(login).getLogin())
                        && !StringUtils.equalsIgnoreCase(this.appUserService.getUserByLogin(login).getLogin(), userCo.getLogin())) {
                    modelMap.addAttribute("isNotEdited", true);
                    return this.showEditUserPage(request, modelMap);
                }
                Date birthdateDate = new Date();
                if (birthdate != "") {

                    try {
                        System.out.println("INSIDEEEEEEEEEEEE");
                        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
                        birthdateDate = sourceFormat.parse(birthdate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println("hello    ===== " + birthdateDate.toString());
                    user.setBirthDate(birthdateDate);
                } else {
//                    System.out.println("ELLLLLLLSSSSSEEEE");
                    birthdateDate = null;
                    user.setBirthDate(birthdateDate);
                }

                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setLogin(login);
                user.setAddress(address);
                user.setDescription(description);

                this.appUserService.updateUser(user);
                request.getSession().setAttribute("userCo", user);
            }
            modelMap.addAttribute("isEdited", true);
            request.getSession().setAttribute("nbEditUser", NB_EDITUSER++);
            return this.showEditUserPage(request, modelMap);
        }
        if (request.getSession().getAttribute("oneTime") != null && (Boolean) request.getSession().getAttribute("oneTime")) {
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
        if ((userCo == null) || !isCo || !userCo.getIsAdmin()) {
            return home.home(request);
        }
        List<AppUser> appUsers = this.appUserService.getAllUsers();
        if (appUsers.size() > 0) {
            modelMap.addAttribute("usersList", appUsers);
        }
        return "/authentification/checkUsers";
    }

    @RequestMapping(value = {"/exportUsers"})
    public String exportUsers(HttpServletResponse response, HttpServletRequest request, ModelMap modelMap) {
        Date actual = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(actual);
        if ((this.appUserService.getAllUsers() != null) && this.appUserService.getAllUsers().size() > 0) {
            ArrayList<AppUser> users = (ArrayList<AppUser>) this.appUserService.getAllUsers();
            String fileWriter = Tools.writeUserCsvFile(users);
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=\"Users_export_" + date + ".csv\"");
            try {
                OutputStream output = response.getOutputStream();
                output.write(fileWriter.getBytes());
                output.flush();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            modelMap.addAttribute("noUsers", true);
        }
        return "/authentification/checkUsers";
    }

    @RequestMapping(value = {"/modifyPassword"}, method = {RequestMethod.GET})
    public String showModifyPassordPage(HttpServletRequest request, ModelMap modelMap) {
        AppUser userCo = (AppUser) request.getSession().getAttribute("userCo");
        Boolean isCo = (Boolean) request.getSession().getAttribute("isCo");
        if (userCo != null && isCo) {

            return "/authentification/modifyPassword";
        }
        if (request.getSession().getAttribute("oneTime") != null && (Boolean) request.getSession().getAttribute("oneTime")) {
            request.getSession().removeAttribute("oneTime");
        }
        return home.home(request);
    }

    @RequestMapping(value = {"/modifyPassword"}, method = {RequestMethod.POST})
    public String modifyPassword(HttpServletRequest request, ModelMap modelMap) {
        AppUser userCo = (AppUser) request.getSession().getAttribute("userCo");
        Boolean isCo = (Boolean) request.getSession().getAttribute("isCo");

        System.out.println("user pwd = " + userCo.getPassword());
        if (userCo != null && isCo) {
            //AppUser user = this.appUserService.getUserByLogin(userCo.getLogin());
            if (userCo != null) { // the user really exist, it's not a fake
                String password = request.getParameter("password");
                String passwordOld = request.getParameter("passwordOld");
                String passwordConf = request.getParameter("passwordConf");
                boolean confPassword = true;
                boolean newPassword = true;
                System.out.println("login = " + userCo.getLogin());

                if (!passwordOld.equals(userCo.getPassword())) {
                    newPassword = false;
                    modelMap.addAttribute("newPassword", newPassword);
                    return "/authentification/modifyPassword";
                } else if (!password.equals(passwordConf)) {
                    confPassword = false;
                    modelMap.addAttribute("confPassword", confPassword);
                    return "/authentification/modifyPassword";
                } else {
                    userCo.setPassword(password);
                    this.appUserService.updateUser(userCo);
                    request.getSession().setAttribute("userCo", userCo);
                    newPassword = true;
                    confPassword = true;
                    if (request.getSession().getAttribute("oneTime") != null && (Boolean) request.getSession().getAttribute("oneTime")) {
                        request.getSession().removeAttribute("oneTime");
                    }
                    return home.home(request);
                }
            }
        }
        if (request.getSession().getAttribute("oneTime") != null && (Boolean) request.getSession().getAttribute("oneTime")) {
            request.getSession().removeAttribute("oneTime");
        }
        return home.home(request);
    }

    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
    public String resetPassword(
            HttpServletRequest request, @RequestParam("email") String userEmail) {

        AppUser user = appUserService.getUserByLogin(userEmail);

        if (user == null) {
            System.out.println("ALLLERTE    ====== THE USER DOES NOT EXIST");
            return home.home(request);
//            throw new UserNotFoundException();
            // ERROR
        }
        System.out.println("USER ID IN RESETPWD = " + user.getId());
        String token = UUID.randomUUID().toString();
        PasswordResetToken pwdResetToken = new PasswordResetToken();
        pwdResetToken.setLogin(user.getLogin()); // FIXME CARE
        pwdResetToken.setToken(token);
        pwdResetToken.setUserId(user.getId());
        pwdResetToken.setExpiryDate(new Date()); // FIXME
        pwdResetTokenService.createPwdResetToken(pwdResetToken);

        System.out.println("token = " + pwdResetToken.getToken());

        String appUrl =
                "http://" + request.getServerName() +
                        ":" + request.getServerPort() +
                        request.getContextPath();

        String url = appUrl + "/authentification/changePwd?id=" + user.getId() + "&token=" + token;
        try {
            boolean isSent;
            String obj = "Réinitialisation de votre mot de passe";
            String text = "Voici le lien pour r\u00E9initialiser votre mot de passe : " + url;
            isSent = tools.sendMail(user.getLogin(), obj, text);
            if (isSent) {
                System.out.println("le message a été envoyé check tes email");
            } else {
                System.out.println("Il y a eu un soucis");
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        

        return home.home(request);

    }

    @RequestMapping(value = {"/getStatistics"}, method = {RequestMethod.GET})
    public String showStatistics(HttpServletRequest request, ModelMap modelMap) {
        AppUser userCo = (AppUser) request.getSession().getAttribute("userCo");
        Boolean isCo = (Boolean) request.getSession().getAttribute("isCo");
        int nbViewInscription = 0;
        int nbInscription = 0;
        int nbViewLogin = 0;
        int nbLogin = 0;
        int nbViewForget = 0;
        int nbForget = 0;
        int nbViewEditUser = 0;
        int nbEditUser = 0;
        int nbComments = 0;
        int nbViewProjectCreate = 0;
        int nbProjectCreate = 0;
        int nbViewProject = 0;
        if (request.getSession().getAttribute("nbViewInscription") != null) {
            nbViewInscription = (Integer) request.getSession().getAttribute("nbViewInscription");
            System.out.println("VIEW INSCRIPTION = " + nbViewInscription);
        }
        if (request.getSession().getAttribute("nbInscription") != null)
            nbInscription = (Integer) request.getSession().getAttribute("nbInscription");
        if (request.getSession().getAttribute("nbViewLogin") != null)
            nbViewLogin = (Integer) request.getSession().getAttribute("nbViewLogin");
        if (request.getSession().getAttribute("nbLogin") != null)
            nbLogin = (Integer) request.getSession().getAttribute("nbLogin");
        if (request.getSession().getAttribute("nbViewForget") != null)
            nbViewForget = (Integer) request.getSession().getAttribute("nbViewForget");
        if (request.getSession().getAttribute("nbForget") != null)
            nbForget = (Integer) request.getSession().getAttribute("nbForget");
        if (request.getSession().getAttribute("nbViewEditUser") != null)
            nbViewEditUser = (Integer) request.getSession().getAttribute("nbViewEditUser");
        if (request.getSession().getAttribute("nbEditUser") != null)
            nbEditUser = (Integer) request.getSession().getAttribute("nbEditUser");
        if (request.getSession().getAttribute("nbComments") != null)
            nbComments = (Integer) request.getSession().getAttribute("nbComments");
        if (request.getSession().getAttribute("nbViewProjectCreate") != null)
            nbViewProjectCreate = (Integer) request.getSession().getAttribute("nbViewProjectCreate");
        if (request.getSession().getAttribute("nbProjectCreate") != null)
            nbProjectCreate = (Integer) request.getSession().getAttribute("nbProjectCreate");
        if (request.getSession().getAttribute("nbViewProject") != null)
            nbViewProject = (Integer) request.getSession().getAttribute("nbViewProject");
        modelMap.addAttribute("nbViewInscription", nbViewInscription);
        modelMap.addAttribute("nbInscription", nbInscription);
        modelMap.addAttribute("nbViewLogin", nbViewLogin);
        modelMap.addAttribute("nbLogin", nbLogin);
        modelMap.addAttribute("nbViewForget", nbViewForget);
        modelMap.addAttribute("nbForget", nbForget);
        modelMap.addAttribute("nbViewEditUser", nbViewEditUser);
        modelMap.addAttribute("nbEditUser", nbEditUser);
        modelMap.addAttribute("nbComments", nbComments);
        modelMap.addAttribute("nbViewProjectCreate", nbViewProjectCreate);
        modelMap.addAttribute("nbProjectCreate", nbProjectCreate);
        modelMap.addAttribute("nbViewProject", nbViewProject);
        if ((userCo == null) || !isCo || !userCo.getIsAdmin()) {
            return home.home(request);
        }
        return "/authentification/infoPage";
    }

    @RequestMapping(value = "/changePwd", method = RequestMethod.GET)
    public String showChangePasswordPage(HttpServletRequest request, ModelMap modelMap,
                                         @RequestParam("id") long id, @RequestParam("token") String token) {
        PasswordResetToken passToken = pwdResetTokenService.getUserByToken(token);
        AppUser appUser = appUserService.getUserById(id);
        if (passToken == null || appUser ==  null) { // error
            System.out.println("------- Utilisateur inconnu -------");
            return home.home(request);
        }
        System.out.println("USER ID IN PWD CHANGE = " + String.valueOf(id));

//        Calendar cal = Calendar.getInstance(); // FIXME Handle date
//        if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
//            System.out.println("------- Token périmé -------");
//            return home.home(request);
//        }
        modelMap.addAttribute("hiddenuserid", id);
        modelMap.addAttribute("toto", "test");
        return "/authentification/resetPwd";
    }

    @RequestMapping(value = "/changePwd", method = RequestMethod.POST)
    public String resetForgetPwd(HttpServletRequest request,
                                         @RequestParam("hidden-user-id") String id, @RequestParam("newPassword") String newPassword) {
        System.out.println("id = " + id);
        AppUser appUser = appUserService.getUserById(Long.valueOf(id));
        System.out.println("user = "+ appUser.getLogin());
        if (appUser != null) {
            String newPwd = newPassword;
            if (newPwd != "") {
                appUser.setPassword(newPwd);
                this.appUserService.updateUser(appUser);
                System.out.println("user pwd is now  = "+ appUser.getPassword());
            }
        }

        // Change le mot de passe et redirige vers la home
        return home.home(request);
    }

}
