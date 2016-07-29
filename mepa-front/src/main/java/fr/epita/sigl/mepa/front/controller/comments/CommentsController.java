package fr.epita.sigl.mepa.front.controller.comments;

import fr.epita.sigl.mepa.core.domain.AppUser;
import fr.epita.sigl.mepa.core.domain.AppCommentsModel;
import fr.epita.sigl.mepa.core.service.CommentsModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by prosp_000 on 21/07/2016.
 */

@Controller
@SessionAttributes({CommentsController.COMMENTS_MODELS_MODEL_ATTRIBUTE})
@RequestMapping("/comment/submit") // The address of the component
public class CommentsController {
    private static final Logger LOG = LoggerFactory.getLogger(CommentsController.class);
    private int ticket = 1;

    protected static final String COMMENTS_MODELS_MODEL_ATTRIBUTE = "c_models";
    // private static final String ADD_CUSTOM_COMMENTS_MODEL_FORM_BEAN_MODEL_ATTRIBUTE = "addCustomCommentsModelFormBean";

    @Autowired
    private CommentsModelService commentsModelService;

    @RequestMapping(value = {"/", "/{projectId}"}, method = {RequestMethod.POST})
    public String processForm(HttpServletRequest request, ModelMap modelMap, HttpServletResponse response, @PathVariable Long projectId) throws IOException {

        String text = request.getParameter("userText");

        if (text != "") {

                AppUser userco = new AppUser();
                userco = (AppUser) request.getSession().getAttribute("userCo");
                modelMap.addAttribute("userco", userco);

                AppCommentsModel newAppCommentsModel = new AppCommentsModel();
                newAppCommentsModel.setData_(text);
                newAppCommentsModel.setProjectId_(projectId);
                newAppCommentsModel.setUser_(userco.getLastName() + " " + userco.getFirstName());
                this.commentsModelService.createCommentsModel(newAppCommentsModel);

                /*Helps to sort the tickets */
                newAppCommentsModel.setArriving_(ticket);
                ticket++;
            }


        response.sendRedirect("/core/preinvest/projectDisplay/" + Long.toString(projectId)+ "/comment");
        return "/preinvest/projectDisplay";
    }
}
