package ejercicio3;

public class HashCerrado<E> {
    private Registro<E>[] tabla;
    private boolean[] marcadoresEliminados;
    private int tamanio;
    private final double UMBRAL_FACTOR_CARGA = 0.7;

    @SuppressWarnings("unchecked")
    public HashCerrado(int capacidadInicial) {
        tabla = new Registro[capacidadInicial];
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
            Registro<E> registro = tabla[indice];
            if (registro != null && registro.obtenerClave() == clave && !marcadoresEliminados[indice]) {
                System.out.println("Clave encontrada en la posición " + indice);
                return indice;
            }
            if (registro == null && !buscarEspacioLibre) {
                System.out.println("No se encontró una posición disponible, tabla llena");
                return -1;
            }
            if (buscarEspacioLibre && (registro == null || marcadoresEliminados[indice])) {
                System.out.println("Espacio disponible en la posición " + indice);
                return indice;
            }
            if (registro != null && registro.obtenerClave() != clave && !marcadoresEliminados[indice]) {
                System.out.println("Colisión en la posición " + indice + " con clave " + registro.obtenerClave() +
                        ", se avanza a la posición " + ((indice + 1) % tabla.length));
            }
            indice = (indice + 1) % tabla.length;
        } 
        while (indice != inicio);
        System.out.println("No se encontró una posición disponible, tabla llena");
        return -1;
    }

    public void insertar(Registro<E> registro) {
        if (registro == null) {
            System.out.println("El registro no puede ser nulo");
            return;
        }
        System.out.println("\nInsertando clave: " + registro.obtenerClave());
        if (buscar(registro.obtenerClave()) != null) {
            System.out.println("Error: LA CLAVE " + registro.obtenerClave() + " YA EXISTE");
            return;
        }
        if ((double) tamanio / tabla.length > UMBRAL_FACTOR_CARGA) {
            redimensionar();
        }
        int indice = explorar(registro.obtenerClave(), true);
        if (indice == -1) {
            System.out.println("La tabla está LLENA");
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
        System.out.println("Insertado en la posición " + indice);
    }

    public Registro<E> buscar(int clave) {
        System.out.println("\nBuscando clave: " + clave);
        int indice = explorar(clave, false);
        if (indice != -1) {
            System.out.println("Elemento encontrado: " + tabla[indice]);
            return tabla[indice];
        } else {
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
            System.out.println("Eliminado lógicamente en la posición " + indice);
        } 
        else {
            System.out.println("Clave no encontrada para eliminar");
        }
    }

    @SuppressWarnings("unchecked")
    private void redimensionar() {
        System.out.println("\nFactor de carga excedido. Redimensionando tabla...");
        Registro<E>[] tablaAnterior = tabla;
        tabla = new Registro[tablaAnterior.length * 2];
        marcadoresEliminados = new boolean[tabla.length];
        tamanio = 0;
        for (Registro<E> registro : tablaAnterior) {
            if (registro != null) {
                insertar(registro);
            }
        }
    }

    public void mostrar() {
        System.out.println("\n--- Estado de la Tabla Hash Cerrada ---");
        System.out.println("Capacidad total: " + tabla.length);
        System.out.println("Elementos actuales: " + tamanio);
        for (int i = 0; i < tabla.length; i++) {
            System.out.printf("[%2d] ", i);
            if (tabla[i] == null) {
                System.out.println("VACÍO");
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
