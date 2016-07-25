package fr.epita.sigl.mepa.core.service.impl;


import fr.epita.sigl.mepa.core.dao.InvestmentDao;
import fr.epita.sigl.mepa.core.dao.ModelDao;
import fr.epita.sigl.mepa.core.domain.AppUser;
import fr.epita.sigl.mepa.core.domain.Investment;
import fr.epita.sigl.mepa.core.domain.Model;
import fr.epita.sigl.mepa.core.domain.Project;
import fr.epita.sigl.mepa.core.domain.AppUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.SocketUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class InvestmentServiceImplUTests {

    @Mock
    InvestmentDao mockedInvestDao;

    @InjectMocks
    InvestmentServiceImpl investmentService;

    @Test
    public void createInvestment_ShouldCreateANewInvestment_WithDateVeryCloseToNow() {
        // Given
        Investment investToCreate = new Investment();
        Date now = new Date();
        long deltaInMilliseconds = 500;

        // When
        investmentService.createInvestment(investToCreate);

        // Then
        assertThat(investToCreate.getCreated()).isCloseTo(now, deltaInMilliseconds);
    }

    @Test
    public void createInvestment_ShouldCreateANewInvestment_UsingInvestmentDao() {
        // Given
        Investment investToCreate = new Investment();

        // When
        investmentService.createInvestment(investToCreate);

        // Then
        verify(mockedInvestDao).create(investToCreate);
    }


    @Test
    public void investmentTest() throws ParseException {
        /**
         * Create a user, a project, an investment, set the variables
         * and get them to test that they been recorded properly
         */


        AppUser testUser = new AppUser();
        String testLogin = "monlogin";
        testUser.setLogin(testLogin);
        String testPassword = "monpassword";
        testUser.setPassword(testPassword);
        String prenom = "hugo";
        testUser.setFirstName(prenom);
        String nom = "capes";
        testUser.setLastName(nom);


        Long monId = 888888888L;
        testUser.setId(monId);



        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
        String d = "2016-02-21";
        String e = "2017-05-12";
        Date debutDate = sdfDate.parse(d);
        Date stopDate = sdfDate.parse(e);
        System.out.println(debutDate.toString());
        Project testProject = new Project(monId, "projet de test", stopDate);
        testProject.setStartDate(debutDate);
        String testDescription = "description du projet de test";
        testProject.setDescription(testDescription);
        testProject.setId(monId);

        Investment testInvest = new Investment();
        testInvest.setId(monId);
        Float testAmount = 12345F;
        testInvest.setAmount(testAmount);
        String f = "2002-02-02";
        Date investDate = sdfDate.parse(f);
        testInvest.setDate(investDate);
        Long testProjectId = 77777L;
        testInvest.setProjectId(testProjectId);
        testInvest.setId(monId);
        Integer testVersion = 14;
        testInvest.setVersion(testVersion);
        String g = "2001-01-01";
        Date testCreated = sdfDate.parse(g);
        testInvest.setCreated(testCreated);
        boolean testAnonymous = false;
        testInvest.setAnonymous(testAnonymous);
        String testStringDate = "ma string date";
        testInvest.setStringDate(testStringDate);


        /**
         * Displaying all the variables
         */
        String rString1 = testUser.getLogin();
        assertThat(testUser.getLogin().equals("monlogin"));
        assertThat(testUser.getPassword().equals("monpassword"));
        assertThat(testUser.getFirstName().equals(prenom));
        assertThat(testUser.getLastName().equals(nom));
        assertThat(testUser.getId().equals(monId));

        assertThat(testProject.getId().equals(monId));
        assertThat(testProject.getUser_id().equals(monId));
        assertThat(testProject.getStartDate().equals(debutDate));
        assertThat(testProject.getDescription().equals(testDescription));

        assertThat(testInvest.getId().equals(monId));
        assertThat(testInvest.getAmount().equals(testAmount));
        assertThat(testInvest.getDate().equals(investDate));
        assertThat(testInvest.getProjectId().equals(testProjectId));
        assertThat(testInvest.getVersion().equals(testVersion));
        assertThat(testInvest.getCreated().equals(testCreated));
        assertThat(!testInvest.isAnonymous());
        assertThat(testInvest.getStringDate().equals(testStringDate));



        /**
        * System.out.print("User Login =" + rString1);
        rString1 = testUser.getPassword();
        System.out.println("Password =" + rString1);
        rString1 = testUser.getFirstName();
        System.out.println("Prénom =" + rString1);
        rString1 = testUser.getLastName();
        System.out.println("Nom =" + rString1);
        System.out.println("Date de naissance =" + testUser.getBirthDate());
        System.out.println("userId =" + testUser.getId());
        System.out.println(" =" + rString1);
         **/

    }
}




