public class Personaje {
    private String nombre;
    private int hp;
    private int fuerza;

    public Personaje(String nombre, int hp, int fuerza, int defensa) {
        this.nombre = nombre;
        this.hp = hp;
        this.fuerza = fuerza;
    }

    // Usamos getters y llamamos al controlador
    public String getNombre() { return nombre; }
    public int getHp() { return hp; }
    public int getFuerza() { return fuerza; }

    @Override
    public String toString() {
        return nombre + " (HP: " + hp + " | STR: " + fuerza + ")";
    }
}


