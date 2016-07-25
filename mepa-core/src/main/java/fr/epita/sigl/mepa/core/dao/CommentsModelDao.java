package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.CommentsModel;

import java.util.List;

/**
 * Created by prosp_000 on 20/07/2016.
 */
public interface CommentsModelDao 
{
    void create (CommentsModel commentsmodel);
    void update (CommentsModel commentsmodel);
    void delete (CommentsModel commentsmodel);
    CommentsModel getById (Long Id);
    List<CommentsModel> getAll();
}
