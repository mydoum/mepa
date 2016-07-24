package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.AppUser;

import java.util.List;

public interface AppUserService {

    void createUser(AppUser appUser);

    AppUser getUserById(Long id);

    List<AppUser> getUserByFirstName(String firstName);

    List<AppUser> getUserByLastName(String lastName);

    AppUser getUserByLogin(String login);

    List<AppUser> getAllUsers();
}
