package ejercicio7;

import java.util.LinkedList;

public class ArbolBMas<E extends Comparable<E>> {
    private NodoBMas<E> raiz;
    private int orden;
    private boolean subir;
    private NodoBMas<E> nuevoDesbordado;

    public ArbolBMas(int orden) {
        this.orden = orden;
        this.raiz = new NodoBMas<>(orden, true);
    }

    public ArbolBMas() {
        this.orden = 5; 
        this.raiz = new NodoBMas<>(orden, true);
    }

    public boolean estaVacio() {
        return this.raiz == null;
    }

    public void destruir() {
        this.raiz = null;
    }

    public void insertar(E clave) {
        subir = false;
        E mediana;
        NodoBMas<E> nuevoNodo;
        mediana = insertarRecursivo(this.raiz, clave);
        if (subir) {
            nuevoNodo = new NodoBMas<>(this.orden, false);
            nuevoNodo.cantidad = 1;
            nuevoNodo.llaves.set(0, mediana);
            nuevoNodo.hijos.set(0, this.raiz);
            nuevoNodo.hijos.set(1, nuevoDesbordado);
            this.raiz = nuevoNodo;
        }
    }

    private E insertarRecursivo(NodoBMas<E> actual, E clave) {
        int[] pos = new int[1];
        E mediana;

        if (actual == null) {
            subir = true;
            nuevoDesbordado = null;
            return clave;
        }

        boolean existe = actual.buscarLlave(clave, pos);
        if (existe && actual.esHoja) {
            System.out.println("Clave duplicada.\n");
            subir = false;
            return null;
        }

        if (actual.esHoja) {
            if (actual.estaLleno(this.orden - 1)) {
                mediana = dividirHoja(actual, clave, pos[0]);
            } else {
                insertarEnHoja(actual, clave, pos[0]);
                subir = false;
                return null;
            }
        } else {
            mediana = insertarRecursivo(actual.hijos.get(pos[0]), clave);
            if (subir) {
                if (actual.estaLleno(this.orden - 1)) {
                    mediana = dividirInterno(actual, mediana, pos[0]);
                } else {
                    insertarEnInterno(actual, mediana, nuevoDesbordado, pos[0]);
                    subir = false;
                }
            }
        }

        return mediana;
    }

    private void insertarEnHoja(NodoBMas<E> hoja, E clave, int posicion) {
        for (int i = hoja.cantidad - 1; i >= posicion; i--) {
            hoja.llaves.set(i + 1, hoja.llaves.get(i));
        }
        hoja.llaves.set(posicion, clave);
        hoja.cantidad++;
    }

    private E dividirHoja(NodoBMas<E> hoja, E clave, int posicion) {
        int posMediana = (orden + 1) / 2;
        NodoBMas<E> nuevaHoja = new NodoBMas<>(orden, true);

        for (int i = posMediana, j = 0; i < orden - 1; i++, j++) {
            nuevaHoja.llaves.set(j, hoja.llaves.get(i));
            nuevaHoja.cantidad++;
        }

        hoja.cantidad = posMediana;

        if (posicion < posMediana) {
            insertarEnHoja(hoja, clave, posicion);
        } else {
            insertarEnHoja(nuevaHoja, clave, posicion - posMediana);
        }

        nuevaHoja.siguiente = hoja.siguiente;
        hoja.siguiente = nuevaHoja;
        subir = true;
        nuevoDesbordado = nuevaHoja;
        return nuevaHoja.llaves.get(0);
    }

    private E dividirInterno(NodoBMas<E> actual, E clave, int posicion) {
        NodoBMas<E> derecho = nuevoDesbordado;
        int posMediana = orden / 2;
        NodoBMas<E> nuevoInterno = new NodoBMas<>(orden, false);

        for (int i = posMediana + 1, j = 0; i < orden - 1; i++, j++) {
            nuevoInterno.llaves.set(j, actual.llaves.get(i));
            nuevoInterno.hijos.set(j + 1, actual.hijos.get(i + 1));
            nuevoInterno.cantidad++;
        }

        nuevoInterno.hijos.set(0, actual.hijos.get(posMediana + 1));
        E mediana = actual.llaves.get(posMediana);
        actual.cantidad = posMediana;

        if (posicion <= posMediana) {
            insertarEnInterno(actual, clave, derecho, posicion);
        } else {
            insertarEnInterno(nuevoInterno, clave, derecho, posicion - posMediana - 1);
        }

        nuevoDesbordado = nuevoInterno;
        subir = true;
        return mediana;
    }

