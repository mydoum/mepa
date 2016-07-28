package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.CommentsModelDao;
import fr.epita.sigl.mepa.core.domain.AppCommentsModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by prosp_000 on 20/07/2016.
 */
@Repository
public class CommentsDaoImpl implements CommentsModelDao
{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void create(AppCommentsModel commentsmodel) {
        this.getSession().save(commentsmodel);
    }

    @Override
    public void update(AppCommentsModel commentsmodel) {
        this.getSession().saveOrUpdate(commentsmodel);
    }

    @Override
    public void delete(AppCommentsModel commentsmodel) {
        this.getSession().delete(commentsmodel);
    }

    @Override
    public AppCommentsModel getById(Long id) {
        Query query = this.getSession().getNamedQuery("AppCommentsModel.findById");
        query.setParameter("id", id);
        return (AppCommentsModel) query.uniqueResult();
    }

    @Override
    public List<AppCommentsModel> getAll() {
        Query query = this.getSession().getNamedQuery("AppCommentsModel.findAll");
        return query.list();
    }
}
