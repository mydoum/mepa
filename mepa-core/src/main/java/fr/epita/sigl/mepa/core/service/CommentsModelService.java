package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.AppCommentsModel;

import java.util.List;

/**
 * Created by prosp_000 on 20/07/2016.
 */
public interface CommentsModelService
{
    void createCommentsModel(AppCommentsModel commentsmodel);
    void updateCommentsModel(AppCommentsModel commentsmodel);
    void deleteCommentsModel(AppCommentsModel commentsmodel);
    AppCommentsModel getCommentsModelById(Long id);
    List<AppCommentsModel> getAllCommentsModels();
}
