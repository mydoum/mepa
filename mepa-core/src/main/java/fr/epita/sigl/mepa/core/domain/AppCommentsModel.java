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
    @NamedQuery(name = "AppCommentsModel.findById", query = "FROM AppCommentsModel o WHERE o.id=:id"),
        @NamedQuery(name = "AppCommentsModel.findAll", query = "FROM AppCommentsModel o")
})
public class AppCommentsModel
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @NotNull
    private String data_;

    @NotNull
    private Long projectId_;

    @NotNull
    private String user_;

    @NotNull
    private int arriving_;


    public Long getId_()
    {
        return this.id;
    }

    public void setId_(Long id_)
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
        return this.data_;
    }

    public void setData_(String data_)
    {
        this.data_ = data_;
    }

    public Long getProjectId_() {
        return projectId_;
    }

    public void setProjectId_(Long projectId) {
        this.projectId_ = projectId;
    }


    public int getArriving_() {
        return arriving_;
    }

    public void setArriving_(int arriving_) {
        this.arriving_ = arriving_;
    }

    public String getUser_() {
        return user_;
    }

    public void setUser_(String user) {
        this.user_ = user;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }



}
