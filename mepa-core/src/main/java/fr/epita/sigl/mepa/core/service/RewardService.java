package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Reward;

import java.util.List;

public interface RewardService {

    void createReward(Reward reward);

    void updateReward(Reward reward);

    void deleteReward(Reward reward);

    Reward getRewardById(Long id);

    List<Reward> getAllRewards();

}