import java.util.ArrayList;
import java.util.List;

/**
 * Árbol Binario de Búsqueda (BST) de enteros con ELIMINACIÓN de nodos.
 *
 * Propiedad BST (convención adoptada):
 *   - Los valores MENORES al del nodo van en el subárbol izquierdo.
 *   - Los valores MAYORES O IGUALES van en el subárbol derecho.
 *
 * La eliminación tiene tres casos:
 *   1) Nodo HOJA        -> se elimina directamente (el padre apunta a null).
 *   2) Nodo con UN HIJO -> el padre se enlaza con el único hijo del nodo eliminado.
 *   3) Nodo con DOS HIJOS -> se reemplaza su valor por el SUCESOR INORDEN
 *      (el mínimo del subárbol derecho) y luego se elimina ese duplicado.
 */
public class ArbolBST {

    /** Raíz del árbol (null si está vacío). */
    private NodoArbol<Integer> raiz;

    // ------------------------------------------------------------------
    // INSERCIÓN Y BÚSQUEDA (heredadas de la Guía 4)
    // ------------------------------------------------------------------

    /**
     * Inserta un valor manteniendo la propiedad BST.
     *
     * @param valor valor a insertar
     */
    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    /** Inserción recursiva: crea el nodo al llegar a una posición vacía. */
    private NodoArbol<Integer> insertarRec(NodoArbol<Integer> nodo, int valor) {
        if (nodo == null) return new NodoArbol<>(valor);

        if (valor < nodo.getDato()) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), valor));
        } else {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), valor));
        }
        return nodo;
    }

    /**
     * Busca un valor en el árbol.
     *
     * @param valor valor a buscar
     * @return true si existe, false si no
     */
    public boolean buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    /** Búsqueda recursiva por el único camino que permite la propiedad BST. */
    private boolean buscarRec(NodoArbol<Integer> nodo, int valor) {
        if (nodo == null) return false;
        if (valor == nodo.getDato()) return true;
        return valor < nodo.getDato()
                ? buscarRec(nodo.getIzquierdo(), valor)
                : buscarRec(nodo.getDerecho(), valor);
    }

    // ------------------------------------------------------------------
    // ELIMINACIÓN (Guía 5)
    // ------------------------------------------------------------------

    /**
     * Elimina un valor del árbol sin romper la propiedad BST.
     *
     * @param valor valor a eliminar
     * @return true si el valor existía y fue eliminado, false si no estaba
     */
    public boolean eliminar(int valor) {
        if (!buscar(valor)) return false;      // nada que eliminar
        raiz = eliminarRec(raiz, valor);       // la raíz puede cambiar
        return true;
    }

    /**
     * Eliminación recursiva. Devuelve la nueva raíz del subárbol, de modo
     * que el padre pueda REASIGNAR su referencia (ese es el "reto principal").
     *
     * @param nodo  nodo actual de la recursión
     * @param valor valor a eliminar
     * @return raíz del subárbol ya actualizada
     */
    private NodoArbol<Integer> eliminarRec(NodoArbol<Integer> nodo, int valor) {
        if (nodo == null) return null;         // valor no encontrado

        if (valor < nodo.getDato()) {
            // Buscar a la izquierda y reasignar la referencia del hijo izquierdo
            nodo.setIzquierdo(eliminarRec(nodo.getIzquierdo(), valor));

        } else if (valor > nodo.getDato()) {
            // Buscar a la derecha y reasignar la referencia del hijo derecho
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), valor));

        } else {
            // ---- Nodo encontrado: se aplican los tres casos ----

            // CASO 1 (hoja) y CASO 2 (un hijo, el derecho):
            // si no hay hijo izquierdo se devuelve el derecho (o null si es hoja).
            if (nodo.getIzquierdo() == null) return nodo.getDerecho();

            // CASO 2 (un hijo, el izquierdo): se devuelve el hijo izquierdo.
            if (nodo.getDerecho() == null) return nodo.getIzquierdo();

            // CASO 3 (dos hijos):
            // a) buscar el sucesor inorden = mínimo del subárbol derecho
            int sucesor = minValor(nodo.getDerecho());
            // b) copiar el valor del sucesor en este nodo
            nodo.setDato(sucesor);
            // c) eliminar el duplicado del subárbol derecho
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), sucesor));
        }
        return nodo;
    }

    /**
     * Encuentra el valor mínimo de un subárbol: se baja siempre por la
     * izquierda hasta el último nodo. Sirve para obtener el sucesor inorden.
     *
     * @param nodo raíz del subárbol (no debe ser null)
     * @return el menor valor del subárbol
     */
    private int minValor(NodoArbol<Integer> nodo) {
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo.getDato();
    }

    // ------------------------------------------------------------------
    // VALIDACIÓN Y VISUALIZACIÓN (para las pruebas y el Resultado.txt)
    // ------------------------------------------------------------------

    /**
     * Recorrido inorden (izquierda, raíz, derecha). En un BST válido
     * siempre produce los valores en orden ascendente.
     *
     * @return lista de valores en inorden
     */
    public List<Integer> inorden() {
        List<Integer> resultado = new ArrayList<>();
        inordenRec(raiz, resultado);
        return resultado;
    }

    private void inordenRec(NodoArbol<Integer> nodo, List<Integer> lista) {
        if (nodo == null) return;
        inordenRec(nodo.getIzquierdo(), lista);
        lista.add(nodo.getDato());
        inordenRec(nodo.getDerecho(), lista);
    }

    /**
     * Valida que el árbol siga ordenado: el inorden debe ser no decreciente.
     *
     * @return true si cumple la propiedad BST
     */
    public boolean esBSTValido() {
        List<Integer> lista = inorden();
        for (int i = 1; i < lista.size(); i++) {
            if (lista.get(i) < lista.get(i - 1)) return false;
        }
        return true;
    }

    /**
     * Calcula la altura como número de NIVELES (vacío = 0, solo raíz = 1).
     *
     * @return altura del árbol
     */
    public int altura() {
        return alturaRec(raiz);
    }

    private int alturaRec(NodoArbol<Integer> nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(alturaRec(nodo.getIzquierdo()),
                            alturaRec(nodo.getDerecho()));
    }

    /**
     * Describe la estructura del árbol en preorden: una línea por nodo con
     * sus hijos izquierdo y derecho. Sirve para comparar ANTES y DESPUES.
     *
     * @return texto con la estructura
     */
    public String estructura() {
        List<String> lineas = new ArrayList<>();
        estructuraRec(raiz, lineas);
        return String.join("\n", lineas);
    }

    private void estructuraRec(NodoArbol<Integer> nodo, List<String> lineas) {
        if (nodo == null) return;
        String izq = nodo.getIzquierdo() == null ? "null" : String.valueOf(nodo.getIzquierdo().getDato());
        String der = nodo.getDerecho() == null ? "null" : String.valueOf(nodo.getDerecho().getDato());
        lineas.add("  " + nodo.getDato() + " -> izq: " + izq + ", der: " + der);
        estructuraRec(nodo.getIzquierdo(), lineas);
        estructuraRec(nodo.getDerecho(), lineas);
    }
}
