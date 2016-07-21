package fr.epita.sigl.mepa.front.controller.home;

import fr.epita.sigl.mepa.core.domain.CommentsModel;
import fr.epita.sigl.mepa.front.commentsmodel.AddCustomCommentsModelFormBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class HomeController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = {"/", "/home"})
    public String home() {
        return "/home/home";
    }


}
