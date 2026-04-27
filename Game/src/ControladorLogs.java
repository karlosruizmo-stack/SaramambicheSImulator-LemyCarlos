import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ControladorLogs {

        private Vista vista;
        public ControladorLogs(Vista vista) {
            this.vista = vista;
        }

        // de array a .txt
        public void guardarEnArchivo(ArrayList<Partida> p1) {
            try (java.io.FileWriter fw = new java.io.FileWriter("partida.txt", false)) { // 'false' borra lo viejo y escribe lo nuevo
                for (Partida l : p1) {
                    fw.write(l.getIdPartida() + "," + l.getPuntuación() + "," + l.getFecha() + "\n");
                }
            } catch (Exception e) {
                System.out.println("Error al guardar: " + e.getMessage());
            }
        }

        // de .txt al array
        public void cargarDesdeArchivo(ArrayList<Partida> p2) {
            File archivo = new File("partida.txt");
            if (!archivo.exists()) return;
            DateTimeFormatter formateador = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

            try (Scanner lector = new Scanner(archivo)) {
                while (lector.hasNextLine()) {
                    String linea = lector.nextLine();
                    String[] partes = linea.split(","); // Dividimos por la coma
                    if (partes.length == 3) {
                        // Creamos el objeto y lo metemos al array
                        LocalDateTime fecha = LocalDateTime.parse(partes[2], formateador);
                        p2.add(new Partida(Integer.parseInt(partes[0]) ,Integer.parseInt(partes[1]) , fecha));
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al cargar: " + e.getMessage());
            }
        }


    }
