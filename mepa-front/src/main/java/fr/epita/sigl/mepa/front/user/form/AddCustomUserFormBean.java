package fr.epita.sigl.mepa.front.user.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class AddCustomUserFormBean {

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    @NotBlank
    String lastName;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    @NotBlank
    String firstName;

    @Email
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    @NotBlank
    private String email;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    @NotBlank
    String password;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    @NotBlank
    String cfmpassword;

    @Past
    @NotBlank
    Date birthdate;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCfmpassword() {
        return cfmpassword;
    }

    public void setCfmpassword(String cfmpassword) {
        this.cfmpassword = cfmpassword;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthday) {
        this.birthdate = birthday;
    }
}
