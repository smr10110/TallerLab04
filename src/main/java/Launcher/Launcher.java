package Launcher;

import Data.DataPlayer;
import Guis.GuiTeams;

import javax.swing.SwingUtilities;
import java.io.InputStream;

public class Launcher {
    public static void main(String[] args) {
        // Verificación manual de los archivos
        verificarArchivo("teams.txt");
        verificarArchivo("aus.txt");
        verificarArchivo("chi.txt");
        verificarArchivo("cmr.txt");
        verificarArchivo("ger.txt");

        // Lanza la aplicación
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuiTeams();
            }
        });
    }

    private static void verificarArchivo(String archivo) {
        InputStream inputStream = DataPlayer.class.getClassLoader().getResourceAsStream(archivo);
        if (inputStream == null) {
            System.out.println("Archivo " + archivo + " no encontrado en el classpath");
        } else {
            System.out.println("Archivo " + archivo + " encontrado en el classpath");
        }
    }
}
