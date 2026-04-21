import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Vista {
    Scanner sc = new Scanner(System.in);

    public int mostrarMenuPrincipal() {
        System.out.println("\n--- SARAMAMBICHE RPG: THE ULTIMATE FIGHTER ---");
        System.out.println("1. Buscar pelea (Auto-Battle)");
        System.out.println("2. Ver estadísticas de Baki");
        System.out.println("0. Salir");
        System.out.print("Selección: ");
        int op = sc.nextInt();
        sc.nextLine();
        return op;
    }

    public void mostrarInicioCombate(String oponente) {
        System.out.println("\n[!] ¡Un saramambiche " + oponente + " ha aparecido!");
    }

    public void mostrarEstado(Personaje p1, Personaje p2) {
        System.out.println("\n----------------------------------");
        System.out.println(String.format("%-15s HP: %d", p1.getNombre(), p1.getHp()));
        System.out.println(String.format("%-15s HP: %d", p2.getNombre(), p2.getHp()));
        System.out.println("----------------------------------");
    }

    public void infoAtaque(String atacante, String tipo, int dano) {
        String decoracion = tipo.equals("ESPECIAL") ? " [CRÍTICO] " : "->";
        System.out.println(decoracion + " " + atacante + " usa " + tipo + " y causa " + dano + " de daño.");
    }

    public void mostrarCargaEspecial(int contador) {
        System.out.println("[Sistema] Carga de especial: " + contador + "/2");
    }

    public void victoria(int puntos) {
        System.out.println("\n¡VICTORIA! Has ganado " + puntos + " puntos.");
    }

    public void derrota() {
        System.out.println("\nK.O. - Baki ha caído en combate...");
    }

    public void mostrarMensaje(String m) { System.out.println(m); }

    public void mostrarLogs(ArrayList<Partida> historialPartidas) {
    }
}
 