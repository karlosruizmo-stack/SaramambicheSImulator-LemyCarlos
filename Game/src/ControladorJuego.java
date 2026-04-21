public class ControladorJuego {

    private Personaje jugador;
    private Personaje enemigoActual;
    private Vista vista;

    private int recordPuntuacion;
    private int puntuacionActual;
    private int contadorAtaquesBasicos = 0;
    private String rutaRecord = "record.txt";

    public ControladorJuego(Vista vista) {
        this.vista = vista;
        // Baki inicial con sus stats de base
        this.jugador = new Personaje("Baki Hanma", 150, 35, 20);
        this.recordPuntuacion = cargarRecord();
        this.puntuacionActual = 0;
    }
}
