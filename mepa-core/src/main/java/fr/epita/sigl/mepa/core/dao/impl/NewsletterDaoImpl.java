package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.NewsletterDAO;
import fr.epita.sigl.mepa.core.domain.CommentsModel;
import fr.epita.sigl.mepa.core.domain.NewsletterModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by prosp_000 on 25/07/2016.
 */
@Repository
public class NewsletterDaoImpl implements NewsletterDAO
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }


    @Override
    public void create(NewsletterModel newslettermodel) {
        this.getSession().save(newslettermodel);    }

    @Override
    public void update(NewsletterModel newslettermodel) {
        this.getSession().update(newslettermodel);
    }

    @Override
    public void delete(NewsletterModel newslettermodel) {
        this.getSession().delete(newslettermodel);
    }

    @Override
    public NewsletterModel getById(Long id) {
        Query query = this.getSession().getNamedQuery("Newsletter.findById");
        query.setParameter("id", id);
        return (NewsletterModel) query.uniqueResult();
    }

    @Override
    public List<NewsletterModel> getAll() {
        Query query = this.getSession().getNamedQuery("Newsletter.findAll");
        return query.list();
    }
}
