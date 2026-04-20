import java.io.*;
import java.util.ArrayList;

public class ControladorPers {
    // Lista donde guardaremos a los personajes cargados desde el fichero
    private ArrayList<Personaje> listaPersonajes;
    private String rutaArchivo = "personajes.txt";

    public ControladorPers() {
        this.listaPersonajes = new ArrayList<>();
        cargarPersonajes(); // Intentamos cargar al iniciar el controlador
    }

    // MÉTODO CLAVE: Lee el fichero y crea los objetos Personaje
    public void cargarPersonajes() {
        // Usamos try-with-resources para que el archivo se cierre solo (Requisito: Try-Catch)
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Suponemos formato: Nombre,HP,Fuerza,Defensa
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    String nombre = partes[0];
                    int hp = Integer.parseInt(partes[1].trim());
                    int fuerza = Integer.parseInt(partes[2].trim());
                    int defensa = Integer.parseInt(partes[3].trim());

                    // Creamos el modelo y lo añadimos a la lista
                    listaPersonajes.add(new Personaje(nombre, hp, fuerza, defensa));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: No se encontró el archivo " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de personajes.");
        } catch (NumberFormatException e) {
            System.err.println("Error: Hay un dato numérico mal escrito en el fichero.");
        }
    }

    // Método para buscar un personaje por nombre (ej: para elegir a Yujiro)
    public Personaje buscarPersonaje(String nombre) {
        for (Personaje p : listaPersonajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null; // Si no lo encuentra
    }

    // Getter para que el Main pueda ver la lista
    public ArrayList<Personaje> getListaPersonajes() {
        return listaPersonajes;
    }
}
