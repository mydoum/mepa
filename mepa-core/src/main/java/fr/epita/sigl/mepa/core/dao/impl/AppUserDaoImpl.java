package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.AppUserDao;
import fr.epita.sigl.mepa.core.domain.AppUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppUserDaoImpl implements AppUserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void create(AppUser appUser) {
        this.getSession().save(appUser);
    }

    @Override
    public void update(AppUser appUser) { this.getSession().saveOrUpdate(appUser);}

    @Override
    public AppUser getById(Long id) {
        Query query = this.getSession().getNamedQuery("AppUser.findById");
        query.setParameter("id", id);
        return (AppUser) query.uniqueResult();
    }

    @Override
    public List<AppUser> getByFirstName(String firstName) {
        Query query = this.getSession().getNamedQuery("AppUser.findByFirstName");
        query.setParameter("firstName", firstName);
        return query.list();
    }

    @Override
    public List<AppUser> getByLastName(String lastName) {
        Query query = this.getSession().getNamedQuery("AppUser.findByLastName");
        query.setParameter("lastName", lastName);
        return query.list();
    }

    @Override
    public AppUser getByLogin(String login) {
        Query query = this.getSession().getNamedQuery("AppUser.findByLogin");
        query.setParameter("login", login);
        return (AppUser) query.uniqueResult();
    }

    @Override
    public List<AppUser> getAll() {
        Query query = this.getSession().getNamedQuery("AppUser.findAll");
        return query.list();
    }
}
