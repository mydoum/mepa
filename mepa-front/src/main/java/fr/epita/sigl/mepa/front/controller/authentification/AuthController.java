package fr.epita.sigl.mepa.front.controller.authentification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by patrickear on 21/7/2016.
 */
@RequestMapping("/authentification")
@Controller
public class AuthController {

    @RequestMapping(value = {"/auth"})
    public String showAuth() {
        return "/authentification/signup";
    }

}
