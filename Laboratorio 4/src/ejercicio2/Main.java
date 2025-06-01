package ejercicio2;

public class Main {
    public static void main(String[] args) {
        ListaEnlazadaCircular lista = new ListaEnlazadaCircular();
        for (int i = 1; i <= 12; i++) {
            lista.insertar(i);
        }
        lista.imprimir();
        System.out.println("¿Es circular?: " + lista.esCircular());
    }
}