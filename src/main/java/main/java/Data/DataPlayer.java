package Data;

import Model.Player;
import Model.Position;
import Model.Team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataPlayer {

    private static final Map<String, String> EQUIPO_ARCHIVO_MAP = new HashMap<>();

    static {
        EQUIPO_ARCHIVO_MAP.put("Australia", "aus.txt");
        EQUIPO_ARCHIVO_MAP.put("Cameroon", "cmr.txt");
        EQUIPO_ARCHIVO_MAP.put("Chile", "chi.txt");
        EQUIPO_ARCHIVO_MAP.put("Germany", "ger.txt");
    }

    public static List<Team> leerEquipos() throws IOException {
        List<Team> equipos = new ArrayList<>();

        // Mensaje de depuración
        System.out.println("Intentando cargar teams.txt desde el classpath...");

        try (InputStream inputStream = DataPlayer.class.getClassLoader().getResourceAsStream("teams.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            if (inputStream == null) {
                throw new IOException("Archivo teams.txt no encontrado en el classpath");
            }
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println("Leyendo línea: " + linea);
                String[] partes = linea.split(";");
                int id = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                int ranking = Integer.parseInt(partes[2]);
                String rutaBandera = partes[3];
                Team equipo = new Team(id, nombre, ranking, rutaBandera);

                // Obtener el nombre del archivo desde el mapa
                String archivoJugadores = EQUIPO_ARCHIVO_MAP.get(nombre);
                if (archivoJugadores == null) {
                    throw new IOException("No se encontró el archivo de jugadores para el equipo: " + nombre);
                }

                System.out.println("Cargando jugadores para el equipo: " + archivoJugadores);

                equipo.getJugadores().addAll(leerJugadores(archivoJugadores));
                equipos.add(equipo);
            }
        }

        return equipos;
    }

    public static List<Player> leerJugadores(String archivoEquipo) throws IOException {
        List<Player> jugadores = new ArrayList<>();

        // Mensaje de depuración
        System.out.println("Intentando cargar " + archivoEquipo + " desde el classpath...");

        try (InputStream inputStream = DataPlayer.class.getClassLoader().getResourceAsStream(archivoEquipo);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            if (inputStream == null) {
                throw new IOException("Archivo " + archivoEquipo + " no encontrado en el classpath");
            }
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println("Leyendo línea: " + linea);
                String[] partes = linea.split(";");
                int numero = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                Position posicion = Position.valueOf(partes[2].toUpperCase());
                jugadores.add(new Player(nombre, numero, posicion));
            }
        }

        return jugadores;
    }
}
