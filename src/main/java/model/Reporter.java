package model;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Reporter {

    ArrayList<String> arrayReports;
    public Reporter() {
        arrayReports = new ArrayList<>();
    }

    public void addSentence(String sentence){
        arrayReports.add(sentence);
    }

    public void generateReport(){
        FileWriter fichero = null;
        PrintWriter pw = null;
        System.out.println("buenas");
        try
        {
            fichero = new FileWriter("./reporte.txt");
            pw = new PrintWriter(fichero);

            for (String iterator:
                 arrayReports) {
                pw.println(iterator);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
