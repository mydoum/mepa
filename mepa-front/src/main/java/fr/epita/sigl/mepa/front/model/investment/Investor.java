package fr.epita.sigl.mepa.front.model.investment;


import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Simon MACE on 20/07/2016.
 */
public class Investor implements Comparable<Investor>{
    private String firstname;
    private String lastname;
    private String email;
    private Float moneyAmount;
    private Date dateOfInvestment;
    private boolean anonymous;

    public Investor(String email, String firstname, String lastname, Float moneyAmount, Date dateOfInvestment,
                    boolean anonymous) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.moneyAmount = moneyAmount;
        this.dateOfInvestment = dateOfInvestment;
        this.anonymous = anonymous;
    }

    public String getEmail() {return email; }

    public Float getMoneyAmount() {
        return moneyAmount;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getDateOfInvestment() {
        return dateOfInvestment;
    }

    public void setEmail(String email) { this.email = email; }

    public void setMoneyAmount(Float moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setDateOfInvestment(Date dateOfInvestment) {
        this.dateOfInvestment = dateOfInvestment;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public int compareTo(Investor investor)
    {
        // Comparaison sur l'auteur
        int compdate= this.getDateOfInvestment().compareTo(investor.getDateOfInvestment());
        if(compdate != 0) {
            return (- compdate);
        }

        // Comparaison sur l'année
        int compLastName = this.getLastname().compareTo(investor.getLastname());
        if(compLastName != 0) { return compLastName; }

        // Comparaison sur le titre
        int compFirstName = this.getFirstname().compareTo(investor.getFirstname());
        if(compFirstName != 0) { return compFirstName; }

        // Les deux livres sont à la même position
        return 0;
    }

}
