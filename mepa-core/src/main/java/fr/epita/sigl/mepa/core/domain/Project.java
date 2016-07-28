package fr.epita.sigl.mepa.core.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
    public enum Currency {
        DOLLAR("$"),
        EURO("€"),
        POUND("£");

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        private String symbol;

        private Currency(String symbol){
            this.symbol = symbol;
        }

        public String getValue() {
            return name();
        }

        public void setValue(String value) {}
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reward_id", nullable = false)
    private Long id;


    private Long user_id; //Id of the user creating the project

    @Temporal(TemporalType.DATE)
    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "endDate", nullable = false)
    private Date endDate;

    @NotNull
    private String name;

    private String description;

    private ArrayList<Image> images;

    private ArrayList<String> imagesLinks;

    private Long goalAmount;

    private Currency currency;

    private Long visitNumber;

    @OneToMany(fetch = FetchType.EAGER) //, mappedBy="project"
    @JoinColumn(name="project_id")
    private Set<Reward> rewards;

    private Boolean twitterAllowed;

    private Boolean facebookAllowed;

    //private String currency;

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
        this.rewards= new HashSet<>();
        this.goalAmount = 0L;
        this.currency = Currency.DOLLAR;
    }

    public Project(int nb) {
    }

    public Project(Long user_id, String projectName, Date endDate) {
        this.user_id = user_id;
        this.startDate = new Date();
        this.endDate = endDate;
        this.name = projectName;
        this.rewards= new HashSet<>();
        this.goalAmount = 0L;
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

    public Long getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(Long goalAmount) {
        this.goalAmount = Math.abs(goalAmount);
    }

    public String getCurrencyString() {
        switch (this.currency){
            case DOLLAR:
                return "$";
            case EURO:
                return "€";
            case POUND:
                return "£";
            default:
                return "*";
        }
    }

    public Long getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(Long visitNumber) {
        this.visitNumber = visitNumber;
    }

    public void increaseVisits() {
        ++this.visitNumber;
    }

    public Boolean getTwitterAllowed() {
        return twitterAllowed;
    }

    public void setTwitterAllowed(Boolean twitterAllowed) {
        this.twitterAllowed = twitterAllowed;
    }

    public Boolean getFacebookAllowed() {
        return facebookAllowed;
    }

    public void setFacebookAllowed(Boolean facebookAllowed) {
        this.facebookAllowed = facebookAllowed;
    }

    public Set<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(Set<Reward> rewards) {
        this.rewards = rewards;
    }

    public void addRewards(Reward reward){
        this.rewards.add(reward);
    }

    public Boolean isFinished() {
        return this.endDate.after(new Date());
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}