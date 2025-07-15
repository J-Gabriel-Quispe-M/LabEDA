package ejercicio4;

import java.util.*;

public class HashAbierto<E> {
    private LinkedList<Registro<E>>[] tabla;
    private int tamanio;
    private final double UMBRAL_FACTOR_CARGA = 0.7;

    @SuppressWarnings("unchecked")
    public HashAbierto(int capacidad) {
        tabla = new LinkedList[capacidad];
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new LinkedList<>();
        }
        tamanio = 0;
    }

    private int calcularHash(int clave) {
        return Math.abs(clave) % tabla.length;
    }

    public void insertar(Registro<E> registro) {
        if (registro == null) {
            System.out.println("El registro es nulo, no se puede insertar.");
            return;
        }
        int indice = calcularHash(registro.obtenerClave());
        System.out.println("\nInsertando clave: " + registro.obtenerClave() + " en la posición " + indice);
        if (!tabla[indice].isEmpty()) {
            System.out.println("Colisión detectada: la posición " + indice + 
            " ya contiene elementos. Se insertará al final de la lista.");
        }
        for (Registro<E> r : tabla[indice]) {
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
        System.out.println("Registro insertado en la posición " + indice + ": " + registro);
    }

    public Registro<E> buscar(int clave) {
        int indice = calcularHash(clave);
        System.out.println("\nBuscando clave: " + clave + " en la posición " + indice);
        if (tabla[indice].isEmpty()) {
            System.out.println("La posición " + indice + " está vacía. Clave no encontrada.");
            return null;
        }
        int paso = 0;
        for (Registro<E> r : tabla[indice]) {
            paso++;
            if (r.obtenerClave() == clave && !r.estaEliminado()) {
                System.out.println("Elemento encontrado en la posición " + indice + ", paso " + paso + ": " + r);
                return r;
            }
        }
        System.out.println("Clave no encontrada en la posición " + indice);
        return null;
    }

    public void eliminar(int clave) {
        int indice = calcularHash(clave);
        System.out.println("\nEliminando clave: " + clave + " en la posición " + indice);

        for (Registro<E> r : tabla[indice]) {
            if (r.obtenerClave() == clave && !r.estaEliminado()) {
                r.eliminar();
                tamanio--;
                System.out.println("Clave eliminada lógicamente: " + clave);
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
                System.out.println("[VACÍO]");
            } 
            else {
                for (Registro<E> r : tabla[i]) {
                    System.out.print(r + " -> ");
                }
                System.out.println("null");
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void redimensionar() {
        System.out.println("\nEl factor de carga ha sido superado. Se duplica la capacidad de la tabla...");
        LinkedList<Registro<E>>[] tablaAnterior = tabla;
        tabla = new LinkedList[tablaAnterior.length * 2];
        for (int i = 0; i < tabla.length; i++) {
            tabla[i] = new LinkedList<>();
        }
        tamanio = 0;
        for (LinkedList<Registro<E>> cubeta : tablaAnterior) {
            for (Registro<E> registro : cubeta) {
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