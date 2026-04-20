public class Controlador {
    public static void guardarEnArchivo(ArrayList<Libro> lista) {
        try (java.io.FileWriter fw = new java.io.FileWriter("libros.txt", false)) { // 'false' borra lo viejo y escribe lo nuevo
            for (Libro l : lista) {
                fw.write(l.getTitulo() + "," + l.getAutor() + "," + l.getAnioPubli() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
    public static void cargarDesdeArchivo(ArrayList<Libro> lista) {
        File archivo = new File("libros.txt");
        if (!archivo.exists()) return;

        try (Scanner lector = new Scanner(archivo)) {
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] partes = linea.split(","); // Dividimos por la coma
                if (partes.length == 3) {
                    // Creamos el objeto y lo metemos al array
                    lista.add(new Libro(partes[0], partes[1], Integer.parseInt(partes[2])));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }
    }
}
