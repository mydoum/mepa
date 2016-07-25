package fr.epita.sigl.mepa.core.service.impl;


import fr.epita.sigl.mepa.core.dao.AppUserDao;
import fr.epita.sigl.mepa.core.domain.AppUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.AopTestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AppUserServiceImplUTests {

    @Mock
    AppUserDao mockedAppUserDao;

    @InjectMocks
    AppUserServiceImpl appUserService;

    @Test
    public void createUser_ShouldCreateANewUser_Basic() {
        // Given
        AppUser userToCreate = new AppUser();

        // When
        appUserService.createUser(userToCreate);
        assertThat(userToCreate.getId() != null);
    }

    @Test
    public void createUser_ShouldCreateANewUser_UsingUserDao() {
        // Given
        AppUser userToCreate = new AppUser();

        // When
        appUserService.createUser(userToCreate);

        // Then
        verify(mockedAppUserDao).create(userToCreate);
    }


    @Test
    public void userTest() throws ParseException {
        /**
         * Create a user, set the variables
         * and get them to test that they been recorded properly
         */


        AppUser userToTest = new AppUser();
        String testLogin = "tahar.sayagh0@gmail.com";
        userToTest.setLogin(testLogin);

        String testPassword = "authent";
        userToTest.setPassword(testPassword);
        String prenom = "Tahar";
        userToTest.setFirstName(prenom);
        String nom = "Sayagh";
        userToTest.setLastName(nom);
        Long monId = 27L;
        userToTest.setId(monId);

        AppUser userToTest1 = new AppUser();
        String testLogin1 = "tahar.sayagh0@gmail.com";
        userToTest1.setLogin(testLogin1);

        String testPwd = "password";
        userToTest1.setPassword(testPwd);
        String firstname = "Simon";
        userToTest1.setFirstName(firstname);
        String lastname = "Mace";
        userToTest1.setLastName(lastname);
        Long idUser = 27L;
        userToTest1.setId(idUser);



        /**
         * Displaying all the variables
         */
        assertThat(userToTest.getLogin().equals("tahar.sayagh0@gmail.com"));
        assertThat(userToTest.getPassword().equals("authent"));
        assertThat(userToTest.getFirstName().equals(prenom));
        assertThat(userToTest.getLastName().equals(nom));
        assertThat(userToTest.getId().equals(monId));

    }
}




