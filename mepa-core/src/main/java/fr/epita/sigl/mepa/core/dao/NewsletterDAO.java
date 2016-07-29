package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.NewsletterModel;

import java.util.List;

/**
 * Created by prosp_000 on 25/07/2016.
 */
public interface NewsletterDAO {
    void create (NewsletterModel newslettermodel);
    void update (NewsletterModel newslettermodel);
    void delete (NewsletterModel newslettermodel);
    NewsletterModel getById (Long Id);
    List<NewsletterModel> getAll();
    List<NewsletterModel> getAllSorted();
}

