package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    User getUserById(Long id);

    List<User> getUserByFirstName(String firstName);

    List<User> getUserByLastName(String lastName);

    User getUserByLogin(String login);

    List<User> getAllUsers();
}
