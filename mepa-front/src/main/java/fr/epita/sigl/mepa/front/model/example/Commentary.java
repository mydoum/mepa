package fr.epita.sigl.mepa.front.model.example;

/**
 * Created by Doc on 20/07/2016.
 */

import org.hibernate.annotations.*;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Size;
public class Commentary {

@Id
@GeneratedValue
@Size(max = 200)
 @NotBlank
    private String Comment;
    private Long id;
    public String getComment()
    {
        return this.Comment;
    }
    public Long getId()
    {
        return this.id;
    }
    public void setComment(String Comment)
    {

        this.Comment = Comment;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
}