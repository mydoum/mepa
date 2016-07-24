package fr.epita.sigl.mepa.front.user.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by tahar on 23/07/2016.
 */
public class LoginUserFormBean {

    @Email
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    @NotBlank
    private String email;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    @NotBlank
    String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
