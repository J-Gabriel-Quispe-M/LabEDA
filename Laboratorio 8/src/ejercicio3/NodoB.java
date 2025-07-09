package ejercicio3;

import java.util.*;

public class NodoB<E extends Comparable<E>> {
    protected ArrayList<E> llaves;
    protected ArrayList<NodoB<E>> hijos;
    protected int cantidad;

    public NodoB(int n) {
        this.cantidad = 0;
        this.llaves = new ArrayList<>();
        this.hijos = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            this.llaves.add(null);
        }
        for (int i = 0; i < n; i++) {
            this.hijos.add(null);
        }
    }

    public boolean estaVacio() {
        return cantidad == 0;
    }

    public boolean buscarLlave(E llave, int[] posicion) {
        int i = 0;
        while (i < cantidad && llaves.get(i).compareTo(llave) < 0) {
            i++;
        }
        posicion[0] = i;
        return i < cantidad && llaves.get(i).compareTo(llave) == 0;
    }

    public boolean estaLleno(int maxLlaves) {
        return cantidad == maxLlaves;
    }
}
