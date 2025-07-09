package ejercicio3;

public class ArbolB<E extends Comparable<E>> implements IArbolB<E> {
    private NodoB<E> raiz;
    private int orden;
    private boolean subir;
    private NodoB<E> nodoDesbordado;

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

    public void insertar(E valor) {
        subir = false;
        E mediana;
        NodoB<E> nuevoNodo;
        mediana = empujar(this.raiz, valor);
        if (subir) {
            nuevoNodo = new NodoB<E>(this.orden);
            nuevoNodo.cantidad = 1;
            nuevoNodo.llaves.set(0, mediana);
            nuevoNodo.hijos.set(0, this.raiz);
            nuevoNodo.hijos.set(1, nodoDesbordado);
            this.raiz = nuevoNodo;
        }
    }

    private E empujar(NodoB<E> actual, E valor) {
        int[] pos = new int[1];
        E mediana;
        if (actual == null) {
            subir = true;
            nodoDesbordado = null;
            return valor;
        }
        boolean existe = actual.buscarLlave(valor, pos);
        if (existe) {
            System.out.println("Elemento duplicado\n");
            subir = false;
            return null;
        }
        mediana = empujar(actual.hijos.get(pos[0]), valor);
        if (subir) {
            if (actual.estaLleno(this.orden - 1)) {
                mediana = dividirNodo(actual, mediana, pos[0]);
            } else {
                insertarEnNodo(actual, mediana, nodoDesbordado, pos[0]);
                subir = false;
            }
        }
        return mediana;
    }

    private void insertarEnNodo(NodoB<E> actual, E valor, NodoB<E> derecho, int pos) {
        for (int i = actual.cantidad - 1; i >= pos; i--) {
            actual.llaves.set(i + 1, actual.llaves.get(i));
            actual.hijos.set(i + 2, actual.hijos.get(i + 1));
        }
        actual.llaves.set(pos, valor);
        actual.hijos.set(pos + 1, derecho);
        actual.cantidad++;
    }

