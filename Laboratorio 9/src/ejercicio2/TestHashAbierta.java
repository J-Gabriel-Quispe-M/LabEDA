package ejercicio2;

public class TestHashOpened {
    public static void main(String[] args) {
        HashOpened<String> tablaHash = new HashOpened<>(4);
        tablaHash.insertar(new Register<>(5, "Pedro"));
        tablaHash.insertar(new Register<>(9, "Jose"));
        tablaHash.insertar(new Register<>(13, "Dariana"));
        tablaHash.insertar(new Register<>(6, "Ariana"));
        tablaHash.mostrarTabla();
        tablaHash.buscar(13);
        tablaHash.buscar(99);
        tablaHash.eliminar(13);
        tablaHash.eliminar(100);
        tablaHash.mostrarTabla();
        tablaHash.insertar(new Register<>(5, "Pedro->Duplicado"));
    }
}
