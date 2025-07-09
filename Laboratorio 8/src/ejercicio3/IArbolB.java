package ejercicio3;

public interface IArbolB<E extends Comparable<E>> {
    void insertar(E x);            
    void eliminar(E x);            
    boolean buscar(E x);           
    boolean estaVacio();
    void destruir();               

    E minimo();                    
    E maximo();                    
    E predecesor(E x);             
    E sucesor(E x);                

    String toString();
}

