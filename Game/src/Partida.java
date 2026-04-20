import java.time.LocalDateTime;

public class Partida {
    private int idPartida;
    private int puntuación;
    private String resultado; // Ej: "Victoria", "Derrota"
    private LocalDateTime fecha;

    public Partida(int idPartida, int puntuación, LocalDateTime fecha) {
        this.idPartida = idPartida;
        this.puntuación = puntuación;
        this.fecha = fecha;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public int getPuntuación() {
        return puntuación;
    }

    public void setPuntuación(int puntuación) {
        this.puntuación = puntuación;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
