package fr.epita.sigl.mepa.front.utilities;

import fr.epita.sigl.mepa.front.model.investment.Investor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Gregoire on 19/07/2016.
 */
public class CsvExporter {

    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ";";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "Prénom;Nom;Email;Montant total;Liste des contributions";

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

    static private Float calculSum(ArrayList<Float> floats) {
        Float sum = 0.0f;

        for (Float f : floats)
            sum += f;

        return sum;
    }
}