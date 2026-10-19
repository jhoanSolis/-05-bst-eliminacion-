# Guía 5. BST II: eliminación de nodos

**Asignatura:** Estructura de Datos II – Ingeniería de Software / Sistemas – Semestre IV
**Ecosistema:** Java / GitHub

## Objetivo
Modificar un Árbol Binario de Búsqueda (BST) eliminando nodos sin romper su
propiedad de ordenación (menores a la izquierda, mayores o iguales a la derecha).

## Estructura de la carpeta
```
05-bst-eliminacion/
├── NodoArbol.java    # Nodo genérico (incluye setDato para el caso de dos hijos)
├── ArbolBST.java     # insertar, buscar, eliminar (3 casos), minValor, inorden, validación
├── Main.java         # Pruebas de los tres casos
├── Resultado.txt     # Salida de la ejecución
├── diagramas/        # Diagramas antes/después de cada caso
│   ├── 01-caso-hoja.mmd
│   ├── 02-caso-un-hijo.mmd
│   └── 03-caso-dos-hijos.mmd
└── README.md
```

## Cómo ejecutar
```bash
javac *.java
java Main
```

## Árbol de prueba
Insertado en este orden: `80, 40, 120, 20, 60, 100, 140, 110`

```
           80
         /    \
       40      120
      /  \    /   \
    20   60  100  140
               \
               110
```
Altura: 4 niveles. Inorden: `[20, 40, 60, 80, 100, 110, 120, 140]`.

## Los tres casos de eliminación

### Caso 1: nodo hoja (se elimina el 20)
El nodo no tiene hijos, así que simplemente desaparece.
- **Referencia que cambia:** `40.izquierdo` pasa de apuntar a 20 a `null`.
- En el código: `if (nodo.getIzquierdo() == null) return nodo.getDerecho();` devuelve `null`
  porque el nodo no tiene hijo derecho.

### Caso 2: nodo con un hijo (se elimina el 100)
El nodo tiene un único hijo (110). El padre se enlaza directamente con ese hijo.
- **Referencia que cambia:** `120.izquierdo` pasa de apuntar a 100 a apuntar a 110.
- En el código: se devuelve el único hijo del nodo, y el padre lo asigna con `setIzquierdo(...)`.

### Caso 3: nodo con dos hijos (se elimina la raíz 80)
No se puede quitar el nodo sin dejar dos subárboles sueltos. Se reemplaza su valor por el
**sucesor inorden**: el mínimo del subárbol derecho (bajar siempre por la izquierda desde 120).
El sucesor es **100**.
1. `minValor(120)` devuelve 100.
2. `nodo.setDato(100)`: la raíz ahora vale 100.
3. `nodo.setDerecho(eliminarRec(nodo.getDerecho(), 100))`: se elimina el duplicado, que
   era un nodo con un hijo (110).
- **Referencias que cambian:** el dato de la raíz (80 → 100) y `120.izquierdo` (100 → 110).
- El inorden sigue ordenado: `[20, 40, 60, 100, 110, 120, 140]`.

### Resumen

| Caso | Ejemplo | Qué cambia |
|---|---|---|
| Hoja | eliminar 20 | el padre (40) apunta a `null` |
| Un hijo | eliminar 100 | el padre (120) apunta al hijo (110) |
| Dos hijos | eliminar 80 | la raíz toma el valor del sucesor (100) y se elimina el duplicado |

## Pruebas de ejecución
La salida completa está en `Resultado.txt`. Resumen:

| Prueba | Altura antes → después | BST válido |
|---|---|---|
| Eliminar 20 (hoja) | 4 → 4 | SÍ |
| Eliminar 100 (un hijo) | 4 → 3 | SÍ |
| Eliminar 80 (dos hijos, raíz) | 4 → 3 | SÍ |
| Eliminar 40 (dos hijos, no raíz) | 4 → 4 | SÍ |
| Eliminar 999 (no existe) | 4 → 4 | SÍ |

## Diagramas antes/después
(agrega aquí los PNG exportados de Draw.io)
- `01-caso-hoja.png`
- `02-caso-un-hijo.png`
- `03-caso-dos-hijos.png`

## Conclusión
La eliminación es correcta cuando se reasignan bien las referencias: el método recursivo
devuelve la nueva raíz de cada subárbol y el padre la guarda. En el caso de dos hijos, usar el
sucesor inorden mantiene la propiedad BST porque es el menor de los valores mayores al eliminado.
