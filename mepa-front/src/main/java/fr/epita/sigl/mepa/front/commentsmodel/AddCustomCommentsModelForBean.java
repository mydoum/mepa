package fr.epita.sigl.mepa.front.commentsmodel;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;


/**
 * Created by prosp_000 on 21/07/2016.
 */
public class AddCustomCommentsModelForBean
{
    @Email
    @Size(max = 20)
    @NotBlank
    private String comment;

    public String getComment()
    {
        return this.comment;
    }

    public void setComment(String comment_) {
        this.comment = comment_;
    }
}
