import java.time.LocalDateTime;
import java.util.ArrayList;

public class ControladorJuego {

    private Personaje jugador;
    private Personaje enemigoActual;
    private Vista vista;
    private ControladorLogs controlLogs;

    private ArrayList<Partida> historialPartidas; // El ArrayList donde cargamos todo
    private int recordPuntuacion;
    private int puntuacionActual;
    private int contadorAtaquesBasicos = 0;

    public ControladorJuego(Vista vista, ControladorLogs controlLogs) {
        this.vista = vista;
        this.controlLogs = controlLogs;
        this.historialPartidas = new ArrayList<>();

        // Cargar Controllogs --> Array historialPartidas
        this.controlLogs.cargarDesdeArchivo(this.historialPartidas);
        // Registra la puntuacion más alta del array historialPartidas
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

    // VISTA mostrarMenuPrincipal --> Devuelve un valor tipo Int
    public void iniciarJuego() {
        int op = -1;
        while (op != 0) {
            op = vista.mostrarMenuPrincipal();
            if (op == 1) {
                flujoCombate();
            } else if (op == 2) {
                vista.mostrarLogs(historialPartidas);
                vista.mostrarMensaje("Récord absoluto: " + recordPuntuacion);
            }
        }
        // GUARDAR
        controlLogs.guardarEnArchivo(historialPartidas);
        vista.mostrarMensaje("Saliendo... Progreso guardado.");
    }

    private void flujoCombate() {
        generarEnemigo();
        vista.mostrarInicioCombate(enemigoActual.getNombre());

        // BORRAMOS: contadorAtaquesBasicos = 0;
        // Al quitar esa línea, el contador se mantiene de una pelea a otra.

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
            gestionarFinDePartida();
        }
    }

    private void registrarVictoria() {
        puntuacionActual += 100;
        vista.victoria(100);

        // MEJORA 1: Curación aleatoria (ejemplo: entre 20 y 50 de vida)
        int curacion = (int) (Math.random() * 31) + 20;
        int nuevaVida = jugador.getHp() + curacion;

        // Evitamos que la vida supere el máximo inicial (ej. 150)
        if (nuevaVida > 150) nuevaVida = 150;

        jugador.setHp(nuevaVida);

        // MEJORA 2: Aumento de fuerza
        jugador.setFuerza(jugador.getFuerza() + 5);

        vista.mostrarMensaje("Baki descansa. Recupera " + curacion + " HP. Nueva fuerza: " + jugador.getFuerza());
    }

    private void gestionarFinDePartida() {

        int nuevoId = historialPartidas.size() + 1;
        Partida partidaFinalizada = new Partida(nuevoId, puntuacionActual, LocalDateTime.now());

        historialPartidas.add(partidaFinalizada);

        // Actualizamos el récord en memoria para la sesión actual
        if (puntuacionActual > recordPuntuacion) {
            recordPuntuacion = puntuacionActual;
            vista.mostrarMensaje("¡NUEVO RÉCORD REGISTRADO: " + recordPuntuacion + "!");
        }

        // Guardamos todo el historial al archivo
        controlLogs.guardarEnArchivo(historialPartidas);

        //RESET
        puntuacionActual = 0;
        jugador.setHp(150);
        jugador.setFuerza(35);
    }

    private void ejecutarTurnoJugador() {
        // Pedimos al usuario que elija
        int accion = vista.menuCombate(contadorAtaquesBasicos);

        if (accion == 1) {
            // Ataque Normal
            int dano = jugador.getFuerza();
            enemigoActual.recibirDaño(dano);
            vista.infoAtaque(jugador.getNombre(), "Ataque Normal", dano);
            contadorAtaquesBasicos++;

        } else if (accion == 2 && contadorAtaquesBasicos >= 2) {
            // Ataque Especial (Solo si tiene carga)
            int danoEspecial = jugador.getFuerza() * 2;
            enemigoActual.recibirDaño(danoEspecial);
            vista.infoAtaque(jugador.getNombre(), "Técnica Especial", danoEspecial);
            contadorAtaquesBasicos = 0; // Reset

        } else {
            // Si elige el especial sin carga o una opción inválida
            vista.mostrarMensaje("Fallo en la ejecución. Pierdes el ritmo.");
        }
    }

    private void ejecutarTurnoEnemigo() {
        jugador.recibirDaño(enemigoActual.getFuerza());
        vista.infoAtaque(enemigoActual.getNombre(), "NORMAL", enemigoActual.getFuerza());
    }

    public void generarEnemigo() {
        int nivel = (puntuacionActual / 100) + 1;
        String[] nombres = {"Jack Hammer", "Doppo Orochi", "Retsu Kaioh", "Hanayama", "Oliva"};
        String nombreAleatorio = nombres[(int) (Math.random() * nombres.length)];

        int hp = 80 + (nivel * 10);
        int str = 15 + (nivel * 5);
        int def = 10 + (nivel * 2);

        this.enemigoActual = new Personaje(nombreAleatorio, hp, str, def);
    }
}
