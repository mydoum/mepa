package fr.epita.sigl.mepa.front.utilities;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by Gregoire on 19/07/2016.
 */
public class CsvExporter {

    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ";";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "id;firstName;lastName;gender;age";

    public static String writeCsvFile() {

        //Create new students objects
        Student student1 = new Student(1, "Ahmed", "Mohamed", "M", 25);
        Student student2 = new Student(2, "Sara", "Said", "F", 23);
        Student student3 = new Student(3, "Ali", "Hassan", "M", 24);
        Student student4 = new Student(4, "Sama", "Karim", "F", 20);
        Student student5 = new Student(5, "Khaled", "Mohamed", "M", 22);
        Student student6 = new Student(6, "Ghada", "Sarhan", "F", 21);

        //Create a new list of student objects
        ArrayList<Student> students = new ArrayList();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);

        String fileWriter = null;

        //Write the CSV file header
        fileWriter += FILE_HEADER.toString();

        //Add a new line separator after the header
        fileWriter += NEW_LINE_SEPARATOR;

        //Write a new student object list to the CSV file
        for (Student student : students) {
                for (int i = 0; i < 1000; i++) {
                    fileWriter += String.valueOf(student.getId());
                    fileWriter += COMMA_DELIMITER;
                    fileWriter += student.getFirstName();
                    fileWriter += COMMA_DELIMITER;
                    fileWriter += student.getLastName();
                    fileWriter += COMMA_DELIMITER;
                    fileWriter += student.getGender();
                    fileWriter += COMMA_DELIMITER;
                    fileWriter += String.valueOf(student.getAge());
                    fileWriter += NEW_LINE_SEPARATOR;
                }
        }
        
        return fileWriter;
    }

    //controller
    @RequestMapping(value = {"/project/download"})
    public String investDownload(HttpServletResponse response) {
        String fileWriter = CsvExporter.writeCsvFile();
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"toto.csv\"");
        try
        {
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(fileWriter.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/project/download";
    }
}
