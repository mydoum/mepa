package fr.epita.sigl.mepa.front.Service;

import fr.epita.sigl.mepa.core.domain.AppUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Simon on 27/07/2016.
 */
public class AuthentificationFrontService {
    public AuthentificationFrontService() {

    }

    public boolean isUserValid(AppUser signinUser, String inputPwd, HttpServletRequest request, ModelMap modelMap) {
        if (signinUser != null) { // the user exist
            if (StringUtils.equals(inputPwd, signinUser.getPassword())) {
                request.getSession().setAttribute("userCo", signinUser);
                boolean isCo = true;
                request.getSession().setAttribute("isCo", true);
                request.getSession().setAttribute("oneTime", true);
                modelMap.addAttribute("isCo", isCo);
                return true;
            }
            else {
                modelMap.addAttribute("pwdFalse", true);
                return false;
            }
        }
        else {
            modelMap.addAttribute("pwdFalse", true);
            return false;
        }
    }

}
