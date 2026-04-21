import java.io.File;
import java.util.Scanner;

public class Vista {
    Scanner sc = new Scanner(System.in);

    public void mostrarEstado(Personaje l1, Personaje l2) {
        System.out.println("\n--- PELEA SARAMAMBICHE ---");
        System.out.println(l1.getNombre() + " HP: " + l1.getHp());// Uso de getters
        System.out.println(l2.getNombre() + " HP: " + l2.getHp());
        System.out.println("--------------------------");
    }
    public void mostrarAtaque(String atacante, String tecnica) {
        System.out.println("¡" + atacante + " lanza un " + tecnica + "!");
    }

    public void mostrarMensaje(String m) { System.out.println(m); }
    
    public void mostrarLogs(){
        File archivoF = new File("partida.txt");
        if (!archivoF.exists()) return;
        try (Scanner lector = new Scanner(archivoF)){
            String linea = lector.nextLine();
            System.out.println(linea);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int pedirEntero(String mensaje) {
        System.out.print(mensaje);
        int op = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer
        return op;
    }
}
 