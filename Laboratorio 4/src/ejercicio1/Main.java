package ejercicio1;

public class Main {
    public static void main(String[] args) {
        ListaDobleEnlazada lista = new ListaDobleEnlazada();
        for (int i = 1; i <= 10; i++) {
            lista.insertar(i);
        }
        lista.imprimirAdelante();
        lista.imprimirAtras();
    }
}