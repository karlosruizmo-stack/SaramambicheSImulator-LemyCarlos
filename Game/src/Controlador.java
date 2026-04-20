import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Controlador {
    private Vista vista;

    public Controlador(Vista vista) {
        this.vista = vista;
    }

    public void registrarPartida(Partida p) {
        // El 'true' permite que las partidas se vayan acumulando en el txt
        try (PrintWriter pw = new PrintWriter(new FileWriter("partida.txt", true))) {
            pw.println(p.getIdPartida() + "," + p.getPuntuación() + "," + p.getFecha());
        } catch (Exception e) {
            System.err.println("Error al registrar: " + e.getMessage());
        }
    }

    public void mostrarHistorial() {
        File archivo = new File("partida.txt");
        if (!archivo.exists()) return;

        try (Scanner lector = new Scanner(archivo)) {
            System.out.println("--- HISTORIAL DE PARTIDAS ---");
            while (lector.hasNextLine()) {
                System.out.println(lector.nextLine());
            }
        } catch (Exception e) {
            System.err.println("Error al leer: " + e.getMessage());
        }
    }
}
