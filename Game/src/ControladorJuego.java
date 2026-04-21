import java.time.LocalDateTime;
import java.util.ArrayList;

public class ControladorJuego {

    private Personaje jugador;
    private Personaje enemigoActual;
    private Vista vista;
    private ControladorLogs controlLogs; // El controlador de archivos

    private ArrayList<Partida> historialPartidas; // El ArrayList donde cargamos todo
    private int recordPuntuacion;
    private int puntuacionActual;
    private int contadorAtaquesBasicos = 0;

    public ControladorJuego(Vista vista, ControladorLogs controlLogs) {
        this.vista = vista;
        this.controlLogs = controlLogs;
        this.historialPartidas = new ArrayList<>();

        // 1. Cargamos los datos del archivo al ArrayList al iniciar
        this.controlLogs.cargarDesdeArchivo(this.historialPartidas);

        // 2. Buscamos el récord dentro de ese ArrayList
        this.recordPuntuacion = calcularRecordDesdeLogs();

        this.jugador = new Personaje("Baki Hanma", 150, 35, 20);
        this.puntuacionActual = 0;
    }

    private int calcularRecordDesdeLogs() {
        int max = 0;
        for (Partida p : historialPartidas) {
            if (p.getPuntuación() > max) {
                max = p.getPuntuación();
            }
        }
        return max;
    }

    public void iniciarJuego() {
        int op = -1;
        while (op != 0) {
            op = vista.mostrarMenuPrincipal();
            if (op == 1) {
                flujoCombate();
            } else if (op == 2) {
                // Aquí usamos el ArrayList cargado para mostrar el historial
                vista.mostrarLogs(historialPartidas);
                vista.mostrarMensaje("Récord absoluto: " + recordPuntuacion);
            }
        }
        // Al salir del programa, nos aseguramos de que todo esté guardado
        controlLogs.guardarEnArchivo(historialPartidas);
    }

    private void flujoCombate() {
        generarEnemigo();
        vista.mostrarInicioCombate(enemigoActual.getNombre());
        contadorAtaquesBasicos = 0;

        while (jugador.estaVivo() && enemigoActual.estaVivo()) {
            ejecutarTurnoJugador();
            if (enemigoActual.estaVivo()) {
                ejecutarTurnoEnemigo();
            }
            vista.mostrarEstado(jugador, enemigoActual);
        }

        if (jugador.estaVivo()) {
            registrarVictoria();
        } else {
            vista.derrota();
            gestionarFinDePartida(); // Al morir, guardamos la partida en el log
        }
    }

    private void gestionarFinDePartida() {
        // Creamos un nuevo objeto Partida con los datos de la sesión actual
        int nuevoId = historialPartidas.size() + 1;
        Partida partidaFinalizada = new Partida(nuevoId, puntuacionActual, LocalDateTime.now());

        // Añadimos al ArrayList
        historialPartidas.add(partidaFinalizada);

        // Actualizamos el archivo .txt inmediatamente
        controlLogs.guardarEnArchivo(historialPartidas);

        // Comprobamos si es nuevo récord
        if (puntuacionActual > recordPuntuacion) {
            recordPuntuacion = puntuacionActual;
            vista.mostrarMensaje("¡NUEVO RÉCORD REGISTRADO!");
        }

        // Reset para la próxima vez que el usuario quiera jugar en la misma sesión
        puntuacionActual = 0;
        jugador.setHp(150); // O resetear stats si lo prefieres
    }

    private void ejecutarTurnoJugador() {
        int danoBase = jugador.getFuerza();
        if (contadorAtaquesBasicos >= 2) {
            int danoEspecial = danoBase * 2;
            enemigoActual.recibirDaño(danoEspecial);
            vista.infoAtaque(jugador.getNombre(), "ESPECIAL", danoEspecial);
            contadorAtaquesBasicos = 0;
        } else {
            enemigoActual.recibirDaño(danoBase);
            vista.infoAtaque(jugador.getNombre(), "NORMAL", danoBase);
            contadorAtaquesBasicos++;
            vista.mostrarCargaEspecial(contadorAtaquesBasicos);
        }
    }

    private void ejecutarTurnoEnemigo() {
        jugador.recibirDaño(enemigoActual.getFuerza());
        vista.infoAtaque(enemigoActual.getNombre(), "NORMAL", enemigoActual.getFuerza());
    }

    public void generarEnemigo() {
        int nivel = (puntuacionActual / 100) + 1;
        this.enemigoActual = new Personaje("Rival", 80 + (nivel * 10), 15 + (nivel * 5), 10 + nivel);
    }

    private void registrarVictoria() {
        puntuacionActual += 100;
        vista.victoria(100);
        jugador.setFuerza(jugador.getFuerza() + 5);
        jugador.setHp(jugador.getHp() + 25);
    }
}
