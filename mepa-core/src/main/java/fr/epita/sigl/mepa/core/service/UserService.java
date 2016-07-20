package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    User getUserById(Integer id);

    List<User> getAllUsers();
}