    private void insertarEnInterno(NodoBMas<E> actual, E clave, NodoBMas<E> derecho, int posicion) {
        for (int i = actual.cantidad - 1; i >= posicion; i--) {
            actual.llaves.set(i + 1, actual.llaves.get(i));
            actual.hijos.set(i + 2, actual.hijos.get(i + 1));
        }
        actual.llaves.set(posicion, clave);
        actual.hijos.set(posicion + 1, derecho);
        actual.cantidad++;
    }

    public boolean buscar(E clave) {
        if (estaVacio()) return false;
        NodoBMas<E> nodo = raiz;
        while (!nodo.esHoja) {
            int i = 0;
            while (i < nodo.cantidad && clave.compareTo(nodo.llaves.get(i)) > 0) i++;
            nodo = nodo.hijos.get(i);
        }
        for (int i = 0; i < nodo.cantidad; i++) {
            if (nodo.llaves.get(i).compareTo(clave) == 0) return true;
        }
        return false;
    }

    public E minimo() {
        if (estaVacio()) return null;
        NodoBMas<E> nodo = raiz;
        while (!nodo.esHoja) {
            nodo = nodo.hijos.get(0);
        }
        return nodo.llaves.get(0);
    }

    public E maximo() {
        if (estaVacio()) return null;
        NodoBMas<E> nodo = raiz;
        while (!nodo.esHoja) {
            nodo = nodo.hijos.get(nodo.cantidad);
        }
        return nodo.llaves.get(nodo.cantidad - 1);
    }

    public E predecesor(E clave) {
        if (estaVacio()) return null;
        NodoBMas<E> nodo = raiz;
        NodoBMas<E> anterior = null;

        while (!nodo.esHoja) {
            int i = 0;
            while (i < nodo.cantidad && clave.compareTo(nodo.llaves.get(i)) > 0) i++;
            nodo = nodo.hijos.get(i);
        }

        for (int i = nodo.cantidad - 1; i >= 0; i--) {
            if (clave.compareTo(nodo.llaves.get(i)) > 0)
                return nodo.llaves.get(i);
        }

        anterior = buscarHojaAnterior(raiz, nodo);
        if (anterior != null && anterior.cantidad > 0) {
            return anterior.llaves.get(anterior.cantidad - 1);
        }

        return null;
    }

    private NodoBMas<E> buscarHojaAnterior(NodoBMas<E> actual, NodoBMas<E> objetivo) {
        if (actual == null || actual.esHoja) return null;
        for (int i = 0; i <= actual.cantidad; i++) {
            NodoBMas<E> hijo = actual.hijos.get(i);
            if (hijo == objetivo) {
                if (i > 0) {
                    NodoBMas<E> anterior = actual.hijos.get(i - 1);
                    while (!anterior.esHoja) {
                        anterior = anterior.hijos.get(anterior.cantidad);
                    }
                    return anterior;
                }
            } else {
                NodoBMas<E> encontrado = buscarHojaAnterior(hijo, objetivo);
                if (encontrado != null) return encontrado;
            }
        }
        return null;
    }

    public E sucesor(E clave) {
        if (estaVacio()) return null;
        NodoBMas<E> nodo = raiz;
        while (!nodo.esHoja) {
            int i = 0;
            while (i < nodo.cantidad && clave.compareTo(nodo.llaves.get(i)) >= 0) i++;
            nodo = nodo.hijos.get(i);
        }

        for (int i = 0; i < nodo.cantidad; i++) {
            if (clave.compareTo(nodo.llaves.get(i)) < 0) return nodo.llaves.get(i);
        }

        if (nodo.siguiente != null && nodo.siguiente.cantidad > 0)
            return nodo.siguiente.llaves.get(0);

        return null;
    }

    public void eliminar(E clave) {
        if (estaVacio()) {
            System.out.println("Árbol vacío.");
            return;
        }
        eliminarRecursivo(raiz, clave);
        if (!raiz.esHoja && raiz.cantidad == 0) {
            raiz = raiz.hijos.get(0);
        }
    }

