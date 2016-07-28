package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.AppCommentsModel;

import java.util.List;

/**
 * Created by prosp_000 on 20/07/2016.
 */
public interface CommentsModelDao 
{
    void create (AppCommentsModel commentsmodel);
    void update (AppCommentsModel commentsmodel);
    void delete (AppCommentsModel commentsmodel);
    AppCommentsModel getById (Long Id);
    List<AppCommentsModel> getAll();
}
