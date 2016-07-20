package fr.epita.sigl.mepa.core.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@NamedQueries({
  //      @NamedQuery(name = "Model.findById", query = "FROM Model o WHERE o.id=:id"),
  //      @NamedQuery(name = "Model.findAll", query = "FROM Model o")
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
    private Long user_id; //Id of the user who is investing

    @NotNull
    private Integer amount; //Amount invested on the project

    @NotNull
    private Long project;

    public Long getUser_id (){ return this.user_id; }
    public void setUser_id (Long user_id){ this.user_id = user_id;}

    public Integer getAmount() { return this.amount; }
    public void setAmount (Integer amount) { this.amount = amount; }

    public String getProject (){ return this.project; }
    public void setProject ( String project ) { this.project = project; }

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

    /**
     * @return the data
     */
   /* public String getData() {
        return this.data;
    }*/

    /**
     * @param data the data to set
     */
    /*public void setData(String data) {
        this.data = data;
    }*/

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
