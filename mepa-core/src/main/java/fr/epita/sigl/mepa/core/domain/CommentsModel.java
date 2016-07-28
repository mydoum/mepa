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

    @NotNull
    private int projectId;

    @NotNull
    private String user;

    @NotNull
    private int arriving;


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

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }


    public int getArriving() {
        return arriving;
    }

    public void setArriving(int arriving) {
        this.arriving = arriving;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }



}
