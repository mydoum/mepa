
package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.NewsletterDAO;
import fr.epita.sigl.mepa.core.domain.NewsletterModel;
import fr.epita.sigl.mepa.core.service.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by prosp_000 on 25/07/2016.
 */

@Service
@Transactional
public class NewsletterServiceImpl implements NewsletterService
{
    @Autowired
    private NewsletterDAO newsletterDao;

    @Override
    public void createNewsletter(NewsletterModel newslettermodel) {
        this.newsletterDao.create(newslettermodel);
    }

    @Override
    public void updateNewsletter(NewsletterModel newslettermodel) {
        this.newsletterDao.update(newslettermodel);
    }

    @Override
    public void deleteNewsletter(NewsletterModel newslettermodel) {
        this.newsletterDao.delete(newslettermodel);
    }

    @Override
    @Transactional(readOnly = true)
    public NewsletterModel getNewsletterById(Long id) {
        return this.newsletterDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NewsletterModel> getAllNewsletterModels() {
        return this.newsletterDao.getAll();
    }
    //Soufiane
    @Override
    @Transactional(readOnly = true)
    public List<NewsletterModel> getAllSorted()
    {
        return this.newsletterDao.getAllSorted();
    }

}