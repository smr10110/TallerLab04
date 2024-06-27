package Data;

import Model.Player;
import Model.Position;
import Model.Team;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class DataPlayer {

    // Cambio la ruta para apuntar al directorio 'resources' dentro de 'src/main'
    private static final String RESOURCE_PATH = "src/main/resources/";

    public static List<Team> leerEquipos() throws IOException {
        List<Team> equipos = new ArrayList<>();
        Path path = Paths.get(RESOURCE_PATH + "teams.txt");  // Ruta corregida para teams.txt
        List<String> lineas = Files.readAllLines(path);
        for (String linea : lineas) {
            String[] partes = linea.split(";");
            int id = Integer.parseInt(partes[0]);
            String nombre = partes[1];
            int ranking = Integer.parseInt(partes[2]);
            String rutaBandera = RESOURCE_PATH + partes[3];  // Ruta corregida para las banderas
            Team equipo = new Team(id, nombre, ranking, rutaBandera);
            equipo.getJugadores().addAll(leerJugadores(partes[1].toLowerCase() + ".txt")); // Carga de jugadores
            equipos.add(equipo);
        }
        return equipos;
    }

    public static List<Player> leerJugadores(String archivoEquipo) throws IOException {
        List<Player> jugadores = new ArrayList<>();
        Path path = Paths.get(RESOURCE_PATH + archivoEquipo); // Ruta corregida para archivos de jugadores
        List<String> lineas = Files.readAllLines(path);
        for (String linea : lineas) {
            String[] partes = linea.split(";");
            int numero = Integer.parseInt(partes[0]);
            String nombre = partes[1];
            Position posicion = Position.valueOf(partes[2]);
            jugadores.add(new Player(nombre, numero, posicion));
        }
        return jugadores;
    }
}
