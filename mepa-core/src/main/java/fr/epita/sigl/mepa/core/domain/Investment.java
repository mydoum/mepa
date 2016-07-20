package fr.epita.sigl.mepa.core.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "Investment.findById", query = "FROM Investment o WHERE o.id=:id"),
        @NamedQuery(name = "Investment.findAll", query = "FROM Investment o"),
        @NamedQuery(name = "Investment.findAllByProject", query = "FROM Investment o WHERE o.projectId=:projectId"),
        })
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @NotNull
    private Date date; //investment date

    @NotNull
    private Long userId; //Id of the user who is investing

    @NotNull
    private Float amount; //Amount invested on the project

    @NotNull
    private Long projectId;

    public Long getUserId (){ return this.userId; }
    public void setUserId (Long user_id){ this.userId = user_id;}

    public Float getAmount() { return this.amount; }
    public void setAmount (Float amount) { this.amount = amount; }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getProjectId (){ return this.projectId; }
    public void setProjectId ( Long project ) { this.projectId = project; }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
