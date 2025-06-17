package ejercicio1;

public class PruebaArbolABB {
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

        System.out.println("Buscar 40: " + arbol.buscar(40)); 
        System.out.println("Buscar 100: " + arbol.buscar(100)); 

        System.out.println("Mínimo: " + arbol.minimo()); 
        System.out.println("Máximo: " + arbol.maximo()); 

        System.out.println("Predecesor de 60: " + arbol.predecesor(60)); 
        System.out.println("Sucesor de 60: " + arbol.sucesor(60));       

        arbol.eliminar(20);
        arbol.eliminar(30);
        arbol.eliminar(50);
        arbol.recorridoInOrden();

        arbol.destruir();
        System.out.println("¿Árbol vacío?: " + arbol.estaVacio()); 
    }
}