package ejercicio7;

public interface IArbolBMas<E extends Comparable<E>> {
    void insertar(E clave);           
    void eliminar(E clave);           
    boolean buscar(E clave);          
    E minimo();                       
    E maximo();                       
    E predecesor(E clave);            
    E sucesor(E clave);               
    boolean estaVacio();             
    void destruir();                 
    String toString();              
}

