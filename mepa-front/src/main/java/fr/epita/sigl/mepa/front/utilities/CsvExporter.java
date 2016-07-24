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
    private static final String FILE_HEADER = "Nom;Prénom;Montant;Date;Email";

    public static String writeCsvFile(ArrayList<Investor> investors) {

        String fileWriter = "";

        //Write the CSV file header
        fileWriter += FILE_HEADER.toString();

        //Add a new line separator after the header
        fileWriter += NEW_LINE_SEPARATOR;

        for (Investor investor : investors) {
            fileWriter += investor.getLastname();
            fileWriter += COMMA_DELIMITER;
            fileWriter += investor.getFirstname();
            fileWriter += COMMA_DELIMITER;
            fileWriter += String.valueOf(investor.getMoneyAmount());
            fileWriter += COMMA_DELIMITER;
            SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
            fileWriter += formater.format(investor.getDateOfInvestment());
            fileWriter += COMMA_DELIMITER;
            fileWriter += investor.getEmail();
            fileWriter += NEW_LINE_SEPARATOR;
        }

        return fileWriter;
    }
}
