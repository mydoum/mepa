package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.PasswordResetToken;

/**
 * Created by patrickear on 29/7/2016.
 */
public interface PasswordResetTokenService {

    void createPwdResetToken(PasswordResetToken pwdResetToken);

    PasswordResetToken getUserByToken(String token);
}
