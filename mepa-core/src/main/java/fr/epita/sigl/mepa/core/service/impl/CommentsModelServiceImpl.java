package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.CommentsModelDao;
import fr.epita.sigl.mepa.core.domain.AppCommentsModel;
import fr.epita.sigl.mepa.core.service.CommentsModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by prosp_000 on 20/07/2016.
 */
@Service
@Transactional
public class CommentsModelServiceImpl implements CommentsModelService
{
    @Autowired
    private CommentsModelDao commentsmodelDao;

    @Override
    public void createCommentsModel(AppCommentsModel commentsmodel) {
        commentsmodel.setCreated(new Date());
        System.out.println("JE SUIS DANS LE SERVICE");
        System.out.println("la date de création de mon model est : " + commentsmodel.getCreated());
        System.out.println("la data de mon model est : " + commentsmodel.getData());
        this.commentsmodelDao.create(commentsmodel);
    }

    @Override
    public void updateCommentsModel(AppCommentsModel model) {
        this.commentsmodelDao.update(model);
    }

    @Override
    public void deleteCommentsModel(AppCommentsModel model) {
        this.commentsmodelDao.delete(model);
    }

    @Override
    @Transactional(readOnly = true)
    public AppCommentsModel getCommentsModelById(Long id) {
        return this.commentsmodelDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppCommentsModel> getAllCommentsModels() {
        return this.commentsmodelDao.getAll();
    }
}
