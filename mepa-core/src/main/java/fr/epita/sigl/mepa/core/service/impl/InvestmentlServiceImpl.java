package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.InvestmentDao;
import fr.epita.sigl.mepa.core.dao.impl.InvestmentDaoImpl;
import fr.epita.sigl.mepa.core.domain.Investment;
import fr.epita.sigl.mepa.core.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class InvestmentlServiceImpl implements InvestmentService {

    @Autowired
    private InvestmentDao investmentDao;

    @Override
    public void createInvestment(Investment investment) {
        investment.setCreated(new Date());
        this.investmentDao.create(investment);
    }

    @Override
    public void updateInvestment(Investment investment) {
        this.investmentDao.update(investment);
    }

    @Override
    public void deleteInvestment(Investment investment) {
        this.investmentDao.delete(investment);
    }

    @Override
    @Transactional(readOnly = true)
    public Investment getInvestmentById(Long id) {
        return this.investmentDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Investment> getAllInvestments() {
        return this.investmentDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Investment> getAllInvestmentsByProjectId(Long id) {
        return this.investmentDao.getAllByProjectId(id);
    }
}
