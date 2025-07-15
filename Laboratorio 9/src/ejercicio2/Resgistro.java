package ejercicio2;

public class Register<E> implements Comparable<Register<E>> {
    private int clave;
    private E valor;
    private boolean eliminado;

    public Register(int clave, E valor) {
        this.clave = clave;
        this.valor = valor;
        this.eliminado = false;
    }

    public int obtenerClave() {
        return clave;
    }

    public E obtenerValor() {
        return valor;
    }

    public void establecerValor(E valor) {
        this.valor = valor;
    }

    public boolean estaEliminado() {
        return eliminado;
    }

    public void eliminar() {
        this.eliminado = true;
    }

    public int compareTo(Register<E> otro) {
        return Integer.compare(this.clave, otro.clave);
    }

    public String toString() {
        return (eliminado ? "[ELIMINADO] " : "") + clave + ":" + valor;
    }
}
