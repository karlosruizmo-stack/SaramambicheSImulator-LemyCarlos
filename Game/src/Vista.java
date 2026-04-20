public class Vista {
    public void mostrarEstado(Personaje l1, Personaje l2) {
        System.out.println("\n--- PELEA SARAMAMBICHE ---");
        System.out.println(l1.getNombre() + " HP: " + l1.getHp());// Uso de getters
        System.out.println(l2.getNombre() + " HP: " + l2.getHp());
        System.out.println("--------------------------");
    }

    public void mostrarAtaque(String atacante, String tecnica) {
        System.out.println("¡" + atacante + " lanza un " + tecnica + "!");
    }
}
 