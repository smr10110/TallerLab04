package Model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private int id;
    private String nombre;
    private int ranking;
    private String rutaBandera;
    private List<Player> jugadores;

    public Team(int id, String nombre, int ranking, String rutaBandera) {
        this.id = id;
        this.nombre = nombre;
        this.ranking = ranking;
        this.rutaBandera = rutaBandera;
        this.jugadores = new ArrayList<>();
    }

    public void agregarJugador(Player jugador) throws Exception {
        for (Player j : jugadores) {
            if (j.getNumero() == jugador.getNumero()) {
                throw new Exception("El número del jugador debe ser único dentro del equipo.");
            }
        }
        jugadores.add(jugador);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getRanking() {
        return ranking;
    }

    public String getRutaBandera() {
        return rutaBandera;
    }

    public List<Player> getJugadores() {
        return jugadores;
    }

    @Override
    public String toString() {
        return String.format("%d:%s:%d", id, nombre, ranking);
    }
}