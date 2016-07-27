package fr.epita.sigl.mepa.front.controller.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController extends HttpServlet{

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = {"/", "/home"})
    public String home(HttpServletRequest request) {
        if (null == request.getSession().getAttribute("isCo")){
            request.getSession().setAttribute("isCo", false);
        }
        return "/home/home";
    }

}
