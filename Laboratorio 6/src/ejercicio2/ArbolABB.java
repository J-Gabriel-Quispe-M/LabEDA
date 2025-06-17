package ejercicio2;

public class ArbolABB<T extends Comparable<T>> {
    private Nodo<T> raiz;

    public ArbolABB() {
        raiz = null;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public void insertar(T dato) {
        raiz = insertarRec(raiz, dato);
    }

    private Nodo<T> insertarRec(Nodo<T> nodo, T dato) {
        if (nodo == null) return new Nodo<>(dato);
        if (dato.compareTo(nodo.dato) < 0)
            nodo.izquierdo = insertarRec(nodo.izquierdo, dato);
        else
            nodo.derecho = insertarRec(nodo.derecho, dato);
        return nodo;
    }

    public boolean buscar(T dato) {
        return buscarRec(raiz, dato);
    }

    private boolean buscarRec(Nodo<T> nodo, T dato) {
        if (nodo == null) return false;
        if (dato.compareTo(nodo.dato) == 0) return true;
        if (dato.compareTo(nodo.dato) < 0)
            return buscarRec(nodo.izquierdo, dato);
        else
            return buscarRec(nodo.derecho, dato);
    }

    public void eliminar(T dato) {
        raiz = eliminarRec(raiz, dato);
    }

    private Nodo<T> eliminarRec(Nodo<T> nodo, T dato) {
        if (nodo == null) return null;

        if (dato.compareTo(nodo.dato) < 0)
            nodo.izquierdo = eliminarRec(nodo.izquierdo, dato);
        else if (dato.compareTo(nodo.dato) > 0)
            nodo.derecho = eliminarRec(nodo.derecho, dato);
        else {
            if (nodo.izquierdo == null) return nodo.derecho;
            else if (nodo.derecho == null) return nodo.izquierdo;

            nodo.dato = valorMinimo(nodo.derecho);
            nodo.derecho = eliminarRec(nodo.derecho, nodo.dato);
        }
        return nodo;
    }

    private T valorMinimo(Nodo<T> nodo) {
        T min = nodo.dato;
        while (nodo.izquierdo != null) {
            min = nodo.izquierdo.dato;
            nodo = nodo.izquierdo;
        }
        return min;
    }

    public T minimo() {
        if (estaVacio()) return null;
        Nodo<T> actual = raiz;
        while (actual.izquierdo != null) actual = actual.izquierdo;
        return actual.dato;
    }

    public T maximo() {
        if (estaVacio()) return null;
        Nodo<T> actual = raiz;
        while (actual.derecho != null) actual = actual.derecho;
        return actual.dato;
    }

    public T predecesor(T dato) {
        Nodo<T> actual = raiz;
        Nodo<T> pre = null;
        while (actual != null) {
            if (dato.compareTo(actual.dato) > 0) {
                pre = actual;
                actual = actual.derecho;
            } 
            else {
                actual = actual.izquierdo;
            }
        }
        return pre != null ? pre.dato : null;
    }

    public T sucesor(T dato) {
        Nodo<T> actual = raiz;
        Nodo<T> suc = null;
        while (actual != null) {
            if (dato.compareTo(actual.dato) < 0) {
                suc = actual;
                actual = actual.izquierdo;
            } 
            else {
                actual = actual.derecho;
            }
        }
        return suc != null ? suc.dato : null;
    }

    public void recorridoInOrden() {
        System.out.print("InOrden: ");
        inOrdenRec(raiz);
        System.out.println();
    }

    private void inOrdenRec(Nodo<T> nodo) {
        if (nodo != null) {
            inOrdenRec(nodo.izquierdo);
            System.out.print(nodo.dato + " ");
            inOrdenRec(nodo.derecho);
        }
    }

    public void recorridoPreOrden() {
        System.out.print("PreOrden: ");
        preOrdenRec(raiz);
        System.out.println();
    }

    private void preOrdenRec(Nodo<T> nodo) {
        if (nodo != null) {
            System.out.print(nodo.dato + " ");
            preOrdenRec(nodo.izquierdo);
            preOrdenRec(nodo.derecho);
        }
    }

    public void recorridoPostOrden() {
        System.out.print("PostOrden: ");
        postOrdenRec(raiz);
        System.out.println();
    }

    private void postOrdenRec(Nodo<T> nodo) {
        if (nodo != null) {
            postOrdenRec(nodo.izquierdo);
            postOrdenRec(nodo.derecho);
            System.out.print(nodo.dato + " ");
        }
    }

    public void destruir() {
        raiz = null;
    }
}