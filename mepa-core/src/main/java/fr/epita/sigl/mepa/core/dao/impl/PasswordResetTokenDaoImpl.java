package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.PasswordResetTokenDao;
import fr.epita.sigl.mepa.core.domain.PasswordResetToken;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by patrickear on 29/7/2016.
 */
@Repository
public class PasswordResetTokenDaoImpl implements PasswordResetTokenDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void create(PasswordResetToken pwdResetToken) {
        this.getSession().save(pwdResetToken);
    }

    @Override
    public PasswordResetToken getById(Long id) {
        Query query = this.getSession().getNamedQuery("PasswordResetToken.findById");
        query.setParameter("id", id);
        return (PasswordResetToken) query.uniqueResult();
    }

    @Override
    public PasswordResetToken getByToken(String token) {
        Query query = this.getSession().getNamedQuery("PasswordResetToken.findByToken");
        query.setParameter("token", token);
        return (PasswordResetToken) query.uniqueResult();
    }
}
