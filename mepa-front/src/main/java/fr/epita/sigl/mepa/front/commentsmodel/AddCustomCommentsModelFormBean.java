package fr.epita.sigl.mepa.front.commentsmodel;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;


/**
 * Created by prosp_000 on 21/07/2016.
 */
public class AddCustomCommentsModelFormBean
{
    @NotBlank
    private String data;

    public String getData()
    {
        return this.data;
    }

    public void setData(String comment_) {
        this.data = comment_;
    }
}
