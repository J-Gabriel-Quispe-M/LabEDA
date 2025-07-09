import java.util.LinkedList;

public class ArbolBMas<E extends Comparable<E>> {
    protected NodoBMas<E> raiz;
    private int orden;
    private boolean subir;
    private NodoBMas<E> nodoDesbordado;

    public ArbolBMas(int orden) {
        this.orden = orden;
        this.raiz = new NodoBMas<>(orden, true); // iniciar como hoja vacía
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
        NodoBMas<E> nuevo;
        mediana = empujar(this.raiz, clave);
        if (subir) {
            nuevo = new NodoBMas<>(this.orden, false);
            nuevo.cantidad = 1;
            nuevo.claves.set(0, mediana);
            nuevo.hijos.set(0, this.raiz);
            nuevo.hijos.set(1, nodoDesbordado);
            this.raiz = nuevo;
        }
    }

    private E empujar(NodoBMas<E> actual, E clave) {
        int[] pos = new int[1];
        E mediana;
        if (actual == null) {
            subir = true;
            nodoDesbordado = null;
            return clave;
        }
        boolean existe = actual.buscarClave(clave, pos);
        if (existe && actual.esHoja) {
            System.out.println("Elemento duplicado\n");
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
            mediana = empujar(actual.hijos.get(pos[0]), clave);
            if (subir) {
                if (actual.estaLleno(this.orden - 1)) {
                    mediana = dividirInterno(actual, mediana, pos[0]);
                } else {
                    insertarEnInterno(actual, mediana, nodoDesbordado, pos[0]);
                    subir = false;
                }
            }
        }
        return mediana;
    }

    private void insertarEnHoja(NodoBMas<E> hoja, E clave, int k) {
        for (int i = hoja.cantidad - 1; i >= k; i--) {
            hoja.claves.set(i + 1, hoja.claves.get(i));
        }
        hoja.claves.set(k, clave);
        hoja.cantidad++;
    }

    private E dividirHoja(NodoBMas<E> hoja, E clave, int k) {
        int posMediana = (orden + 1) / 2;
        NodoBMas<E> nuevaHoja = new NodoBMas<>(orden, true);
        for (int i = posMediana, j = 0; i < orden - 1; i++, j++) {
            nuevaHoja.claves.set(j, hoja.claves.get(i));
            nuevaHoja.cantidad++;
        }
        hoja.cantidad = posMediana;
        if (k < posMediana) {
            insertarEnHoja(hoja, clave, k);
        } else {
            insertarEnHoja(nuevaHoja, clave, k - posMediana);
        }
        nuevaHoja.siguiente = hoja.siguiente;
        hoja.siguiente = nuevaHoja;
        subir = true;
        nodoDesbordado = nuevaHoja;
        return nuevaHoja.claves.get(0);
    }

    private E dividirInterno(NodoBMas<E> nodo, E clave, int k) {
        NodoBMas<E> derecho = nodoDesbordado;
        int posMediana = orden / 2;
        NodoBMas<E> nuevoInterno = new NodoBMas<>(orden, false);
        for (int i = posMediana + 1, j = 0; i < orden - 1; i++, j++) {
            nuevoInterno.claves.set(j, nodo.claves.get(i));
            nuevoInterno.hijos.set(j + 1, nodo.hijos.get(i + 1));
            nuevoInterno.cantidad++;
        }
        nuevoInterno.hijos.set(0, nodo.hijos.get(posMediana + 1));
        E mediana = nodo.claves.get(posMediana);
        nodo.cantidad = posMediana;
        if (k <= posMediana) {
            insertarEnInterno(nodo, clave, derecho, k);
        } else {
            insertarEnInterno(nuevoInterno, clave, derecho, k - posMediana - 1);
        }
        nodoDesbordado = nuevoInterno;
        subir = true;
        return mediana;
    }

    private void insertarEnInterno(NodoBMas<E> nodo, E clave, NodoBMas<E> derecho, int k) {
        for (int i = nodo.cantidad - 1; i >= k; i--) {
            nodo.claves.set(i + 1, nodo.claves.get(i));
            nodo.hijos.set(i + 2, nodo.hijos.get(i + 1));
        }
        nodo.claves.set(k, clave);
        nodo.hijos.set(k + 1, derecho);
        nodo.cantidad++;
    }

    public boolean buscar(E clave) {
        if (estaVacio()) return false;
        NodoBMas<E> nodo = raiz;
        while (!nodo.esHoja) {
            int i = 0;
            while (i < nodo.cantidad && clave.compareTo(nodo.claves.get(i)) > 0) {
                i++;
            }
            nodo = nodo.hijos.get(i);
        }
        for (int i = 0; i < nodo.cantidad; i++) {
            if (nodo.claves.get(i).compareTo(clave) == 0) {
                return true;
            }
        }
        return false;
    }

    public E minimo() {
        if (estaVacio()) return null;
        NodoBMas<E> nodo = raiz;
        while (!nodo.esHoja) {
            nodo = nodo.hijos.get(0);
        }
        return nodo.claves.get(0);
    }

    public E maximo() {
        if (estaVacio()) return null;
        NodoBMas<E> nodo = raiz;
        while (!nodo.esHoja) {
            nodo = nodo.hijos.get(nodo.cantidad);
        }
        return nodo.claves.get(nodo.cantidad - 1);
    }

    public String toString() {
        if (estaVacio()) return "El árbol B+ está vacío\n";
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
                sb.append(nodo.claves.get(i));
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
