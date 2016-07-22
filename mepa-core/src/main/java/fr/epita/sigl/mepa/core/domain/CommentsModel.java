package fr.epita.sigl.mepa.core.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by prosp_000 on 20/07/2016.
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "CommentsModel.findById", query = "FROM CommentsModel o WHERE o.id=:id"),
        @NamedQuery(name = "CommentsModel.findAll", query = "FROM CommentsModel o")
})

public class CommentsModel
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @NotNull
    private String data;


    public int getId()
    {
        return this.id;
    }

    public void setId(int id_)
    {
        this.id = id_;
    }

    public Date getCreated()
    {
        return this.created;
    }

    public void setCreated(Date created_)
    {
        this.created = created_;
    }


    public String getData()
    {
        return this.data;
    }

    public void setData(String data_)
    {
        this.data = data_;
    }



    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }



}
