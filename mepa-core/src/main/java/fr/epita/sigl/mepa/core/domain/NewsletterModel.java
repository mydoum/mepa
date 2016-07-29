package fr.epita.sigl.mepa.core.domain;

/**
 * Created by prosp_000 on 25/07/2016.
 */

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@NamedQueries({
        @NamedQuery(name = "Newsletter.findById", query = "FROM NewsletterModel o WHERE o.id=:id"),
        @NamedQuery(name = "Newsletter.findAllSorted", query = "FROM NewsletterModel o ORDER BY o.like_ "),
        @NamedQuery(name = "Newsletter.findAll", query = "FROM NewsletterModel o")})



public class NewsletterModel
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long projectid;



    private ArrayList<String> emails;
    private int like_;




    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public int getLike_() {
        return like_;
    }
    public void setLike_(int like_) {
        this.like_ = like_;
    }



    public long getProjectid() {
        return projectid;
    }
    public void setProjectid(Long projectid_) {
        this.projectid = projectid_;
    }


    public void addEmail(String email_) {

        this.emails.add(email_);
        System.out.println("\n LA taillle de la liste est  : " + emails.size() +  "\n");

    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}