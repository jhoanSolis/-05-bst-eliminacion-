/**
 * Nodo genérico de un árbol binario.
 * Cada nodo guarda un dato y referencias a sus hijos izquierdo y derecho.
 *
 * IMPORTANTE para la eliminación: el caso de dos hijos necesita
 * cambiar el dato de un nodo existente, por eso se incluye setDato().
 *
 * @param <T> tipo de dato almacenado en el nodo
 */
public class NodoArbol<T> {

    /** Valor almacenado en el nodo. */
    private T dato;

    /** Referencia al hijo izquierdo (null si no existe). */
    private NodoArbol<T> izquierdo;

    /** Referencia al hijo derecho (null si no existe). */
    private NodoArbol<T> derecho;

    /**
     * Crea un nodo hoja (sin hijos).
     *
     * @param dato valor a almacenar
     */
    public NodoArbol(T dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    /** @return el dato almacenado */
    public T getDato() { return dato; }

    /** @param dato nuevo valor del nodo (usado en el caso de dos hijos) */
    public void setDato(T dato) { this.dato = dato; }

    /** @return el hijo izquierdo o null */
    public NodoArbol<T> getIzquierdo() { return izquierdo; }

    /** @param izquierdo nuevo hijo izquierdo */
    public void setIzquierdo(NodoArbol<T> izquierdo) { this.izquierdo = izquierdo; }

    /** @return el hijo derecho o null */
    public NodoArbol<T> getDerecho() { return derecho; }

    /** @param derecho nuevo hijo derecho */
    public void setDerecho(NodoArbol<T> derecho) { this.derecho = derecho; }
}
