package fr.epita.sigl.mepa.front.utilities;

import fr.epita.sigl.mepa.front.model.investment.Investor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Gregoire on 19/07/2016.
 */
public class CsvExporter {

    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ";";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "Nom;Prénom;Montant;Date";

    public static String writeCsvFile(ArrayList<Investor> investors) {

        String fileWriter = "";

        //Write the CSV file header
        fileWriter += FILE_HEADER.toString();

        //Add a new line separator after the header
        fileWriter += NEW_LINE_SEPARATOR;

        for (Investor investor : investors) {
            if (!investor.isAnonymous()) {
                if (investor.getFirstname().compareTo("") != 0 && investor.getLastname().compareTo("") != 0) {
                    fileWriter += investor.getLastname();
                    fileWriter += COMMA_DELIMITER;
                    fileWriter += investor.getFirstname();
                }
                else {
                    fileWriter += investor.getEmail();
                    fileWriter += COMMA_DELIMITER;
                    fileWriter += "";
                }
            } else {
                fileWriter += "Anonyme";
                fileWriter += COMMA_DELIMITER;
                fileWriter += "Anonyme";
            }

            fileWriter += COMMA_DELIMITER;
            fileWriter += String.valueOf(investor.getMoneyAmount());
            fileWriter += COMMA_DELIMITER;
            fileWriter += investor.getStringDate();
            fileWriter += NEW_LINE_SEPARATOR;
        }

        return fileWriter;
    }
}
