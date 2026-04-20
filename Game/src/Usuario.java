import java.time.LocalDateTime;
import java.util.ArrayList;

public class Usuario {
    private String nomUser;
    private ArrayList<Partida> partida; // Solo la que se está jugando o la última

    public Usuario(String nomUser) {
        this.nomUser = nomUser;
        this.partida = new ArrayList<>();
    }
}