package ejercicio3;

public class Main {
    public static void main(String[] args) {
        ListaDobleUtil lista = new ListaDobleUtil();
        lista.insertarValores();
        lista.imprimirLista();
        System.out.println("Tamaño de la lista: " + lista.obtenerTamano());
    }
}
