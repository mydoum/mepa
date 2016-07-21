package fr.epita.sigl.mepa.front.controller.comments;

import fr.epita.sigl.mepa.core.domain.CommentsModel;
import fr.epita.sigl.mepa.core.service.CommentsModelService;
import fr.epita.sigl.mepa.front.commentsmodel.AddCustomCommentsModelForBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prosp_000 on 21/07/2016.
 */

@Controller
@RequestMapping("/commnents/core")
@SessionAttributes({CommentsController.COMMENTS_MODELS_MODEL_ATTRIBUTE})

public class CommentsController
{
    private static final Logger LOG = LoggerFactory.getLogger(CommentsController.class);

    protected static final String COMMENTS_MODELS_MODEL_ATTRIBUTE = "commentsmodels";
    private static final String ADD_CUSTOM_COMMENTS_MODEL_FORM_BEAN_MODEL_ATTRIBUTE = "initAddCustomCommentsModelFormBean";

    @Autowired
    private CommentsModelService commentsModelService;



    /*@RequestMapping(value = {"/", "/form"})
    public String showForm(HttpServletRequest request, ModelMap modelMap) {
        // Get models data from database
        List<CommentsModel> commentsmodels = this.commentsModelService.getAllCommentsModels();
      /*  if (LOG.isDebugEnabled()) {
            LOG.debug("There are {} models in database", models.size());
        }*/

        // Update model attribute "models", to use it in JSP
        /*modelMap.addAttribute(COMMENTS_MODELS_MODEL_ATTRIBUTE, commentsmodels);

       return "/comments/core/form";
    }*/

    /**
     * @param request
     * @param modelMap
     * @param addCustomCommentsModelFormBean
     * @param //result
     * @return
     */
    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
    public String processForm(HttpServletRequest request, ModelMap modelMap,
                              @Valid AddCustomCommentsModelForBean addCustomCommentsModelFormBean/*, BindingResult result*/) {
        /*if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/comments/core/comment_form";
        }*/
        CommentsModel newCommentsModel = new CommentsModel();
        newCommentsModel.setData(addCustomCommentsModelFormBean.getComment());
        this.commentsModelService.createCommentsModel(newCommentsModel);

        modelMap.addAttribute("commentsmodel", newCommentsModel);

        return "/comments/core/comments_form";
    }

    /**
     * Create a random model and add it in database.
     */
   /* private void createRandomModel() {
        Model newModel = new Model();
        newModel.setData(RandomStringUtils.randomAlphanumeric(42));
        this.modelService.createModel(newModel);
    }*/

    /**
     * Initialize "models" model attribute
     *
     * @return an empty List of Models.
     */
    @ModelAttribute(COMMENTS_MODELS_MODEL_ATTRIBUTE)
    public List<CommentsModel> initCommentsModels() {
        return new ArrayList<CommentsModel>();
    }

    /**
     * Initialize "addCustomModelFormBean" model attribute
     *
     * @return a new AddCustomModelFormBean.
     */
    @ModelAttribute(ADD_CUSTOM_COMMENTS_MODEL_FORM_BEAN_MODEL_ATTRIBUTE)
    public AddCustomCommentsModelForBean initAddCustomCommentsModelFormBean() {
        return new AddCustomCommentsModelForBean();
    }
}
