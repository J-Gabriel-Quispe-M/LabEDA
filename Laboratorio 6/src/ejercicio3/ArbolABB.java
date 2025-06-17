package ejercicio3;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.w3c.dom.Node;

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

    // Método para graficar el árbol con GraphStream
    public void graficar() {
    System.setProperty("org.graphstream.ui", "swing");

    SingleGraph grafo = new SingleGraph("Árbol ABB");

    if (raiz == null) {
        System.out.println("El árbol está vacío.");
        return;
    }

    agregarNodoYAristas(grafo, raiz, null);

    grafo.setAttribute("ui.stylesheet", "node {fill-color: lightblue; size: 30px; text-size: 18;}");
    
    for (Node nodo : grafo.getNodeSet()) {
        ((SingleGraph) nodo).setAttribute("ui.label", ((SingleGraph) nodo).getId());
    }

    grafo.display();
}


    private void agregarNodoYAristas(SingleGraph grafo, Nodo<T> nodo, String padreId) {
        if (nodo == null) return;

        String nodoId = nodo.dato.toString();

        if (grafo.getNode(nodoId) == null) {
            grafo.addNode(nodoId);
        }

        if (padreId != null) {
            String edgeId = padreId + "-" + nodoId;
            if (grafo.getEdge(edgeId) == null) {
                grafo.addEdge(edgeId, padreId, nodoId, true);
            }
        }

        agregarNodoYAristas(grafo, nodo.izquierdo, nodoId);
        agregarNodoYAristas(grafo, nodo.derecho, nodoId);
    }
}

