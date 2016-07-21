package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.CommentsModelDao;
import fr.epita.sigl.mepa.core.domain.CommentsModel;
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
    public void create(CommentsModel commentsmodel) {
        this.getSession().save(commentsmodel);
    }

    @Override
    public void update(CommentsModel commentsmodel) {
        this.getSession().saveOrUpdate(commentsmodel);
    }

    @Override
    public void delete(CommentsModel commentsmodel) {
        this.getSession().delete(commentsmodel);
    }

    @Override
    public CommentsModel getById(Long id) {
        Query query = this.getSession().getNamedQuery("CommentsModel.findById");
        query.setParameter("id", id);
        return (CommentsModel) query.uniqueResult();
    }

    @Override
    public List<CommentsModel> getAll() {
        Query query = this.getSession().getNamedQuery("Model.findAll");
        return query.list();
    }
}
