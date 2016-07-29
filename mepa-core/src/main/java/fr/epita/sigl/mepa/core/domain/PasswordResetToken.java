package fr.epita.sigl.mepa.core.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by patrickear on 29/7/2016.
 */
@Entity
public class PasswordResetToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = AppUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "login")
    private AppUser user;

    private Date expiryDate;
}