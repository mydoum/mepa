package fr.epita.sigl.mepa.core.utils;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import sun.plugin2.message.Message;


import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Properties;

/*@Entity
@NamedQueries({
        @NamedQuery(name = "Investment.findById", query = "FROM Investment o WHERE o.id=:id"),
        @NamedQuery(name = "Investment.findAll", query = "FROM Investment o"),
        @NamedQuery(name = "Investment.findAllByProject", query = "FROM Investment o WHERE o.project_id=:project_id"),
        })*/
public class Mail
{
    //Class to send an email.

    private final static String MAILER_VERSION = "Java";

    public static boolean sendSMTPMail(String server, boolean debug)
    {
        boolean result = false;
        try
        {
            Properties prop = System.getProperties();
            prop.put("smtp.gmail.com", server);
            javax.mail.Session session = Session.getDefaultInstance(prop, null);
            javax.mail.Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mepa.epita@gmail.com"));
            InternetAddress[] internetAddresses = new InternetAddress[1];
            internetAddresses[0] = new InternetAddress("hugo.capes@hotmail.fr");
            message.setRecipients(javax.mail.Message.RecipientType.TO, internetAddresses);
            message.setSubject("Thanks for your donation");
            message.setText("Hello! Thanks you for your donation to this project!\nYours sincerely.\nThe MEPA team");
            message.setHeader("X-Mailer", MAILER_VERSION);
            message.setSentDate(new Date());
            session.setDebug(debug);
            Transport.send(message);
            result = true;
        }
        catch (AddressException e)
        {
            e.printStackTrace();
        }
        catch (javax.mail.MessagingException e)
        {
            e.printStackTrace();
        }
        return result;
    }
}

    /*@Id
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
    private Integer amount; //Amount invested on the project

    @NotNull
    private Long projectId;

    public Long getUserId (){ return this.userId; }
    public void setUserId (Long user_id){ this.userId = user_id;}

    public Integer getAmount() { return this.amount; }
    public void setAmount (Integer amount) { this.amount = amount; }

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
    }*/

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

   /* @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}*/
