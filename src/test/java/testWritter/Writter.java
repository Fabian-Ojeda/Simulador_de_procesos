package testWritter;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Writter {

    public static void main(String[] args) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        System.out.println("buenas");
        try
        {
            fichero = new FileWriter("./prueba.txt");
            pw = new PrintWriter(fichero);

            for (int i = 0; i < 10; i++)
                pw.println("Linea " + i);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
