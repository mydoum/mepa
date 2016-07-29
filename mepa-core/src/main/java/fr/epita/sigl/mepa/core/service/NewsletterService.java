package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.NewsletterModel;

import java.util.List;

/**
 * Created by prosp_000 on 25/07/2016.
 */
public interface NewsletterService {
    void createNewsletter(NewsletterModel newslettermodel);
    void updateNewsletter(NewsletterModel newslettermodel);
    void deleteNewsletter(NewsletterModel newslettermodel);
    NewsletterModel getNewsletterById(Long id);
    List<NewsletterModel> getAllNewsletterModels();
    List<NewsletterModel> getAllSorted();

}
