import java.util.ArrayList;

public class Usuario {
    private String nomUser;
    private ArrayList<Partida> party;

    public Usuario(String nomUser) {
        this.nomUser = nomUser;
        this.party = new ArrayList<>();
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public ArrayList<Partida> getParty() {
        return party;
    }

    public void setParty(ArrayList<Partida> party) {
        this.party = party;
    }
}
