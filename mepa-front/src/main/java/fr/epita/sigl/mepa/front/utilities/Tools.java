package fr.epita.sigl.mepa.front.utilities;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Simon on 26/07/2016.
 */
public class Tools {
    static private Properties mailServerProperties;
    static private Session getMailSession;
    static private MimeMessage generateMailMessage;

    private final static String MAILER_VERSION = "Java";

    public Tools() {
    }

    public int percentage (int a, int b) {
        if (a == 0) {
            return 100;
        }
        return ((b * 100) / a);
    }

    public static boolean sendMail (String mail, String Subject, String message) throws AddressException, MessagingException {
        //User tmpUser = userService.getUserById(userId);
        boolean result = false;

        try {
            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");

            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
            generateMailMessage.setSubject(Subject);
            generateMailMessage.setContent(message, "text/html");
            generateMailMessage.setHeader("X-Mailer", MAILER_VERSION);


            Transport transport = getMailSession.getTransport("smtp");

            transport.connect("smtp.gmail.com", "mepa.epita@gmail.com", "sigl2017");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
        } catch (AddressException e) {
            // e.printStackTrace();
            return false;
        } catch (javax.mail.MessagingException e) {
            // e.printStackTrace();
            return false;
        }
        return result;
    }
}
