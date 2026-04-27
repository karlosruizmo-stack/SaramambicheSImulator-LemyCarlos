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

    public void victoria(int puntos) {
        System.out.println("\n¡VICTORIA! Has ganado " + puntos + " puntos.");
    }

    public void derrota() {
        System.out.println("\nK.O. - Baki ha caído en combate...");
    }

    public void mostrarMensaje(String m) { System.out.println(m); }

    public void mostrarLogs(ArrayList<Partida> historialPartidas) {
        System.out.println("\n--- HISTORIAL DE PARTIDAS ---");
        if (historialPartidas.isEmpty()) {
            System.out.println("No hay partidas registradas aún.");
        } else {
            for (Partida p : historialPartidas) {
                System.out.println("ID: " + p.getIdPartida() + " | Puntos: " + p.getPuntuación() + " | Fecha: " + p.getFecha());
            }
        }
    }
    public int menuCombate(int carga) {
        System.out.println("\n-- Turno de Baki (Carga: " + carga + "/2) --");
        System.out.println("1. Ataque Normal");
        if (carga >= 2) {
            System.out.println("2. Técnica Especial [LISTO]");
        } else {
            System.out.println("2. Técnica Especial [BLOQUEADO]");
        }
        System.out.print("Acción: ");
        int eleccion = sc.nextInt();
        sc.nextLine();
        return eleccion;
    }

}
 