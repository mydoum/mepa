package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.CommentsModel;

import java.util.List;

/**
 * Created by prosp_000 on 20/07/2016.
 */
public interface CommentsModelService
{
    void createCommentsModel(CommentsModel commentsmodel);
    void updateCommentsModel(CommentsModel commentsmodel);
    void deleteCommentsModel(CommentsModel commentsmodel);
    CommentsModel getCommentsModelById(Long id);
    List<CommentsModel> getAllCommentsModels();
}
