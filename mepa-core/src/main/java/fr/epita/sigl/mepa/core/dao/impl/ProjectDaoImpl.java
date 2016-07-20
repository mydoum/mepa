package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.InvestmentDao;
import fr.epita.sigl.mepa.core.dao.ProjectDao;
import fr.epita.sigl.mepa.core.domain.Investment;
import fr.epita.sigl.mepa.core.domain.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void create(Project project) {
        this.getSession().save(project);
    }

    @Override
    public void update(Project project) {
        this.getSession().saveOrUpdate(project);
    }

    @Override
    public void delete(Project project) {
        this.getSession().delete(project);
    }

    @Override
    public Project getById(Long id) {
        Query query = this.getSession().getNamedQuery("Project.findById");
        query.setParameter("id", id);
        return (Project) query.uniqueResult();
    }

    @Override
    public List<Project> getAll() {
        Query query = this.getSession().getNamedQuery("Project.findAll");
        return query.list();
    }
}
