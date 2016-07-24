package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.AppUserDao;
import fr.epita.sigl.mepa.core.domain.AppUser;
import fr.epita.sigl.mepa.core.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserDao appUserDao;

    @Override
    public void createUser(AppUser appUser) {
        this.appUserDao.create(appUser);
    }

    @Override
    @Transactional(readOnly = true)
    public AppUser getUserById(Long id) {
        return this.appUserDao.getById(id);
    }

    @Override
    public List<AppUser> getUserByFirstName(String firstName) {
        return this.appUserDao.getByFirstName(firstName);
    }

    @Override
    public List<AppUser> getUserByLastName(String lastName) {
        return this.appUserDao.getByLastName(lastName);
    }

    @Override
    public AppUser getUserByLogin(String login) {
        return this.appUserDao.getByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppUser> getAllUsers() {
        return this.appUserDao.getAll();
    }
}
