package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.PasswordResetTokenDao;
import fr.epita.sigl.mepa.core.domain.PasswordResetToken;
import fr.epita.sigl.mepa.core.service.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by patrickear on 29/7/2016.
 */
@Service
@Transactional
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    @Autowired
    private PasswordResetTokenDao pwdResetTokenDao;

    @Override
    public void createPwdResetToken(PasswordResetToken pwdResetToken) {
        this.pwdResetTokenDao.create(pwdResetToken);
    }

    @Override
    public PasswordResetToken getUserByToken(String token) {
        return this.pwdResetTokenDao.getByToken(token);
    }
}
