import org.graphstream.graph.implementations.MultiGraph;

public class VisualizadorArbolBMas<E extends Comparable<E>> extends ArbolBMas<E> {
    private MultiGraph grafo;
    private int contadorId;

    public VisualizadorArbolBMas(int orden) {
        super(orden);
        System.setProperty("org.graphstream.ui", "swing");
        grafo = new MultiGraph("Árbol B+");
        grafo.setAttribute("ui.stylesheet", """
            node {
                shape: box;
                size-mode: fit;
                padding: 6px;
                fill-color:rgb(2, 10, 17);
                stroke-mode: plain;
                stroke-color: black;
                text-alignment: center;
                text-size: 14px;
            }
            edge {
                arrow-shape: none;
                fill-color: gray;
                size: 2px;
            }
        """);
        grafo.setAutoCreate(true);
        grafo.setStrict(false);
        contadorId = 0;
    }

    public void mostrarArbol() {
        grafo.clear();
        contadorId = 0;
        if (!estaVacio()) {
            construirGrafo(this.raiz, null, 0);
        }
        System.setProperty("org.graphstream.ui", "swing");
        grafo.display();
    }

    private String construirGrafo(NodoBMas<E> nodo, String idPadre, int nivel) {
        String idNodo = "N" + (contadorId++);
        StringBuilder etiqueta = new StringBuilder();
        for (int i = 0; i < nodo.cantidad; i++) {
            etiqueta.append(nodo.claves.get(i));
            if (i < nodo.cantidad - 1) etiqueta.append(" | ");
        }

        grafo.addNode(idNodo);
        grafo.getNode(idNodo).setAttribute("ui.label", etiqueta.toString());
        grafo.getNode(idNodo).setAttribute("ui.style", "shape: box; fill-color: #A2D2FF; size-mode: fit; padding: 8px; text-size: 16px; stroke-mode: plain; stroke-color: black;");

        if (idPadre != null) {
            grafo.addEdge(idPadre + "-" + idNodo, idPadre, idNodo, true);
        }

        if (!nodo.esHoja) {
            for (int i = 0; i <= nodo.cantidad; i++) {
                if (nodo.hijos.get(i) != null) {
                    construirGrafo(nodo.hijos.get(i), idNodo, nivel + 1);
                }
            }
        }

        return idNodo;
    }
}
