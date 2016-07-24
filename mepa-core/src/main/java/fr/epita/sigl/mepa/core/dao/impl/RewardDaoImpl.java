package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.RewardDao;
import fr.epita.sigl.mepa.core.domain.Reward;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RewardDaoImpl implements RewardDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void create(Reward reward) {
        this.getSession().save(reward);
    }

    @Override
    public void update(Reward reward) {
        this.getSession().saveOrUpdate(reward);
    }

    @Override
    public void delete(Reward reward) {
        this.getSession().delete(reward);
    }

    @Override
    public Reward getById(Long id) {
        Query query = this.getSession().getNamedQuery("Reward.findById");
        query.setParameter("id", id);
        return (Reward) query.uniqueResult();
    }

    @Override
    public List<Reward> getAll() {
        Query query = this.getSession().getNamedQuery("Reward.findAll");
        return query.list();
    }
}
