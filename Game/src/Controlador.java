import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Controlador {

    // de array a .txt
    public  void guardarEnArchivo(ArrayList<Usuario> p1) {
        try (java.io.FileWriter fw = new java.io.FileWriter("usuarios.txt", false)) { // 'false' borra lo viejo y escribe lo nuevo
            for (Usuario l : p1) {
                fw.write(l.getTitulo() + "," + l.getAutor() + "," + l.getAnioPubli() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
    // de .txt al array
    public  void cargarDesdeArchivo(ArrayList<Usuario> p2) {
        File archivo = new File("usuarios.txt");
        if (!archivo.exists()) return;

        try (Scanner lector = new Scanner(archivo)) {
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] partes = linea.split(","); // Dividimos por la coma
                if (partes.length == 3) {
                    // Creamos el objeto y lo metemos al array
                    p2.add(new Usuario(partes[0], partes[1], Integer.parseInt(partes[2])));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }
    }
}
