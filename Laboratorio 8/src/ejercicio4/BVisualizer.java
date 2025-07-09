import org.graphstream.graph.Graph;
import org.graphstream.graph.Node; 
import org.graphstream.graph.implementations.SingleGraph;

public class BVisualizer<E extends Comparable<E>> extends BTree<E> {
    private Graph graph;

    public BVisualizer(int orden) {
        super(orden);
    }

    public void displayTree() {
        graph = new SingleGraph("Árbol B");
        System.setProperty("org.graphstream.ui", "swing");

        graph.setAttribute("ui.stylesheet", """
            node {
                shape: box;
                size-mode: fit;
                padding: 5px;
                text-alignment: center;
                fill-color:rgb(2, 8, 14);
                stroke-mode: plain;
                stroke-color: black;
                text-size: 14;
            <<    shadow-mode: plain;
            }
            edge {
                fill-color:rgb(227, 236, 245);
                arrow-shape: none;
            }
        """);

        if (super.isEmpty()) {
            System.out.println("Árbol B vacío.");
            return;
        }

        buildGraph(super.root, null, 0);

        graph.display();
    }

    private int buildGraph(NodoB<E> node, String parentId, int nodeId) {
        if (node == null) return nodeId;

        String nodeKey = "N" + nodeId;
        String label = formatNodeLabel(node);
        Node graphNode = graph.addNode(nodeKey);
        graphNode.setAttribute("ui.label", label);

        if (parentId != null) {
            String edgeId = parentId + "-" + nodeKey;
            graph.addEdge(edgeId, parentId, nodeKey, true);
        }

        // Recorremos hijos
        for (int i = 0; i <= node.count; i++) {
            if (node.childs.get(i) != null) {
                nodeId++;
                nodeId = buildGraph(node.childs.get(i), nodeKey, nodeId);
            }
        }

        return nodeId;
    }

    private String formatNodeLabel(NodoB<E> node) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i < node.count; i++) {
            sb.append(node.keys.get(i));
            if (i < node.count - 1) sb.append(" | ");
        }
        sb.append(" ]");
        return sb.toString();
    }

    public void showTreeInfo() {
        System.out.println("Elementos en orden: " + super.toString());
        System.out.println("Mínimo: " + super.Min());
        System.out.println("Máximo: " + super.Max());
    }
}