    private E dividirNodo(NodoB<E> actual, E valor, int pos) {
        NodoB<E> derecho = nodoDesbordado;
        int posMediana = (pos <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;
        nodoDesbordado = new NodoB<E>(this.orden);

        for (int i = posMediana; i < this.orden - 1; i++) {
            nodoDesbordado.llaves.set(i - posMediana, actual.llaves.get(i));
            nodoDesbordado.hijos.set(i - posMediana + 1, actual.hijos.get(i + 1));
        }
        nodoDesbordado.cantidad = (this.orden - 1) - posMediana;
        actual.cantidad = posMediana;

        if (pos <= this.orden / 2) {
            insertarEnNodo(actual, valor, derecho, pos);
        } else {
            insertarEnNodo(nodoDesbordado, valor, derecho, pos - posMediana);
        }

        E mediana = actual.llaves.get(actual.cantidad - 1);
        nodoDesbordado.hijos.set(0, actual.hijos.get(actual.cantidad));
        actual.cantidad--;
        return mediana;
    }

    @Override
    public String toString() {
        if (estaVacio()) return "Árbol B vacío";
        return escribirArbol(this.raiz);
    }

    private String escribirArbol(NodoB<E> actual) {
        String s = "| ";
        for (int i = 0; i < actual.cantidad; i++) {
            if (actual.hijos.get(i) != null)
                s += escribirArbol(actual.hijos.get(i));
            s += actual.llaves.get(i).toString() + " ";
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
        while (i < nodo.cantidad && clave.compareTo(nodo.llaves.get(i)) > 0) i++;
        if (i < nodo.cantidad && clave.compareTo(nodo.llaves.get(i)) == 0)
            return true;
        return buscarRecursivo(nodo.hijos.get(i), clave);
    }

    public E minimo() {
        if (estaVacio()) return null;
        NodoB<E> actual = this.raiz;
        while (actual.hijos.get(0) != null) {
            actual = actual.hijos.get(0);
        }
        return actual.llaves.get(0);
    }

    public E maximo() {
        if (estaVacio()) return null;
        NodoB<E> actual = this.raiz;
        while (actual.hijos.get(actual.cantidad) != null) {
            actual = actual.hijos.get(actual.cantidad);
        }
        return actual.llaves.get(actual.cantidad - 1);
    }

    public E predecesor(E clave) {
        return predecesorRecursivo(this.raiz, clave, null);
    }

    private E predecesorRecursivo(NodoB<E> nodo, E clave, E ultimoIzquierda) {
        if (nodo == null) return ultimoIzquierda;
        int i = 0;
        while (i < nodo.cantidad && clave.compareTo(nodo.llaves.get(i)) > 0) {
            ultimoIzquierda = nodo.llaves.get(i);
            i++;
        }
        if (i < nodo.cantidad && clave.compareTo(nodo.llaves.get(i)) == 0) {
            if (nodo.hijos.get(i) != null) {
                NodoB<E> tmp = nodo.hijos.get(i);
                while (tmp.hijos.get(tmp.cantidad) != null) {
                    tmp = tmp.hijos.get(tmp.cantidad);
                }
                return tmp.llaves.get(tmp.cantidad - 1);
            }
            return ultimoIzquierda;
        }
        return predecesorRecursivo(nodo.hijos.get(i), clave, ultimoIzquierda);
    }

    public E sucesor(E clave) {
        return sucesorRecursivo(this.raiz, clave, null);
    }

    private E sucesorRecursivo(NodoB<E> nodo, E clave, E ultimoDerecha) {
        if (nodo == null) return ultimoDerecha;
        int i = 0;
        while (i < nodo.cantidad && clave.compareTo(nodo.llaves.get(i)) > 0) {
            i++;
        }
        if (i < nodo.cantidad && clave.compareTo(nodo.llaves.get(i)) == 0) {
            if (nodo.hijos.get(i + 1) != null) {
                NodoB<E> tmp = nodo.hijos.get(i + 1);
                while (tmp.hijos.get(0) != null) {
                    tmp = tmp.hijos.get(0);
                }
                return tmp.llaves.get(0);
            }
            return ultimoDerecha;
        }
        if (i < nodo.cantidad) ultimoDerecha = nodo.llaves.get(i);
        return sucesorRecursivo(nodo.hijos.get(i), clave, ultimoDerecha);
    }

    public void eliminar(E valor) {
        eliminarDesde(this.raiz, valor);
        if (this.raiz != null && this.raiz.cantidad == 0) {
            this.raiz = this.raiz.hijos.get(0);
        }
    }

    private void eliminarDesde(NodoB<E> actual, E valor) {
        int i = 0;
        while (i < actual.cantidad && valor.compareTo(actual.llaves.get(i)) > 0) {
            i++;
        }
        if (i < actual.cantidad && valor.compareTo(actual.llaves.get(i)) == 0) {
            if (actual.hijos.get(i) == null) {
                for (int j = i; j < actual.cantidad - 1; j++) {
                    actual.llaves.set(j, actual.llaves.get(j + 1));
                }
                actual.cantidad--;
            } else {
                NodoB<E> aux = actual.hijos.get(i + 1);
                while (aux.hijos.get(0) != null)
                    aux = aux.hijos.get(0);
                E sucesor = aux.llaves.get(0);
                actual.llaves.set(i, sucesor);
                eliminarDesde(actual.hijos.get(i + 1), sucesor);
            }
        } else {
            if (actual.hijos.get(i) == null) {
                System.out.println("El valor no existe en el árbol.");
                return;
            }
            if (actual.hijos.get(i).cantidad < (orden - 1) / 2 + 1) {
                NodoB<E> izq = (i > 0) ? actual.hijos.get(i - 1) : null;
                NodoB<E> der = (i < actual.cantidad) ? actual.hijos.get(i + 1) : null;
                if (izq != null && izq.cantidad > (orden - 1) / 2) {
                    NodoB<E> hijo = actual.hijos.get(i);
                    for (int j = hijo.cantidad - 1; j >= 0; j--) {
                        hijo.llaves.set(j + 1, hijo.llaves.get(j));
                    }
                    for (int j = hijo.cantidad; j >= 0; j--) {
                        hijo.hijos.set(j + 1, hijo.hijos.get(j));
                    }
                    hijo.llaves.set(0, actual.llaves.get(i - 1));
                    hijo.hijos.set(0, izq.hijos.get(izq.cantidad));
                    hijo.cantidad++;
                    actual.llaves.set(i - 1, izq.llaves.get(izq.cantidad - 1));
                    izq.cantidad--;
                } else if (der != null && der.cantidad > (orden - 1) / 2) {
                    NodoB<E> hijo = actual.hijos.get(i);
                    hijo.llaves.set(hijo.cantidad, actual.llaves.get(i));
                    hijo.hijos.set(hijo.cantidad + 1, der.hijos.get(0));
                    hijo.cantidad++;
                    actual.llaves.set(i, der.llaves.get(0));
                    for (int j = 0; j < der.cantidad - 1; j++) {
                        der.llaves.set(j, der.llaves.get(j + 1));
                    }
                    for (int j = 0; j < der.cantidad; j++) {
                        der.hijos.set(j, der.hijos.get(j + 1));
                    }
                    der.cantidad--;
                } else {
                    if (izq != null) {
                        fusionarNodos(izq, actual.hijos.get(i), actual, i - 1);
                        eliminarDesde(izq, valor);
                    } else if (der != null) {
                        fusionarNodos(actual.hijos.get(i), der, actual, i);
                        eliminarDesde(actual.hijos.get(i), valor);
                    }
                    return;
                }
            }
            eliminarDesde(actual.hijos.get(i), valor);
        }
    }

    private void fusionarNodos(NodoB<E> izq, NodoB<E> der, NodoB<E> padre, int indice) {
        izq.llaves.set(izq.cantidad, padre.llaves.get(indice));
        izq.cantidad++;
        for (int i = 0; i < der.cantidad; i++) {
            izq.llaves.set(izq.cantidad, der.llaves.get(i));
            izq.hijos.set(izq.cantidad, der.hijos.get(i));
            izq.cantidad++;
        }
        izq.hijos.set(izq.cantidad, der.hijos.get(der.cantidad));
        for (int i = indice; i < padre.cantidad - 1; i++) {
            padre.llaves.set(i, padre.llaves.get(i + 1));
            padre.hijos.set(i + 1, padre.hijos.get(i + 2));
        }
        padre.cantidad--;
    }
}

