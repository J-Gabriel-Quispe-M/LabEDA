package ejercicio2;

import java.util.*;

public class HashOpened<E> {
    private LinkedList<Register<E>>[] tabla;
    private int tamanio;
    private final double UMBRAL_FACTOR_CARGA = 0.7;

    @SuppressWarnings("unchecked")
    public HashOpened(int capacidad) {
        tabla = new LinkedList[capacidad];
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new LinkedList<>();
        }
        tamanio = 0;
    }

    private int calcularHash(int clave) {
        return Math.abs(clave) % tabla.length;
    }

    public void insertar(Register<E> registro) {
        if (registro == null) {
            System.out.println("Registro nulo, no se puede insertar.");
            return;
        }
        int indice = calcularHash(registro.obtenerClave());
        System.out.println("\nInsertando clave: " + registro.obtenerClave() + " en posicion " + indice);
        if (!tabla[indice].isEmpty()) {
            System.out.println("Colision: posicion " + indice + " ya tiene elementos. Se insertara en la lista.");
        }
        for (Register<E> r : tabla[indice]) {
            if (r.obtenerClave() == registro.obtenerClave() && !r.estaEliminado()) {
                System.out.println("Error: LA CLAVE " + registro.obtenerClave() + " YA EXISTE.");
                return;
            }
        }
        if ((double) tamanio / tabla.length > UMBRAL_FACTOR_CARGA) {
            redimensionar();
            indice = calcularHash(registro.obtenerClave());
        }
        tabla[indice].add(registro);
        tamanio++;
        System.out.println("Insertado en posicion " + indice + ": " + registro);
    }

    public Register<E> buscar(int clave) {
        int indice = calcularHash(clave);
        System.out.println("\nBuscando clave: " + clave + " en posicion " + indice);
        if (tabla[indice].isEmpty()) {
            System.out.println("Posicion " + indice + " esta vacia. Clave no encontrada.");
            return null;
        }

        int paso = 0;
        for (Register<E> r : tabla[indice]) {
            paso++;
            if (r.obtenerClave() == clave && !r.estaEliminado()) {
                System.out.println("Elemento encontrado en posicion " + indice + ", paso " + paso + ": " + r);
                return r;
            }
        }
        System.out.println("Clave no encontrada en posicion " + indice);
        return null;
    }

    public void eliminar(int clave) {
        int indice = calcularHash(clave);
        System.out.println("\nEliminando clave: " + clave + " en posicion " + indice);

        for (Register<E> r : tabla[indice]) {
            if (r.obtenerClave() == clave && !r.estaEliminado()) {
                r.eliminar();
                tamanio--;
                System.out.println("Eliminado logicamente: " + clave);
                return;
            }
        }
        System.out.println("Clave no encontrada para eliminar: " + clave);
    }

    public void mostrarTabla() {
        System.out.println("\n--- Estado de la Tabla Hash Abierta ---");
        for (int i = 0; i < tabla.length; i++) {
            System.out.print("[" + i + "] ");
            if (tabla[i].isEmpty()) {
                System.out.println("[VACIO]");
            } else {
                for (Register<E> r : tabla[i]) {
                    System.out.print(r + " -> ");
                }
                System.out.println("null");
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void redimensionar() {
        System.out.println("\nFactor de carga excedido. Redimensionando tabla");
        LinkedList<Register<E>>[] tablaAnterior = tabla;
        tabla = new LinkedList[tablaAnterior.length * 2];
        for (int i = 0; i < tabla.length; i++) {
            tabla[i] = new LinkedList<>();
        }
        tamanio = 0;
        for (LinkedList<Register<E>> cubeta : tablaAnterior) {
            for (Register<E> registro : cubeta) {
                if (!registro.estaEliminado()) {
                    insertar(registro);
                }
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