    private void eliminarRecursivo(NodoBMas<E> nodo, E clave) {
        int i = 0;
        while (i < nodo.cantidad && clave.compareTo(nodo.llaves.get(i)) > 0) i++;

        if (nodo.esHoja) {
            if (i < nodo.cantidad && clave.compareTo(nodo.llaves.get(i)) == 0) {
                for (int j = i; j < nodo.cantidad - 1; j++) {
                    nodo.llaves.set(j, nodo.llaves.get(j + 1));
                }
                nodo.llaves.set(nodo.cantidad - 1, null);
                nodo.cantidad--;
            } else {
                System.out.println("Clave no encontrada.");
            }
            return;
        }

        NodoBMas<E> hijo = nodo.hijos.get(i);
        eliminarRecursivo(hijo, clave);

        int minLlaves = (orden - 1) / 2;
        if (hijo.cantidad < minLlaves) {
            NodoBMas<E> izquierda = (i > 0) ? nodo.hijos.get(i - 1) : null;
            NodoBMas<E> derecha = (i < nodo.cantidad) ? nodo.hijos.get(i + 1) : null;

            if (izquierda != null && izquierda.cantidad > minLlaves) {
                for (int j = hijo.cantidad; j > 0; j--) {
                    hijo.llaves.set(j, hijo.llaves.get(j - 1));
                }
                hijo.llaves.set(0, nodo.llaves.get(i - 1));
                hijo.cantidad++;
                nodo.llaves.set(i - 1, izquierda.llaves.get(izquierda.cantidad - 1));
                izquierda.cantidad--;
            } else if (derecha != null && derecha.cantidad > minLlaves) {
                hijo.llaves.set(hijo.cantidad, nodo.llaves.get(i));
                hijo.cantidad++;
                nodo.llaves.set(i, derecha.llaves.get(0));
                for (int j = 0; j < derecha.cantidad - 1; j++) {
                    derecha.llaves.set(j, derecha.llaves.get(j + 1));
                }
                derecha.cantidad--;
            } else {
                if (izquierda != null) {
                    fusionarNodos(izquierda, hijo, nodo, i - 1);
                } else if (derecha != null) {
                    fusionarNodos(hijo, derecha, nodo, i);
                }
            }
        }
    }

    private void fusionarNodos(NodoBMas<E> izquierdo, NodoBMas<E> derecho, NodoBMas<E> padre, int indice) {
        for (int i = 0; i < derecho.cantidad; i++) {
            izquierdo.llaves.set(izquierdo.cantidad + i, derecho.llaves.get(i));
        }
        if (!izquierdo.esHoja) {
            for (int i = 0; i <= derecho.cantidad; i++) {
                izquierdo.hijos.set(izquierdo.cantidad + i, derecho.hijos.get(i));
            }
        } else {
            izquierdo.siguiente = derecho.siguiente;
        }
        izquierdo.cantidad += derecho.cantidad;
        for (int i = indice; i < padre.cantidad - 1; i++) {
            padre.llaves.set(i, padre.llaves.get(i + 1));
            padre.hijos.set(i + 1, padre.hijos.get(i + 2));
        }
        padre.llaves.set(padre.cantidad - 1, null);
        padre.hijos.set(padre.cantidad, null);
        padre.cantidad--;
    }

    public String toString() {
        if (estaVacio()) return "Árbol B+ vacío\n";
        StringBuilder sb = new StringBuilder();
        LinkedList<NodoBMas<E>> cola = new LinkedList<>();
        LinkedList<Integer> niveles = new LinkedList<>();
        cola.add(raiz);
        niveles.add(0);
        int nivelActual = -1;
        while (!cola.isEmpty()) {
            NodoBMas<E> nodo = cola.poll();
            int nivel = niveles.poll();
            if (nivel != nivelActual) {
                nivelActual = nivel;
                sb.append("\nNivel ").append(nivel).append(": ");
            }
            sb.append("[");
            for (int i = 0; i < nodo.cantidad; i++) {
                sb.append(nodo.llaves.get(i));
                if (i < nodo.cantidad - 1) sb.append(", ");
            }
            sb.append("] ");
            if (!nodo.esHoja) {
                for (int i = 0; i <= nodo.cantidad; i++) {
                    cola.add(nodo.hijos.get(i));
                    niveles.add(nivel + 1);
                }
            }
        }
        return sb.toString();
    }
}

