package fr.epita.sigl.mepa.front.utilities;

import fr.epita.sigl.mepa.core.domain.AppUser;
import fr.epita.sigl.mepa.front.model.investment.Investor;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by Simon on 26/07/2016.
 */
public class Tools {
    static private Properties mailServerProperties;
    static private Session getMailSession;
    static private MimeMessage generateMailMessage;

    private final static String MAILER_VERSION = "Java";

    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ";";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "Prénom;Nom;Email;Montant total;Liste des contributions";
    private static final String FILE_HEADER_USER = "Pr\u00E9nom;Nom;Email";



    public Tools() {
    }

    public int percentage (int a, int b) {
        if (a == 0) {
            return 100;
        }
        System.out.println("goal: " + a + ", actual: " + b);
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

    /**
     * This function export an arraylist of investors
     * @param investors
     * @return
     */
    public static String writeCsvFile(ArrayList<Investor> investors) {

        //regrouper par investisseur
        //pour chaque investisseur, calculer la somme totale d'investissement
        //mettre dans l'excel

        ArrayList<String> keys = new ArrayList<>();
        HashMap<String, ArrayList<Float>> investorsMap = new HashMap<>();

        for (Investor investor : investors) {
            ArrayList<Float> floats;
            if (!investorsMap.containsKey(investor.getFirstname() + COMMA_DELIMITER + investor.getLastname()
                    + COMMA_DELIMITER + investor.getEmail())) {
                floats = new ArrayList<>();
                floats.add(investor.getMoneyAmount());
                investorsMap.put(investor.getFirstname() + COMMA_DELIMITER + investor.getLastname() + COMMA_DELIMITER
                        + investor.getEmail(), floats);
                keys.add(investor.getFirstname() + ";" + investor.getLastname() + ";" + investor.getEmail());
            } else {
                floats = investorsMap.get(investor.getFirstname() + COMMA_DELIMITER + investor.getLastname()
                        + COMMA_DELIMITER + investor.getEmail());
                floats.add(investor.getMoneyAmount());
                investorsMap.put(investor.getFirstname() + COMMA_DELIMITER + investor.getLastname() + COMMA_DELIMITER
                        + investor.getEmail(), floats);
            }
        }

        String fileWriter = "";
        fileWriter += FILE_HEADER;
        fileWriter += NEW_LINE_SEPARATOR;

        for (String investor : keys) {
            ArrayList<Float> floats = investorsMap.get(investor);
            Float sum = calculSum(floats);
            fileWriter += investor;
            fileWriter += COMMA_DELIMITER;
            fileWriter += sum;
            fileWriter += NEW_LINE_SEPARATOR;
        }
        return fileWriter;
    }

    public static String writeUserCsvFile(ArrayList<AppUser> users) {

        ArrayList<String> keys = new ArrayList<>();
        HashMap<String, ArrayList<Float>> usersMap = new HashMap<>();

        for (AppUser user : users) {
            ArrayList<Float> floats;
            if (!usersMap.containsKey(user.getFirstName() + COMMA_DELIMITER + user.getLastName()
                    + COMMA_DELIMITER + user.getLogin())) {
                floats = new ArrayList<>();
                usersMap.put(user.getFirstName() + COMMA_DELIMITER + user.getLastName() + COMMA_DELIMITER
                        + user.getLogin(), floats);
                keys.add(user.getFirstName() + ";" + user.getLastName() + ";" + user.getLogin());
            } else {
                floats = usersMap.get(user.getFirstName() + COMMA_DELIMITER + user.getLastName()
                        + COMMA_DELIMITER + user.getLogin());
                usersMap.put(user.getFirstName() + COMMA_DELIMITER + user.getLastName() + COMMA_DELIMITER
                        + user.getLogin(), floats);
            }
        }

        String fileWriter = "";
        fileWriter += FILE_HEADER_USER;
        fileWriter += NEW_LINE_SEPARATOR;

        for (String user : keys) {
            ArrayList<Float> floats = usersMap.get(user);
            Float sum = calculSum(floats);
            fileWriter += user;
            fileWriter += COMMA_DELIMITER;
            fileWriter += NEW_LINE_SEPARATOR;
        }
        return fileWriter;
    }


    public static Float calculSum(ArrayList<Float> floats) {
        Float sum = 0.0f;

        for (Float f : floats)
            sum += f;

        return sum;
    }
}
