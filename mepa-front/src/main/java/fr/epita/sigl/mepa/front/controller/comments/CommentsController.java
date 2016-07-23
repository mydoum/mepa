package fr.epita.sigl.mepa.front.controller.comments;

import fr.epita.sigl.mepa.core.domain.CommentsModel;
import fr.epita.sigl.mepa.core.service.CommentsModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by prosp_000 on 21/07/2016.
 */

@Controller
@SessionAttributes({CommentsController.COMMENTS_MODELS_MODEL_ATTRIBUTE})

public class CommentsController
{
    private static final Logger LOG = LoggerFactory.getLogger(CommentsController.class);

    protected static final String COMMENTS_MODELS_MODEL_ATTRIBUTE = "c_models";
  // private static final String ADD_CUSTOM_COMMENTS_MODEL_FORM_BEAN_MODEL_ATTRIBUTE = "addCustomCommentsModelFormBean";

    @Autowired
    private CommentsModelService commentsModelService;



    @RequestMapping(value = {"/comment"}, method = {RequestMethod.GET})
    public String showForm(HttpServletRequest request, ModelMap modelMap) {

        /*try {
            this.createRandomModel();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        // Get models data from database
        List<CommentsModel> c_models = this.commentsModelService.getAllCommentsModels();
       if (LOG.isDebugEnabled()) {
            LOG.debug("There are {} models in database", c_models.size());
        }

        // Update model attribute "models", to use it in JSP
        modelMap.addAttribute(COMMENTS_MODELS_MODEL_ATTRIBUTE, c_models);

       return "/comments/core/comment_form";
    }

    /**
     * @param request
     * @param modelMap
     * @param //result
     * @return
     */
   /* @RequestMapping(value = {"/comment/submit"}, method = {RequestMethod.POST})
    public String processForm(HttpServletRequest request, ModelMap modelMap,
                              @Valid AddCustomCommentsModelFormBean addCustomModelFormBean, BindingResult result) {

        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/comments/core/comment_form";
        }

        CommentsModel newCommentsModel = new CommentsModel();
        newCommentsModel.setData(addCustomModelFormBean.getData());
        this.commentsModelService.createCommentsModel(newCommentsModel);
        System.out.println("la data de mon model est : " + newCommentsModel.getData());


        List<CommentsModel> new_c_models = this.commentsModelService.getAllCommentsModels();

        modelMap.addAttribute("new_c_models",new_c_models);

        if (LOG.isDebugEnabled()) {
            LOG.debug("HAHAHAHAHAHHA There are {} models in database", new_c_models.size());
        }
        return "/comments/core/comment_form";
    }*/
    @RequestMapping(value = {"/comment/submit"}, method = {RequestMethod.POST})
    public String processForm(HttpServletRequest request, ModelMap modelMap) {


        CommentsModel newCommentsModel = new CommentsModel();
        String text = request.getParameter("userText");
        System.out.println(text);
        newCommentsModel.setData(text);
        this.commentsModelService.createCommentsModel(newCommentsModel);
        System.out.println("la data de mon model est : " + newCommentsModel.getData());


        List<CommentsModel> new_c_models = this.commentsModelService.getAllCommentsModels();

        modelMap.addAttribute("new_c_models",new_c_models);

        if (LOG.isDebugEnabled()) {
            LOG.debug("HAHAHAHAHAHHA There are {} models in database", new_c_models.size());
        }
        return "/preinvest/projectDisplay";
    }

    /**essaie avec @ModelAttribute("addCustomCommentsFormBean") CommentsModel comment
     * Create a random model and add it in database.Après le @Valid
     À la place de l'autre AddCustomCommentsFormBean
     */
    /*private void createRandomModel() {
        CommentsModel newModel = new CommentsModel();
        newModel.setData(RandomStringUtils.randomAlphanumeric(42));
        this.commentsModelService.createCommentsModel(newModel);
    }*/

    /**
     * Initialize "models" model attribute
     *
     * @return an empty List of Models.
     */
    /*@ModelAttribute(COMMENTS_MODELS_MODEL_ATTRIBUTE)
    public List<CommentsModel> initCommentsModels() {
        return new ArrayList<CommentsModel>();
    }
*/
    /**
     * Initialize "addCustomModelFormBean" model attribute
     *
     * @return a new AddCustomModelFormBean.
     */
    /*@ModelAttribute(ADD_CUSTOM_COMMENTS_MODEL_FORM_BEAN_MODEL_ATTRIBUTE)
    public AddCustomCommentsModelFormBean initAddCustomCommentsModelFormBean() {
        return new AddCustomCommentsModelFormBean();
    }*/
}
