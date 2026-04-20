import java.io.*;

public class ControladorPers {
    private Personaje baki;
    private int recordPuntuacion;
    private String rutaRecord = "record.txt";

    public ControladorPers() { //Establecemos las stats predefinidas on las que comienza el jugador
        this.baki = new Personaje("Baki Hanma", 150, 30, 20);
        this.recordPuntuacion = cargarRecord();
    }

    // Despues de cada combate las stats augmentan aleatoriamente
    public void evolucionAleatoria() {
        int masFuerza = (int) (Math.random() * 10) + 1;
        int masVida = (int) (Math.random() * 15) + 5;

        baki.setFuerza(baki.getFuerza() + masFuerza);
        baki.setHp(baki.getHp() + masVida);

        System.out.println("¡EL CAMINO A LA FUERZA SE HACE MÁS CORTO!");
        System.out.println("Fuerza +" + masFuerza + " | Vida +" + masVida);
    }

    // Pedimos al programa que guarde la puntuación obtenido pero no las stats para que no se junten los archivos para futurs partidas
    public void guardarNuevoRecord(int puntuacionPartida) {
        if (puntuacionPartida > recordPuntuacion) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(rutaRecord))) {
                writer.println(puntuacionPartida);
                this.recordPuntuacion = puntuacionPartida;
                System.out.println("¡Nuevo record");
            } catch (IOException e) {
                System.err.println("Error al guardar récord: " + e.getMessage());
            }
        }
    }

    private int cargarRecord() {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaRecord))) {
            return Integer.parseInt(br.readLine().trim());
        } catch (Exception e) {
            return 0; // Si el programa no encuentra ningun archivo se incia de 0
        }
    }

    public Personaje getBaki() { return baki; }
    public int getRecordPuntuacion() { return recordPuntuacion; }
}
