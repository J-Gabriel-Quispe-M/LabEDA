package ejercicio1;

public class HashClosed<E> {
    private Register<E>[] tabla;
    private boolean[] marcadoresEliminados;
    private int tamanio;
    private final double UMBRAL_FACTOR_CARGA = 0.7;

    @SuppressWarnings("unchecked")
    public HashClosed(int capacidadInicial) {
        tabla = new Register[capacidadInicial];
        marcadoresEliminados = new boolean[capacidadInicial];
        tamanio = 0;
    }

    private int calcularHash(int clave) {
        return Math.abs(clave) % tabla.length;
    }

    private int explorar(int clave, boolean buscarEspacioLibre) {
        int indice = calcularHash(clave);
        int inicio = indice;
        do {
            Register<E> registro = tabla[indice];
            if (registro != null && registro.obtenerClave() == clave && !marcadoresEliminados[indice]) {
                System.out.println("La clave se encontro en la posicion " + indice);
                return indice;
            }
            if (registro == null && !buscarEspacioLibre) {
                System.out.println("No se encontro posicion disponible, tabla llena");
                return -1;
            }
            if (buscarEspacioLibre && (registro == null || marcadoresEliminados[indice])) {
                System.out.println("Hay espacio disponible en la posicion " + indice);
                return indice;
            }
            if (registro != null && registro.obtenerClave() != clave && !marcadoresEliminados[indice]) {
                System.out.println("Colision en posicion" + indice + " con clave " + registro.obtenerClave() +
                        ", se recorre a posicion " + ((indice + 1) % tabla.length));
            }
            indice = (indice + 1) % tabla.length;
        } 
        while (indice != inicio);
        System.out.println("No se encontro posición disponible, la tabla esta llena");
        return -1;
    }

    public void insertar(Register<E> registro) {
        if (registro == null) {
            System.out.println("El registro no puede ser nulo");
            return;
        }
        System.out.println("\nInsertando clave: " + registro.obtenerClave());
        if (buscar(registro.obtenerClave()) != null) {
            System.out.println("Error: LA CLAVE " + registro.obtenerClave() + " SE REPITE");
            return;
        }
        if ((double) tamanio / tabla.length > UMBRAL_FACTOR_CARGA) {
            redimensionar();
        }
        int indice = explorar(registro.obtenerClave(), true);
        if (indice == -1) {
            System.out.println("Tabla LLENA");
            return;
        }
        if (tabla[indice] != null && marcadoresEliminados[indice]) {
            marcadoresEliminados[indice] = false;
            tamanio++;
        } 
        else if (tabla[indice] == null) {
            tamanio++;
        }
        tabla[indice] = registro;
        System.out.println("Insertado en la posicion " + indice);
    }

    public Register<E> buscar(int clave) {
        System.out.println("\nBuscando la clave: " + clave);
        int indice = explorar(clave, false);
        if (indice != -1) {
            System.out.println("Elemento encontrado: " + tabla[indice]);
            return tabla[indice];
        } 
        else {
            System.out.println("Clave no encontrada");
            return null;
        }
    }

    public void eliminar(int clave) {
        System.out.println("\nEliminando clave: " + clave);
        int indice = explorar(clave, false);
        if (indice != -1) {
            marcadoresEliminados[indice] = true;
            tamanio--;
            System.out.println("Eliminado logicamente en posición " + indice);
        } 
        else {
            System.out.println("Clave no encontrada para eliminar");
        }
    }

    @SuppressWarnings("unchecked")
    private void redimensionar() {
        System.out.println("\nFactor de carga excedido. Redimensionando");
        Register<E>[] tablaAnterior = tabla;
        tabla = new Register[tablaAnterior.length * 2];
        marcadoresEliminados = new boolean[tabla.length];
        tamanio = 0;
        for (Register<E> registro : tablaAnterior) {
            if (registro != null) {
                insertar(registro);
            }
        }
    }

    public void mostrar() {
        System.out.println("\n--- Tabla Hash Cerrada ---");
        System.out.println("Capacidad: " + tabla.length);
        System.out.println("Elementos: " + tamanio);
        for (int i = 0; i < tabla.length; i++) {
            System.out.printf("[%2d] ", i);
            if (tabla[i] == null) {
                System.out.println("VACIO");
            } 
            else if (marcadoresEliminados[i]) {
                System.out.println("ELIMINADO");
            } 
            else {
                System.out.println(tabla[i]);
            }
        }
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }

    public int obtenerTamanio() {
        return tamanio;
    }
}