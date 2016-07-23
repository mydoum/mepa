package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.UserDao;
import fr.epita.sigl.mepa.core.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void create(User user) {
        this.getSession().save(user);
    }

    @Override
    public User getById(Long id) {
        Query query = this.getSession().getNamedQuery("User.findById");
        query.setParameter("id", id);
        return (User) query.uniqueResult();
    }

    @Override
    public User getByFirstName(String firstName) {
        Query query = this.getSession().getNamedQuery("User.findByFirstName");
        query.setParameter("firstName", firstName);
        return (User) query.uniqueResult();
    }

    @Override
    public User getByLastName(String lastName) {
        Query query = this.getSession().getNamedQuery("User.findByLastName");
        query.setParameter("lastName", lastName);
        return (User) query.uniqueResult();
    }

    @Override
    public User getByLogin(String login) {
        Query query = this.getSession().getNamedQuery("User.findByLogin");
        query.setParameter("login", login);
        return (User) query.uniqueResult();
    }

    @Override
    public List<User> getAll() {
        Query query = this.getSession().getNamedQuery("User.findAll");
        return query.list();
    }
}
