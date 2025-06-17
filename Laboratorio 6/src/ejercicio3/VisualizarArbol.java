package ejercicio3;

public class VisualizarArbol {
    public static void main(String[] args) {
        ArbolABB<Integer> arbol = new ArbolABB<>();
        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(70);
        arbol.insertar(60);
        arbol.insertar(80);
        arbol.recorridoInOrden();    
        arbol.recorridoPreOrden();   
        arbol.recorridoPostOrden();  
        arbol.graficar();
    }
}