package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.Investment;

import java.util.List;

public interface InvestmentDao {

    void create(Investment investment);

    void update(Investment investment);

    void delete(Investment investment);

    Investment getById(Long id);

    List<Investment> getAll();

}
