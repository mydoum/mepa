package fr.epita.sigl.mepa.front.controller.home;

import fr.epita.sigl.mepa.front.utilities.CsvExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class HomeController extends HttpServlet{

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = {"/", "/home"})
    public String home() {
        return "/home/home";
    }

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
