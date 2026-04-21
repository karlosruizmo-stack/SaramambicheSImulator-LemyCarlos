public static void main(String[] args) {
    Vista vista = new Vista();
    ControladorLogs logs = new ControladorLogs(vista);
    ControladorJuego juego = new ControladorJuego(vista, logs);
1
    juego.iniciarJuego();
}