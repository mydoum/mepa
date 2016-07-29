package fr.epita.sigl.mepa.core.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by patrickear on 29/7/2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "PasswordResetToken.findById", query = "FROM PasswordResetToken o WHERE o.id=:id"),
        @NamedQuery(name = "PasswordResetToken.findByToken", query = "FROM PasswordResetToken o WHERE o.token=:token"),
})
public class PasswordResetToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "token", unique = true)
    private String token;

    @Column(name = "login")
    private String login;

    @Column(name = "expireDate")
    private Date expiryDate;

    public String getToken() { return this.token; }

    public String getLogin() { return this.login; }

    public Date getExpiryDate() { return this.expiryDate; }

    public void setToken(String token) { this.token = token;}

    public void setLogin(String login) { this.login = login; }

    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }

}