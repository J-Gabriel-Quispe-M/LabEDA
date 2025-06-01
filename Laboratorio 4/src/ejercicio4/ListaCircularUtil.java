package ejercicio4;

import java.util.LinkedList;

public class ListaCircularUtil {
    private LinkedList<Integer> lista;

    public ListaCircularUtil() {
        lista = new LinkedList<>();
    }

    public void insertarValores() {
        for (int i = 1; i <= 12; i++) {
            lista.add(i);
        }
    }

    public void imprimirCircular(int vueltas) {
        System.out.print("Lista circular: ");
        int size = lista.size();
        for (int i = 0; i < vueltas; i++) {
            int valor = lista.get(i % size); 
            System.out.print(valor + " ");
        }
        System.out.println();
    }

    public int obtenerTamano() {
        return lista.size();
    }
}