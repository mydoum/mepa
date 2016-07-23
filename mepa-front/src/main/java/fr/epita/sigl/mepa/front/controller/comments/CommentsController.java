package fr.epita.sigl.mepa.front.controller.comments;

import fr.epita.sigl.mepa.core.domain.CommentsModel;
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

/**
 * Created by prosp_000 on 21/07/2016.
 */

@Controller
@SessionAttributes({CommentsController.COMMENTS_MODELS_MODEL_ATTRIBUTE})
@RequestMapping("/comment/submit") // The address of the component
public class CommentsController {
    private static final Logger LOG = LoggerFactory.getLogger(CommentsController.class);

    protected static final String COMMENTS_MODELS_MODEL_ATTRIBUTE = "c_models";
    // private static final String ADD_CUSTOM_COMMENTS_MODEL_FORM_BEAN_MODEL_ATTRIBUTE = "addCustomCommentsModelFormBean";

    @Autowired
    private CommentsModelService commentsModelService;

    @RequestMapping(value = {"/", "/{projectId}"}, method = {RequestMethod.POST})
    public String processForm(HttpServletRequest request, ModelMap modelMap, HttpServletResponse response, @PathVariable String projectId) throws IOException {


        CommentsModel newCommentsModel = new CommentsModel();
        String text = request.getParameter("userText");
        newCommentsModel.setData(text);
        this.commentsModelService.createCommentsModel(newCommentsModel);
        System.out.println("INSIDE");
        response.sendRedirect("/core/preinvest/projectDisplay/" + projectId);
        return "/preinvest/projectDisplay";
    }
}