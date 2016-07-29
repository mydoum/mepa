package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.PasswordResetToken;

/**
 * Created by patrickear on 29/7/2016.
 */
public interface PasswordResetTokenDao {
    void create(PasswordResetToken pwdResetToken);

    PasswordResetToken getById(Long id);

    PasswordResetToken getByToken(String token);
}
