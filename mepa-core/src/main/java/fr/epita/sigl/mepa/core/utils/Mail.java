package fr.epita.sigl.mepa.core.utils;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;


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
            prop.put("mail.smtp.starttls.enable","true");
            prop.put("mail.smtp.host", server);
            prop.put("mail.smtp.user", "mepa.epita@gmail.com");
            prop.put("mail.smtp.password", "sigl2017");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
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

            Transport transport = session.getTransport("smtps");
            transport.connect(server, "mepa.epita@gmail.com", "sigl2017");
            transport.send(message);
            transport.close();
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
