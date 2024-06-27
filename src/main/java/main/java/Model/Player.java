package Model;

public class Player {
    private String nombre;
    private int numero;
    private Position posicion;

    public Player(String nombre, int numero, Position posicion) {
        this.nombre = nombre;
        this.numero = numero;
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Position getPosicion() {
        return posicion;
    }

    public void setPosicion(Position posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return String.format("%d:%s:%s", numero, nombre, posicion);
    }
}
