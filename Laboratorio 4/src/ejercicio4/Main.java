package ejercicio4;

public class Main {
    public static void main(String[] args) {
        ListaCircularUtil lista = new ListaCircularUtil();
        lista.insertarValores();
        lista.imprimirCircular(20); 
        System.out.println("Tamaño de la lista: " + lista.obtenerTamano());
    }
}