package ejercicio1;

public class TestHashClosed {
    public static void main(String[] args) {
        HashClosed<String> tablaHash = new HashClosed<>(8);
        tablaHash.insertar(new Register<>(5, "Pedro"));
        tablaHash.insertar(new Register<>(13, "Jose"));  
        tablaHash.insertar(new Register<>(21, "Dariana"));
        tablaHash.insertar(new Register<>(6, "Ariana"));
        tablaHash.mostrar();
        tablaHash.buscar(21);
        tablaHash.buscar(99);
        tablaHash.eliminar(13);
        tablaHash.eliminar(100);
        tablaHash.mostrar();
        tablaHash.insertar(new Register<>(5, "Pedro->Duplicado"));
    }
}
