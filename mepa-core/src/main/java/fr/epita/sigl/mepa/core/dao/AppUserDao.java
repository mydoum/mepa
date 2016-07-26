package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.AppUser;

import java.util.List;

public interface AppUserDao {

    void create(AppUser appUser);
    void update(AppUser appUser);

    AppUser getById(Long id);

    List<AppUser> getByFirstName(String firstName);

    List<AppUser> getByLastName(String lastName);

    AppUser getByLogin(String login);

    List<AppUser> getAll();

}
