package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.RewardDao;
import fr.epita.sigl.mepa.core.domain.Reward;
import fr.epita.sigl.mepa.core.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RewardServiceImpl implements RewardService {

    @Autowired
    private RewardDao rewardDao;

    @Override
    public void createReward(Reward reward) {
        this.rewardDao.create(reward);
    }

    @Override
    public void updateReward(Reward reward) {
        this.rewardDao.update(reward);
    }

    @Override
    public void deleteReward(Reward reward) {
        this.rewardDao.delete(reward);
    }

    @Override
    @Transactional(readOnly = true)
    public Reward getRewardById(Long id) {
        return this.rewardDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reward> getAllRewards() {
        return this.rewardDao.getAll();
    }
}