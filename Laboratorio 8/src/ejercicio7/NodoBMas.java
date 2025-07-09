package ejercicio7;

import java.util.ArrayList;
import java.util.Collections;

public class NodoBMas<E extends Comparable<E>> {
    ArrayList<E> llaves;                      
    ArrayList<NodoBMas<E>> hijos;             
    int cantidad;                             
    boolean esHoja;                           
    NodoBMas<E> siguiente;                    

    public NodoBMas(int orden, boolean esHoja) {
        this.cantidad = 0;
        this.esHoja = esHoja;
        this.llaves = new ArrayList<>(Collections.nCopies(orden - 1, null));
        this.hijos = new ArrayList<>(Collections.nCopies(orden, null));
        this.siguiente = null;
    }

    public NodoBMas(int orden) {
        this(orden, true);
    }

    public boolean estaLleno(int maxLlaves) {
        return this.cantidad >= maxLlaves;
    }

    public boolean buscarLlave(E llave, int[] pos) {
        pos[0] = 0;
        while (pos[0] < this.cantidad && llave.compareTo(this.llaves.get(pos[0])) > 0) {
            pos[0]++;
        }
        return (pos[0] < this.cantidad && llave.compareTo(this.llaves.get(pos[0])) == 0);
    }
}

