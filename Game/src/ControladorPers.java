import java.io.*;

public class ControladorPers {
    private Personaje baki;
    private int recordPuntuacion;
    private int puntuacionActual; // Puntos acumulados en la sesión de juego
    private String rutaRecord = "record.txt";

    public ControladorPers() {
        // Establecemos las stats predefinidas con las que comienza el jugador
        this.baki = new Personaje("Baki Hanma", 150, 30, 20);
        this.recordPuntuacion = cargarRecord();
        this.puntuacionActual = 0;
    }
    public void registrarVictoria() {
        this.puntuacionActual += 100;
        System.out.println("Victoria aplastante +100 puntos.");
        System.out.println("Puntuación actual: " + this.puntuacionActual);

        // Ejecutamos la subida de stats aleatoria
        evolucionAleatoria();
    }

    // Despues de cada combate las stats aumentan aleatoriamente
    public void evolucionAleatoria() {
        int masFuerza = (int) (Math.random() * 10) + 1;
        int masVida = (int) (Math.random() * 15) + 5;

        baki.setFuerza(baki.getFuerza() + masFuerza);
        baki.setHp(baki.getHp() + masVida);

        System.out.println("El camino a la fuerza se hace más corto");
        System.out.println("Fuerza +" + masFuerza + " | Vida +" + masVida);
    }

    // Se llama al morir o salir para ver si superamos el record
    public void finalizarPartida() {
        System.out.println("\n--- RESUMEN DE LA SESIÓN ---");
        System.out.println("Puntos totales: " + this.puntuacionActual);

        if (this.puntuacionActual > recordPuntuacion) {
            System.out.println("Has superado el récord anterior de " + recordPuntuacion);
            guardarNuevoRecord(this.puntuacionActual);
        } else {
            System.out.println("Aun te falta mejorar " + recordPuntuacion);
        }
    }

    // Guarda la puntuación obtenida en el archivo
    public void guardarNuevoRecord(int puntuacionPartida) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaRecord))) {
            writer.println(puntuacionPartida);
            this.recordPuntuacion = puntuacionPartida;
            System.out.println("Nuevo record guardado con éxito");
        } catch (IOException e) {
            System.err.println("Error al guardar récord: " + e.getMessage());
        }// Implementación de try catch para evitar la perdida de archivos
    }

    private int cargarRecord() {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaRecord))) {
            return Integer.parseInt(br.readLine().trim());
        } catch (Exception e) {
            return 0; // Si no encuentra el archivo, se inicia desde cero
        }
    }

    // Getters
    public Personaje getBaki() { return baki; }
    public int getRecordPuntuacion() { return recordPuntuacion; }
    public int getPuntuacionActual() { return puntuacionActual; }
}
