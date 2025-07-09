
import java.util.ArrayList;
import java.util.Collections;

public class NodoBMas<E extends Comparable<E>> {
    ArrayList<E> keys;              
    ArrayList<NodoBMas<E>> childs;     
    int count;                      
    boolean isLeaf;                
    NodoBMas<E> next;                  
    public boolean esHoja;
    public int cantidad;
    public Object hijos;
    public Object claves;

    public NodoBMas(int orden, boolean isLeaf) {
        this.count = 0;
        this.isLeaf = isLeaf;
        this.keys = new ArrayList<>(Collections.nCopies(orden - 1, null));
        this.childs = new ArrayList<>(Collections.nCopies(orden, null));
        this.next = null;
    }

    public NodoBMas(int orden) {
        this(orden, true);
    }

    public boolean nodeFull(int maxKeys) {
        return this.count >= maxKeys;
    }

    public boolean searchNode(E key, int[] pos) {
        pos[0] = 0;
        while (pos[0] < this.count && key.compareTo(this.keys.get(pos[0])) > 0) {
            pos[0]++;
        }
        return (pos[0] < this.count && key.compareTo(this.keys.get(pos[0])) == 0);
    }
}

