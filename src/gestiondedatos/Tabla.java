package gestiondedatos;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;

public class Tabla {

    private Map<Etiqueta, Columna> columnas;

    public Tabla() {

        this.columnas = new LinkedHashMap<>();
    }

    public void agregarColumna(Etiqueta etiquetaColumna, Columna columna) {
        // Verificacar que la columna no sea nula
        if (columna == null) {
            throw new IllegalArgumentException("La columna a agregar no puede ser nula.");
        }
        // Verificar si ya existe una columna con la misma etiqueta
        if (columnas.containsKey(etiquetaColumna)) {
            throw new IllegalArgumentException("Ya existe una columna con la etiqueta: " +
                    etiquetaColumna.getValor());
        }

        this.columnas.put(etiquetaColumna, columna);
    }

    public void agregarColumna(Columna columna) {
        // Reutiliza el metodo existente con validaciones
        // Genera los items 0,1, 2, 3, etc
        EtiquetaEntero nuevaEtiqueta = new EtiquetaEntero(this.getCantidadColumnas());
        agregarColumna(nuevaEtiqueta, columna);
    }

    public Columna getColumna(Etiqueta etiquetaColumna) {
        if (!columnas.containsKey(etiquetaColumna)) {
            throw new IllegalArgumentException("No existe una columna con la etiqueta: " +
                    etiquetaColumna.getValor());
        }
        return columnas.get(etiquetaColumna);
    }

    public boolean eliminarColumna(Etiqueta etiquetaColumna) {
        if (!columnas.containsKey(etiquetaColumna)) {
            // Lanzar una excepción si la columna no existe, no puede ser eliminada.
            throw new IllegalArgumentException("No se puede eliminar la columna. " +
                    "No existe una columna con la etiqueta: " + etiquetaColumna.getValor());
        }

        return columnas.remove(etiquetaColumna) != null;
        // remove devuelve el valor anterior o null si no existía
    }

    // Para obtener todas las etiquetas de las columnas:
    public Set<Etiqueta> getEtiquetasColumnas() {
        return columnas.keySet();
    }

    // Para obtener el número de columnas:
    public int getCantidadColumnas() {
        return columnas.size();
    }

    // Para agregar una fila

    public void agregarFila(List<Celda<?>> fila) {
        if (fila == null) {
            throw new IllegalArgumentException("La fila a agregar no puede ser nula.");
        }
        if (fila.size() != this.getCantidadColumnas()) {
            throw new IllegalArgumentException("El número de celdas en la fila (" + fila.size() +
                    ") no coincide con el número de columnas de la tabla (" + this.getCantidadColumnas() + ").");
        }

        int i = 0;
        // Iterar sobre las columnas en su orden de inserción (gracias a LinkedHashMap)
        for (Map.Entry<Etiqueta, Columna> entry : columnas.entrySet()) {
            Columna columna = entry.getValue();
            Celda<?> celda = fila.get(i);
            // La validación de la celda con el tipo de la columna ya la hace columna.agregarCelda()
            columna.agregarCelda(celda);
            i++;
        }
    }

    public void imprimirColumnasDetalle() {
        if (columnas.isEmpty()) {
            System.out.println("La tabla está vacía.");
            return;
        }

        for (Map.Entry<Etiqueta, Columna> entry : columnas.entrySet()) {
            Etiqueta etiqueta = entry.getKey();
            Columna columna = entry.getValue();

            System.out.print(etiqueta.getValor() + " : ("); // Imprime la etiqueta y el inicio del paréntesis

            List<Celda<?>> celdas = columna.getCeldas(); // Obtiene la lista de celdas de la columna

            // Itera sobre cada celda de la columna para imprimir su valor
            for (int i = 0; i < celdas.size(); i++) {
                Celda<?> celda = celdas.get(i);
                System.out.print(celda.toString()); // Usa el toString de Celda para el valor

                if (i < celdas.size() - 1) {
                    System.out.print(", "); // Agrega coma y espacio entre celdas
                }
            }
            System.out.println(")"); // Cierra el paréntesis y salta de línea para la siguiente columna
        }
    }
}
