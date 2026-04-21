public class Personaje {
    private String nombre;
    private int hp;
    private int fuerza;
    private int defensa;

    public Personaje(String nombre, int hp, int fuerza, int defensa) {
        this.nombre = nombre;
        this.hp = hp;
        this.fuerza = fuerza;
        this.defensa = defensa;
    }

    // --- MÉTODOS DE LÓGICA (Facilitan la vida al Controlador) ---

    public boolean estaVivo() {
        return this.hp > 0;
    }

    public void recibirDaño(int cantidad) {
        // Variación aleatoria del 10% para que no sea siempre el mismo número
        int variacion = (int) (Math.random() * 10) - 5;
        int dañoFinal = (cantidad + variacion) - (this.defensa / 5);
        // ... resto del código
    }

    public String getNombre() { return nombre; }

    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }

    public int getFuerza() { return fuerza; }
    public void setFuerza(int fuerza) { this.fuerza = fuerza; }

    public int getDefensa() { return defensa; }
    public void setDefensa(int defensa) { this.defensa = defensa; }

    @Override
    public String toString() {
        return nombre + " (HP: " + hp + " | STR: " + fuerza + " | DEF: " + defensa + ")";
    }
}