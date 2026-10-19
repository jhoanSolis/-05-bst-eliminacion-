/**
 * Guía 5. BST II: eliminación de nodos.
 *
 * Se construye el mismo BST para cada prueba y se elimina un nodo de cada tipo:
 *   Caso 1: nodo hoja            (20)
 *   Caso 2: nodo con un hijo     (100, su único hijo es 110)
 *   Caso 3: nodo con dos hijos   (80, la raíz)
 * Además: un nodo con dos hijos que no es la raíz (40) y un valor inexistente (999).
 *
 * Árbol original (insertado en este orden: 80, 40, 120, 20, 60, 100, 140, 110):
 *
 *            80
 *          /    \
 *        40      120
 *       /  \    /   \
 *     20   60  100  140
 *                 \
 *                 110
 */
public class Main {

    public static void main(String[] args) {
        int[] datos = {80, 40, 120, 20, 60, 100, 140, 110};

        System.out.println("##### ARBOL ORIGINAL #####");
        mostrar("Original", crearArbol(datos));
        System.out.println();

        probar("CASO 1: ELIMINAR NODO HOJA (20)", datos, 20);
        probar("CASO 2: ELIMINAR NODO CON UN HIJO (100)", datos, 100);
        probar("CASO 3: ELIMINAR NODO CON DOS HIJOS - LA RAIZ (80)", datos, 80);
        probar("EXTRA: NODO CON DOS HIJOS QUE NO ES RAIZ (40)", datos, 40);
        probar("EXTRA: ELIMINAR UN VALOR INEXISTENTE (999)", datos, 999);
    }

    /** Crea un BST nuevo insertando los datos en el orden dado. */
    static ArbolBST crearArbol(int[] datos) {
        ArbolBST arbol = new ArbolBST();
        for (int d : datos) arbol.insertar(d);
        return arbol;
    }

    /**
     * Prueba una eliminación: muestra el árbol ANTES, elimina el valor
     * y muestra el árbol DESPUES, validando que siga ordenado.
     */
    static void probar(String titulo, int[] datos, int valor) {
        ArbolBST arbol = crearArbol(datos);

        System.out.println("=== " + titulo + " ===");
        mostrar("ANTES", arbol);

        boolean eliminado = arbol.eliminar(valor);
        System.out.println("Eliminar " + valor + ": " + (eliminado ? "eliminado" : "no encontrado"));

        mostrar("DESPUES", arbol);
        System.out.println();
    }

    /** Imprime altura, inorden, validez BST y la estructura del árbol. */
    static void mostrar(String etiqueta, ArbolBST arbol) {
        System.out.println("[" + etiqueta + "] Altura: " + arbol.altura()
                + " | Inorden: " + arbol.inorden()
                + " | BST valido: " + (arbol.esBSTValido() ? "SI" : "NO"));
        System.out.println(arbol.estructura());
    }
}
