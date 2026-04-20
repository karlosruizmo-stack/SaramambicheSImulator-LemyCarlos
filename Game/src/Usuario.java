public class Usuario {
    private String nomUser;
    private Partida partidaActual; // Solo la que se está jugando o la última

    public Usuario(String nomUser, Partida partidaActual) {
        this.nomUser = nomUser;
        this.partidaActual = new Partida(0,0,20/04/2026);
    }
}