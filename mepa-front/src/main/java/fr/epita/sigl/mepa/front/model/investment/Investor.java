package fr.epita.sigl.mepa.front.model.investment;


import java.util.Date;

/**
 * Created by Valentin ZHENG on 20/07/2016.
 */
public class Investor implements Comparable<Investor>{
    private String firstname;
    private String lastname;
    private Float moneyAmount;
    private Date dateOfInvestment;

    public Investor(String firstname, String lastname, Float moneyAmount, Date dateOfInvestment) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.moneyAmount = moneyAmount;
        this.dateOfInvestment = dateOfInvestment;
    }

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
