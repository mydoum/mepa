package fr.epita.sigl.mepa.front.model.commentsmodel;

import org.hibernate.validator.constraints.NotBlank;


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
