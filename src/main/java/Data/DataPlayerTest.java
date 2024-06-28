package Data;

import Model.Player;
import Model.Team;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DataPlayerTest {

    private static List<Team> equipos;

    @BeforeClass
    public static void setUp() throws IOException {
        equipos = DataPlayer.leerEquipos();
    }

    @Test
    public void testLeerEquipos() {
        assertNotNull("La lista de equipos no debería ser nula", equipos);
        assertEquals("Se esperan 4 equipos", 4, equipos.size());
    }

    @Test
    public void testLeerJugadores() throws IOException {
        List<Player> jugadores = DataPlayer.leerJugadores("aus.txt");
        assertNotNull("La lista de jugadores de Australia no debería ser nula", jugadores);
        assertEquals("Se esperan 3 jugadores para Australia", 3, jugadores.size());
    }

    // Puedes añadir más pruebas según las funcionalidades que quieras validar en DataPlayer
}
