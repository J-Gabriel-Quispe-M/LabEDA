public class ArbolB<E extends Comparable<E>> implements IArbolB<E> {
    protected NodoB<E> raiz;
    private int orden;
    private boolean subir;
    private NodoB<E> nuevoDesbordado;

    public ArbolB(int orden) {
        this.orden = orden;
        this.raiz = null;
    }

    public ArbolB() {}

    public boolean estaVacio() {
        return this.raiz == null;
    }

    public void destruir() {
        this.raiz = null;
    }

    public void insertar(E clave) {
        subir = false;
        E mediana;
        NodoB<E> nuevoNodo;
        mediana = insertarRecursivo(this.raiz, clave);
        if (subir) {
            nuevoNodo = new NodoB<E>(this.orden);
            nuevoNodo.cantidad = 1;
            nuevoNodo.claves.set(0, mediana);
            nuevoNodo.hijos.set(0, this.raiz);
            nuevoNodo.hijos.set(1, nuevoDesbordado);
            this.raiz = nuevoNodo;
        }
    }

    private E insertarRecursivo(NodoB<E> actual, E clave) {
        int[] pos = new int[1];
        E mediana;
        if (actual == null) {
            subir = true;
            nuevoDesbordado = null;
            return clave;
        }
        boolean existe = actual.buscarEnNodo(clave, pos);
        if (existe) {
            System.out.println("Elemento duplicado\n");
            subir = false;
            return null;
        }
        mediana = insertarRecursivo(actual.hijos.get(pos[0]), clave);
        if (subir) {
            if (actual.nodoLleno(this.orden - 1)) {
                mediana = dividirNodo(actual, mediana, pos[0]);
            } else {
                insertarEnNodo(actual, mediana, nuevoDesbordado, pos[0]);
                subir = false;
            }
        }
        return mediana;
    }

    private void insertarEnNodo(NodoB<E> actual, E clave, NodoB<E> hijoDerecho, int k) {
        for (int i = actual.cantidad - 1; i >= k; i--) {
            actual.claves.set(i + 1, actual.claves.get(i));
            actual.hijos.set(i + 2, actual.hijos.get(i + 1));
        }
        actual.claves.set(k, clave);
        actual.hijos.set(k + 1, hijoDerecho);
        actual.cantidad++;
    }

    private E dividirNodo(NodoB<E> actual, E clave, int k) {
        NodoB<E> derecho = nuevoDesbordado;
        int posMediana = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;
        nuevoDesbordado = new NodoB<E>(this.orden);

        for (int i = posMediana; i < this.orden - 1; i++) {
            nuevoDesbordado.claves.set(i - posMediana, actual.claves.get(i));
            nuevoDesbordado.hijos.set(i - posMediana + 1, actual.hijos.get(i + 1));
        }

        nuevoDesbordado.cantidad = (this.orden - 1) - posMediana;
        actual.cantidad = posMediana;

        if (k <= this.orden / 2) {
            insertarEnNodo(actual, clave, derecho, k);
        } else {
            insertarEnNodo(nuevoDesbordado, clave, derecho, k - posMediana);
        }

        E mediana = actual.claves.get(actual.cantidad - 1);
        nuevoDesbordado.hijos.set(0, actual.hijos.get(actual.cantidad));
        actual.cantidad--;
        return mediana;
    }

    public String toString() {
        if (estaVacio()) return "Árbol B vacío";
        return escribirArbol(this.raiz);
    }

    private String escribirArbol(NodoB<E> actual) {
        String s = "| ";
        for (int i = 0; i < actual.cantidad; i++) {
            if (actual.hijos.get(i) != null)
                s += escribirArbol(actual.hijos.get(i));
            s += actual.claves.get(i).toString() + " ";
        }
        if (actual.hijos.get(actual.cantidad) != null)
            s += escribirArbol(actual.hijos.get(actual.cantidad));
        s += "| ";
        return s;
    }

    public boolean buscar(E clave) {
        return buscarRecursivo(this.raiz, clave);
    }

    private boolean buscarRecursivo(NodoB<E> nodo, E clave) {
        if (nodo == null) return false;
        int i = 0;
        while (i < nodo.cantidad && clave.compareTo(nodo.claves.get(i)) > 0) i++;
        if (i < nodo.cantidad && clave.compareTo(nodo.claves.get(i)) == 0)
            return true;
        return buscarRecursivo(nodo.hijos.get(i), clave);
    }

    public E minimo() {
        if (this.estaVacio()) return null;
        NodoB<E> actual = this.raiz;
        while (actual.hijos.get(0) != null) {
            actual = actual.hijos.get(0);
        }
        return actual.claves.get(0);
    }

    public E maximo() {
        if (this.estaVacio()) return null;
        NodoB<E> actual = this.raiz;
        while (actual.hijos.get(actual.cantidad) != null) {
            actual = actual.hijos.get(actual.cantidad);
        }
        return actual.claves.get(actual.cantidad - 1);
    }

    public E predecesor(E clave) {
        return predecesorRecursivo(this.raiz, clave, null);
    }

    private E predecesorRecursivo(NodoB<E> nodo, E clave, E ultimoIzq) {
        if (nodo == null) return ultimoIzq;
        int i = 0;
        while (i < nodo.cantidad && clave.compareTo(nodo.claves.get(i)) > 0) {
            ultimoIzq = nodo.claves.get(i);
            i++;
        }
        if (i < nodo.cantidad && clave.compareTo(nodo.claves.get(i)) == 0) {
            if (nodo.hijos.get(i) != null) {
                NodoB<E> tmp = nodo.hijos.get(i);
                while (tmp.hijos.get(tmp.cantidad) != null) {
                    tmp = tmp.hijos.get(tmp.cantidad);
                }
                return tmp.claves.get(tmp.cantidad - 1);
            }
            return ultimoIzq;
        }
        return predecesorRecursivo(nodo.hijos.get(i), clave, ultimoIzq);
    }

    public E sucesor(E clave) {
        return sucesorRecursivo(this.raiz, clave, null);
    }

    private E sucesorRecursivo(NodoB<E> nodo, E clave, E ultimoDer) {
        if (nodo == null) return ultimoDer;
        int i = 0;
        while (i < nodo.cantidad && clave.compareTo(nodo.claves.get(i)) > 0) {
            i++;
        }
        if (i < nodo.cantidad && clave.compareTo(nodo.claves.get(i)) == 0) {
            if (nodo.hijos.get(i + 1) != null) {
                NodoB<E> tmp = nodo.hijos.get(i + 1);
                while (tmp.hijos.get(0) != null) {
                    tmp = tmp.hijos.get(0);
                }
                return tmp.claves.get(0);
            }
            return ultimoDer;
        }
        if (i < nodo.cantidad) ultimoDer = nodo.claves.get(i);
        return sucesorRecursivo(nodo.hijos.get(i), clave, ultimoDer);
    }

    public void eliminar(E valor) {
        eliminarRecursivo(this.raiz, valor);

        if (this.raiz != null && this.raiz.cantidad == 0) {
            this.raiz = this.raiz.hijos.get(0);
        }
    }

    private void eliminarRecursivo(NodoB<E> actual, E valor) {
        int i = 0;
        while (i < actual.cantidad && valor.compareTo(actual.claves.get(i)) > 0) {
            i++;
        }
        if (i < actual.cantidad && valor.compareTo(actual.claves.get(i)) == 0) {
            if (actual.hijos.get(i) == null) {
                for (int j = i; j < actual.cantidad - 1; j++) {
                    actual.claves.set(j, actual.claves.get(j + 1));
                }
                actual.cantidad--;
            } else {
                NodoB<E> aux = actual.hijos.get(i + 1);
                while (aux.hijos.get(0) != null)
                    aux = aux.hijos.get(0);
                E sucesor = aux.claves.get(0);
                actual.claves.set(i, sucesor);
                eliminarRecursivo(actual.hijos.get(i + 1), sucesor);
            }
        } else {
            if (actual.hijos.get(i) == null) {
                System.out.println("El valor no existe en el árbol.");
                return;
            }
            if (actual.hijos.get(i).cantidad < (orden - 1) / 2 + 1) {
                NodoB<E> izquierdo = (i > 0) ? actual.hijos.get(i - 1) : null;
                NodoB<E> derecho = (i < actual.cantidad) ? actual.hijos.get(i + 1) : null;

                if (izquierdo != null && izquierdo.cantidad > (orden - 1) / 2) {
                    NodoB<E> hijo = actual.hijos.get(i);
                    for (int j = hijo.cantidad - 1; j >= 0; j--) {
                        hijo.claves.set(j + 1, hijo.claves.get(j));
                    }
                    for (int j = hijo.cantidad; j >= 0; j--) {
                        hijo.hijos.set(j + 1, hijo.hijos.get(j));
                    }
                    hijo.claves.set(0, actual.claves.get(i - 1));
                    hijo.hijos.set(0, izquierdo.hijos.get(izquierdo.cantidad));
                    hijo.cantidad++;
                    actual.claves.set(i - 1, izquierdo.claves.get(izquierdo.cantidad - 1));
                    izquierdo.cantidad--;
                } else if (derecho != null && derecho.cantidad > (orden - 1) / 2) {
                    NodoB<E> hijo = actual.hijos.get(i);
                    hijo.claves.set(hijo.cantidad, actual.claves.get(i));
                    hijo.hijos.set(hijo.cantidad + 1, derecho.hijos.get(0));
                    hijo.cantidad++;
                    actual.claves.set(i, derecho.claves.get(0));
                    for (int j = 0; j < derecho.cantidad - 1; j++) {
                        derecho.claves.set(j, derecho.claves.get(j + 1));
                    }
                    for (int j = 0; j < derecho.cantidad; j++) {
                        derecho.hijos.set(j, derecho.hijos.get(j + 1));
                    }
                    derecho.cantidad--;
                } else {
                    if (izquierdo != null) {
                        fusionarNodos(izquierdo, actual.hijos.get(i), actual, i - 1);
                        eliminarRecursivo(izquierdo, valor);
                    } else if (derecho != null) {
                        fusionarNodos(actual.hijos.get(i), derecho, actual, i);
                        eliminarRecursivo(actual.hijos.get(i), valor);
                    }
                    return;
                }
            }
            eliminarRecursivo(actual.hijos.get(i), valor);
        }
    }

    private void fusionarNodos(NodoB<E> izquierdo, NodoB<E> derecho, NodoB<E> padre, int indice) {
        izquierdo.claves.set(izquierdo.cantidad, padre.claves.get(indice));
        izquierdo.cantidad++;
        for (int i = 0; i < derecho.cantidad; i++) {
            izquierdo.claves.set(izquierdo.cantidad, derecho.claves.get(i));
            izquierdo.hijos.set(izquierdo.cantidad, derecho.hijos.get(i));
            izquierdo.cantidad++;
        }
        izquierdo.hijos.set(izquierdo.cantidad, derecho.hijos.get(derecho.cantidad));
        for (int i = indice; i < padre.cantidad - 1; i++) {
            padre.claves.set(i, padre.claves.get(i + 1));
            padre.hijos.set(i + 1, padre.hijos.get(i + 2));
        }
        padre.cantidad--;
    }
}
<