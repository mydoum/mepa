package fr.epita.sigl.mepa.front.controller.authentification;

import fr.epita.sigl.mepa.core.domain.User;
import fr.epita.sigl.mepa.core.service.UserService;
import fr.epita.sigl.mepa.front.user.form.AddCustomUserFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by patrickear on 21/7/2016.
 */
@RequestMapping("/authentification")
@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/auth"})
    public String showAuth() {
        return "/authentification/signup";
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String getForm(HttpServletRequest request, ModelMap modelMap,
                              @Valid AddCustomUserFormBean addCustomUserFormBean, BindingResult result) throws ParseException {
        return "/home/home";

    }

    @RequestMapping(value = {"/addUser"}, method = {RequestMethod.POST})
    public String processForm(HttpServletRequest request, ModelMap modelMap) {
        User newUser = new User();
        String bithDate = request.getParameter("birthdate");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String login = request.getParameter("email");
        String pwd = request.getParameter("password");

        newUser.setBirthDate(new Date());
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setLogin(login);
        newUser.setPassword(pwd);

        this.userService.createUser(newUser);
        System.out.println("Create new user");

        return "/home/home";
    }

    @RequestMapping(value = {"/signin"}, method = {RequestMethod.GET})
    public String getsignin(HttpServletRequest request, ModelMap modelMap) {
        return "/authentification/signin";
    }

}
