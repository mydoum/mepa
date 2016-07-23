package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.UserDao;
import fr.epita.sigl.mepa.core.domain.User;
import fr.epita.sigl.mepa.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void createUser(User user) {
        this.userDao.create(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return this.userDao.getById(id);
    }

    @Override
    public User getUserByFirstName(String firstName) {
        return this.userDao.getByFirstName(firstName);
    }

    @Override
    public User getUserByLastName(String lastName) {
        return this.userDao.getByLastName(lastName);
    }

    @Override
    public User getUserByLogin(String login) {
        return this.userDao.getByLastName(login);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return this.userDao.getAll();
    }
}
