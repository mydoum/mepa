package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.User;

import java.util.List;

public interface UserDao {

    void create(User user);

    User getById(Long id);

    User getByFirstName(String firstName);

    User getByLastName(String lastName);

    List<User> getAll();

}
