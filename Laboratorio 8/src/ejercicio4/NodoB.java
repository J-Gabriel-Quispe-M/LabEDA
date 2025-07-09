import java.util.*;

public class NodoB<E extends Comparable<E>> {
    protected ArrayList<E> claves;
    protected ArrayList<NodoB<E>> hijos;
    protected int cantidad;

    public NodoB(int n) {
        this.cantidad = 0;
        this.claves = new ArrayList<>();
        this.hijos = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            this.claves.add(null);
        }
        for (int i = 0; i < n; i++) {
            this.hijos.add(null);
        }
    }

    public boolean nodoVacio() {
        return cantidad == 0;
    }

    public boolean buscarEnNodo(E clave, int[] pos) {
        int i = 0;
        while (i < cantidad && claves.get(i).compareTo(clave) < 0) {
            i++;
        }
        pos[0] = i;
        return i < cantidad && claves.get(i).compareTo(clave) == 0;
    }

    public boolean nodoLleno(int maxClaves) {
        return cantidad == maxClaves;
    }
}
