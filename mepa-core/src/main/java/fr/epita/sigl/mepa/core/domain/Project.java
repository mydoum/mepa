package fr.epita.sigl.mepa.core.domain;

import fr.epita.sigl.mepa.core.service.RewardService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name="PROJECT")
@NamedQueries({
        @NamedQuery(name = "Project.findById", query = "FROM Project p WHERE p.id=:id"),
        @NamedQuery(name = "Project.findAll", query = "FROM Project p ORDER BY p.endDate ASC"),
        @NamedQuery(name = "Project.findAllUnfinished", query = "FROM Project p WHERE p.endDate > CURRENT_DATE ORDER BY p.endDate ASC"),
        @NamedQuery(name = "Project.findAllFinished", query = "FROM Project p WHERE p.endDate <= CURRENT_DATE ORDER BY p.endDate ASC")
})
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reward_id", nullable = false)
    private Long id;

    @NotNull
    private Long user_id; //Id of the user creating the project

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "endDate", nullable = false)
    private Date endDate;

    @NotNull
    private String name;

    private String description;

    private ArrayList<Image> images;

    private ArrayList<String> imagesLinks;

    @OneToMany(fetch = FetchType.EAGER) //, mappedBy="project"
    @JoinColumn(name="project_id")
    private Set<Reward> rewards;

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
    public Project() {
        this.user_id = (long) 1;
        this.startDate = new Date();
        this.endDate = new Date();
        this.name = "Nom du projet";
    }

    public Project(Long user_id, String projectName, Date endDate) {
        this.user_id = user_id;
        this.startDate = new Date();
        this.endDate = endDate;
        this.name = projectName;
    }

    //return Date with a specific format
    public String dateFormat(String format, Date date)
    {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Date getStartDate() { return startDate; }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() { return endDate; }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public ArrayList<String> getImagesLinks() {
        return imagesLinks;
    }

    public void setImagesLinks(ArrayList<String> imagesLinks) {
        this.imagesLinks = imagesLinks;
    }

    public Set<Reward> getRewards() {
        return rewards;
    }

    /*@Autowired
    RewardService rewardService;*/
    public void addReward(Reward r) {
        /*rewards.add(r);
        rewardService.createReward(r);*/
    }
    public void setRewards(Set<Reward> rewards) {


        this.rewards = rewards;
    }

    public Boolean isFinished() {
        return this.endDate.after(new Date());
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }



}
