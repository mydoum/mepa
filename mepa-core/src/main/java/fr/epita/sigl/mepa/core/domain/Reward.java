package fr.epita.sigl.mepa.core.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name="REWARD")
@NamedQueries({
        @NamedQuery(name = "Reward.findById", query = "FROM Reward p WHERE p.id=:id"),
        @NamedQuery(name = "Reward.findAll", query = "FROM Reward p")
})
public class Reward implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reward_id", nullable = false)
    private Long reward_id;

/*    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;*/
    @NotNull
    private String name;

    private String description;

    private float costStart;


    /*
* ID
* Name
* UserID
* Description
* Date début
* Date Fin
* Photos
* Vidéos
* */
    public Reward() {
    }

    public Reward(String rewardName) {
    }

    //return Date with a specific format
    public String dateFormat(String format, Date date)
    {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public Long getId() {
        return reward_id;
    }

    public void setId(Long id) {
        this.reward_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCostStart() {
        return costStart;
    }

    public void setCostStart(float costStart) {
        this.costStart = costStart;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }



}