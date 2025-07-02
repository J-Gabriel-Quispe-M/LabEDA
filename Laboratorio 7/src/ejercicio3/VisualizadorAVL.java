package ejercicio3;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

public class VisualizadorAVL<T extends Comparable<T>> {

    private Graph grafo;

    public VisualizadorAVL() {
        grafo = new SingleGraph("Árbol AVL");
        grafo.setAttribute("ui.stylesheet", "node { fill-color: lightblue; size: 25px; text-size: 20px; }");
    }

    public void graficar(ArbolAVL<T> arbol) {
        grafo.clear();
        if (!arbol.estaVacio()) {
            recorrerYAgregar(arbol.obtenerRaiz(), null, "raiz");
        }

        Viewer viewer = grafo.display();
        viewer.enableAutoLayout();  // Layout automático básico sin dependencias adicionales
    }

    private void recorrerYAgregar(NodoAVL<T> nodo, String padreId, String id) {
        if (nodo == null) return;

        if (grafo.getNode(id) == null) {
            grafo.addNode(id).setAttribute("ui.label", nodo.dato.toString());
        }

        if (padreId != null && grafo.getEdge(padreId + "-" + id) == null) {
            grafo.addEdge(padreId + "-" + id, padreId, id);
        }

        if (nodo.izquierdo != null) {
            recorrerYAgregar(nodo.izquierdo, id, id + "I");
        }
        if (nodo.derecho != null) {
            recorrerYAgregar(nodo.derecho, id, id + "D");
        }
    }
}
