package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.InvestmentDao;
import fr.epita.sigl.mepa.core.domain.Investment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InvestmentDaoImpl implements InvestmentDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void create(Investment investment) {
        this.getSession().save(investment);
    }

    @Override
    public void update(Investment investment) {
        this.getSession().saveOrUpdate(investment);
    }

    @Override
    public void delete(Investment investment) {
        this.getSession().delete(investment);
    }

    @Override
    public Investment getById(Long id) {
        Query query = this.getSession().getNamedQuery("Investment.findById");
        query.setParameter("id", id);
        return (Investment) query.uniqueResult();
    }

    @Override
    public List<Investment> getAll() {
        Query query = this.getSession().getNamedQuery("Investment.findAll");
        return query.list();
    }
    
    @Override
    public List<Investment> getAllByProjectId(Long id){
        Query query = this.getSession().getNamedQuery("Investment.findAllByProject");
        query.setParameter("id", id);
        return query.list();
    }
}
