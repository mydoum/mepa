package fr.epita.sigl.mepa.front.user.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by tahar on 23/07/2016.
 */
public class resendPwdFormBean {

    @Email
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    @NotBlank
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
