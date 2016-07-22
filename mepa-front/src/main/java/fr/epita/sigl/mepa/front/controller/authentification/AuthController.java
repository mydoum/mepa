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
//        if (result.hasErrors()) {
//            // Error(s) in form bean validation
//            return "/example/core/form";
//        }
        User newUser = new User();
        newUser.setFirstName("toto");
        newUser.setLastName("tata");
        newUser.setLogin("test@test.com");
        newUser.setPassword("123456");

        DateFormat df = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
        String target = "27-09-1991 20:29:30";
        Date res = new Date();
        try {
//            res = df.parse(target);
//            newUser.setBirthDate(res);
        } catch (Exception e) {

        }
//        System.out.println(result);
        newUser.setBirthDate(res);
//        System.out.println("User is : " + request.getParameter("lastname"));
        this.userService.createUser(newUser);
        System.out.println("New User created!");
        System.out.println("User firstName : "+ newUser.getFirstName());
        System.out.println("User lastName : "+ newUser.getLastName());
        System.out.println("User login : "+ newUser.getLogin());
        System.out.println("User password : "+ newUser.getPassword());
//
        System.out.println("User birthdate : "+ newUser.getBirthDate());
        return "/home/home";

//        newUser.setPassword(addCustomUserFormBean.getCfmpassword());
//        newUser.setBirthDate(addCustomUserFormBean.getBirthdate());
//        this.userService.createUser(newUser);
//        System.out.println("New User created!");
//        System.out.println("User firstName : "+ newUser.getFirstName());
//        System.out.println("User lastName : "+ newUser.getLastName());
//        System.out.println("User login : "+ newUser.getLogin());
//        System.out.println("User password : "+ newUser.getPassword());
//        System.out.println("User birthdate : "+ newUser.getBirthDate().toString());
//
//        modelMap.addAttribute("user", newUser);
//
//        System.out.println("test");
//
//        return "/home/home";
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
    public String processForm(HttpServletRequest request, ModelMap modelMap) {
        String test = request.getParameter("birthdate");
        System.out.println(test);

        return "/home/home";

    }


}
