package fr.epita.sigl.mepa.front.commentsmodel;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;


/**
 * Created by prosp_000 on 21/07/2016.
 */
public class AddCustomCommentsModelFormBean
{
    @Email
    @Size(max = 20)
    @NotBlank
    private String data;

    public String getComment()
    {
        return this.data;
    }

    public void setComment(String comment_) {
        this.data = comment_;
    }
}
