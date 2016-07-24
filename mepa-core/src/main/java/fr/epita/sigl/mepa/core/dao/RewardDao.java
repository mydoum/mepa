package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.Reward;

import java.util.List;

public interface RewardDao {

    void create(Reward reward);

    void update(Reward reward);

    void delete(Reward reward);

    Reward getById(Long id);

    List<Reward> getAll();

}
