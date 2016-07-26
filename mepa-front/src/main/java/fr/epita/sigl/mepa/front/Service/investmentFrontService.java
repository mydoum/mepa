package fr.epita.sigl.mepa.front.Service;

import fr.epita.sigl.mepa.core.domain.AppUser;
import fr.epita.sigl.mepa.core.domain.Investment;
import fr.epita.sigl.mepa.core.domain.Project;
import fr.epita.sigl.mepa.core.service.AppUserService;
import fr.epita.sigl.mepa.core.service.InvestmentService;
import fr.epita.sigl.mepa.front.model.investment.Investor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Simon on 26/07/2016.
 */
@Service
public class investmentFrontService {

    public investmentFrontService() {

    }

    @Autowired
    private InvestmentService investmentService;
    @Autowired
    private AppUserService appUserService;


}
