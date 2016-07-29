package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Investment;

import java.util.List;

public interface InvestmentService {

    void createInvestment(Investment investment);

    void updateInvestment(Investment investment);

    void deleteInvestment(Investment investment);

    Investment getInvestmentById(Long id);

    List<Investment> getAllInvestmentsByProjectId(Long id);

    List<Investment> getAllInvestments();

    List<Investment> getAllInvestmentsByRewardId(Long projectId, Long rewardId);
    
    void dumpAllInvestmentsByProject(Long id);

}
