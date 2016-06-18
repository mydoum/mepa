package fr.epita.sigl.mepa.front.model.example;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class AddCustomModelFormBean {

    @Email
    @Size(max = 20)
    @NotBlank
    private String email;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
