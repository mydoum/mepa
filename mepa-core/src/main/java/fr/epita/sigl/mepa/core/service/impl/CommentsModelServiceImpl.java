package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.CommentsModelDao;
import fr.epita.sigl.mepa.core.dao.ModelDao;
import fr.epita.sigl.mepa.core.domain.CommentsModel;
import fr.epita.sigl.mepa.core.domain.Model;
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
    public void createCommentsModel(CommentsModel commentsmodel) {
        commentsmodel.setCreated(new Date());
        this.commentsmodelDao.create(commentsmodel);
    }

    @Override
    public void updateCommentsModel(CommentsModel model) {
        this.commentsmodelDao.update(model);
    }

    @Override
    public void deleteCommentsModel(CommentsModel model) {
        this.commentsmodelDao.delete(model);
    }

    @Override
    @Transactional(readOnly = true)
    public CommentsModel getCommentsModelById(Long id) {
        return this.commentsmodelDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentsModel> getAllCommentsModels() {
        return this.commentsmodelDao.getAll();
    }
}
