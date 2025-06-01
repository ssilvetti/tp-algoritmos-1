package visualizador;

import gestiondedatos.*;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;

public class Visualizador {

    private static final int PADDING = 4;

    public static void imprimirTabla(Tabla tabla) {
        if (tabla == null) {
            System.out.println("La tabla es nula y no puede ser visualizada.");
            return;
        }
        if (tabla.contarColumnas() == 0 && tabla.contarFilas() == 0) {
            System.out.println("La tabla está vacía.");
            return;
        }

        List<Etiqueta> etiquetasColumnas = tabla.getEtiquetasColumnas();

        // Calcular el ancho máximo para la columna de etiquetas de fila
        int anchoEtiquetaFila = "FILA".length(); // Ancho mínimo para la cabecera "FILA"
        if (tabla.contarFilas() > 0) {
            for (Etiqueta etiquetaFila : tabla.getEtiquetasFilas()) {
                anchoEtiquetaFila = Math.max(anchoEtiquetaFila,
                        String.valueOf(etiquetaFila.getValor()).length());
            }
        }

        // Calcular los anchos máximos para cada columna de datos
        Map<Etiqueta, Integer> anchosColumnas = new LinkedHashMap<>();
        for (Etiqueta etiquetaColumna : etiquetasColumnas) {
            int anchoMaximoColumna = String.valueOf(etiquetaColumna.getValor()).length();
            Columna columna = tabla.getColumna(etiquetaColumna);

            for (int i = 0; i < tabla.contarFilas(); i++) {
                Celda<?> celda = columna.getCelda(i);
                anchoMaximoColumna = Math.max(anchoMaximoColumna, celda.toString().length());
            }
            anchosColumnas.put(etiquetaColumna, anchoMaximoColumna);
        }

        // --- IMPRESIÓN DE LA CABECERA ---
        System.out.printf("%-" + (anchoEtiquetaFila + PADDING) + "s", "FILA");

        for (Etiqueta etiquetaColumna : etiquetasColumnas) {
            int ancho = anchosColumnas.get(etiquetaColumna);
            System.out.printf("%-" + (ancho + PADDING) + "s",
                    String.valueOf(etiquetaColumna.getValor()));
        }
        System.out.println(); // Salto de línea después de la cabecera

        // --- IMPRESIÓN DE LAS FILAS DE DATOS ---
        for (int i = 0; i < tabla.contarFilas(); i++) {
            Etiqueta etiquetaFila = tabla.getEtiquetasFilas().get(i);
            List<Celda<?>> fila = tabla.getFila(i);

            // Imprimir la etiqueta de fila
            System.out.printf("%-" + (anchoEtiquetaFila + PADDING) + "s",
                    String.valueOf(etiquetaFila.getValor()));

            // Imprimir los valores de las celdas en esa fila
            for (int j = 0; j < etiquetasColumnas.size(); j++) {
                Etiqueta etiquetaColumna = etiquetasColumnas.get(j);
                int ancho = anchosColumnas.get(etiquetaColumna);
                Celda<?> celda = fila.get(j);
                System.out.printf("%-" + (ancho + PADDING) + "s", celda.toString());
            }
            System.out.println(); // Salto de línea después de cada fila
        }
    }
}
