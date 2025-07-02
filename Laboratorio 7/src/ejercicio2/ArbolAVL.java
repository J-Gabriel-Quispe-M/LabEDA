package ejercico2;

public class ArbolAVL<T extends Comparable<T>> {

    private NodoAVL<T> raiz;

    public boolean estaVacio() {
        return raiz == null;
    }

    public void destruir() {
        raiz = null;
    }

    public void insertar(T dato) {
        raiz = insertar(raiz, dato);
    }

    private NodoAVL<T> insertar(NodoAVL<T> nodo, T dato) {
        if (nodo == null) {
            return new NodoAVL<>(dato);
        }

        if (dato.compareTo(nodo.dato) < 0) {
            nodo.izquierdo = insertar(nodo.izquierdo, dato);
        }
        else if (dato.compareTo(nodo.dato) > 0) {
            nodo.derecho = insertar(nodo.derecho, dato);
        }
        else {
            return nodo; 
        }

        actualizarAltura(nodo);
        return balancear(nodo);
    }

    public void eliminar(T dato) {
        raiz = eliminar(raiz, dato);
    }

    private NodoAVL<T> eliminar(NodoAVL<T> nodo, T dato) {
        if (nodo == null) return null;
        if (dato.compareTo(nodo.dato) < 0) {
            nodo.izquierdo = eliminar(nodo.izquierdo, dato);
        } 
        else if (dato.compareTo(nodo.dato) > 0) {
            nodo.derecho = eliminar(nodo.derecho, dato);
        } 
        else {
            if (nodo.izquierdo == null || nodo.derecho == null) {
                nodo = (nodo.izquierdo != null) ? nodo.izquierdo : nodo.derecho;
            } 
            else {
                NodoAVL<T> minimo = encontrarMinimo(nodo.derecho);
                nodo.dato = minimo.dato;
                nodo.derecho = eliminar(nodo.derecho, minimo.dato);
            }
        }
        if (nodo == null) return null;
        actualizarAltura(nodo);
        return balancear(nodo);
    }

    public T minimo() {
        if (estaVacio()) return null;
        return encontrarMinimo(raiz).dato;
    }

    private NodoAVL<T> encontrarMinimo(NodoAVL<T> nodo) {
        while (nodo.izquierdo != null) nodo = nodo.izquierdo;
        return nodo;
    }

    public T maximo() {
        if (estaVacio()) return null;
        return encontrarMaximo(raiz).dato;
    }

    private NodoAVL<T> encontrarMaximo(NodoAVL<T> nodo) {
        while (nodo.derecho != null) nodo = nodo.derecho;
        return nodo;
    }

    public boolean buscar(T dato) {
        return buscar(raiz, dato);
    }

    private boolean buscar(NodoAVL<T> nodo, T dato) {
        if (nodo == null) return false;
        if (dato.compareTo(nodo.dato) < 0) return buscar(nodo.izquierdo, dato);
        if (dato.compareTo(nodo.dato) > 0) return buscar(nodo.derecho, dato);
        return true;
    }

    public void recorridoInOrden() {
        inOrden(raiz);
        System.out.println();
    }

    private void inOrden(NodoAVL<T> nodo) {
        if (nodo != null) {
            inOrden(nodo.izquierdo);
            System.out.print(nodo.dato + " ");
            inOrden(nodo.derecho);
        }
    }

    public void recorridoPreOrden() {
        preOrden(raiz);
        System.out.println();
    }

    private void preOrden(NodoAVL<T> nodo) {
        if (nodo != null) {
            System.out.print(nodo.dato + " ");
            preOrden(nodo.izquierdo);
            preOrden(nodo.derecho);
        }
    }

    public void recorridoPostOrden() {
        postOrden(raiz);
        System.out.println();
    }

    private void postOrden(NodoAVL<T> nodo) {
        if (nodo != null) {
            postOrden(nodo.izquierdo);
            postOrden(nodo.derecho);
            System.out.print(nodo.dato + " ");
        }
    }

    public T predecesor(T dato) {
        NodoAVL<T> actual = raiz;
        T predecesor = null;
        while (actual != null) {
            if (dato.compareTo(actual.dato) > 0) {
                predecesor = actual.dato;
                actual = actual.derecho;
            }
            else {
                actual = actual.izquierdo;
            }
        }
        return predecesor;
    }

    public T sucesor(T dato) {
        NodoAVL<T> actual = raiz;
        T sucesor = null;
        while (actual != null) {
            if (dato.compareTo(actual.dato) < 0) {
                sucesor = actual.dato;
                actual = actual.izquierdo;
            } 
            else {
                actual = actual.derecho;
            }
        }
        return sucesor;
    }

    private int altura(NodoAVL<T> nodo) {
        return nodo == null ? 0 : nodo.altura;
    }

    private void actualizarAltura(NodoAVL<T> nodo) {
        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
    }

    private int obtenerBalance(NodoAVL<T> nodo) {
        return nodo == null ? 0 : altura(nodo.izquierdo) - altura(nodo.derecho);
    }

    private NodoAVL<T> balancear(NodoAVL<T> nodo) {
        int balance = obtenerBalance(nodo);
        if (balance > 1) {
            if (obtenerBalance(nodo.izquierdo) < 0) {
                nodo.izquierdo = rotacionSimpleDerecha(nodo.izquierdo);
            }
            return rotacionSimpleIzquierda(nodo);
        }
        if (balance < -1) {
            if (obtenerBalance(nodo.derecho) > 0) {
                nodo.derecho = rotacionSimpleIzquierda(nodo.derecho);
            }
            return rotacionSimpleDerecha(nodo);
        }
        return nodo;
    }

    public NodoAVL<T> rotacionSimpleIzquierda(NodoAVL<T> y) {
        NodoAVL<T> x = y.izquierdo;
        NodoAVL<T> temp = x.derecho;
        x.derecho = y;
        y.izquierdo = temp;
        actualizarAltura(y);
        actualizarAltura(x);
        return x;
    }

    public NodoAVL<T> rotacionSimpleDerecha(NodoAVL<T> x) {
        NodoAVL<T> y = x.derecho;
        NodoAVL<T> temp = y.izquierdo;
        y.izquierdo = x;
        x.derecho = temp;
        actualizarAltura(x);
        actualizarAltura(y);
        return y;
    }

    public NodoAVL<T> balancearIzquierda(NodoAVL<T> nodo) {
        return rotacionSimpleIzquierda(nodo);
    }

    public NodoAVL<T> balancearDerecha(NodoAVL<T> nodo) {
        return rotacionSimpleDerecha(nodo);
    }
}