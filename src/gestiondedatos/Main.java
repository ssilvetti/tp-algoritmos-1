package gestiondedatos;

import visualizador.Visualizador;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // --- PRUEBA 1: Tabla Completa y Heterogénea ---

        // 1. Crear columnas vacías con sus tipos y etiquetas
        Columna colNumerica = new Columna(TipoDato.NUMERICO);
        Columna colCadena = new Columna(TipoDato.CADENA);
        Columna colBooleana = new Columna(TipoDato.BOOLEANO);

        // 2. Crear etiquetas de columna
        EtiquetaString etColNum = new EtiquetaString("Edad");
        EtiquetaString etColStr = new EtiquetaString("Nombre Completo");
        EtiquetaString etColBool = new EtiquetaString("Activo");

        // 3. Crear la tabla y agregar las columnas
        Tabla tabla = new Tabla();
        tabla.agregarColumna(etColNum, colNumerica);
        tabla.agregarColumna(etColStr, colCadena);
        tabla.agregarColumna(etColBool, colBooleana);

        // 4. Agregar filas con datos de diferentes longitudes y NAs
        // Fila 0
        List<Celda<?>> fila0 = Arrays.asList(
                new Celda<>(25, TipoDato.NUMERICO),
                new Celda<>("Juan Pérez", TipoDato.CADENA),
                new Celda<>(true, TipoDato.BOOLEANO)
        );
        tabla.agregarFila(new EtiquetaString("A1"), fila0); // Etiqueta de fila String

        // Fila 1
        List<Celda<?>> fila1 = Arrays.asList(
                new Celda<>(30.5, TipoDato.NUMERICO),
                new Celda<>("María Laura Rodríguez", TipoDato.CADENA), // Valor de cadena más largo
                new Celda<>(false, TipoDato.BOOLEANO)
        );
        tabla.agregarFila(new EtiquetaEntero(2), fila1); // Etiqueta de fila Integer

        // Fila 2 (con un NA)
        List<Celda<?>> fila2 = Arrays.asList(
                new Celda<>(), // NA
                new Celda<>("Carlos", TipoDato.CADENA),
                new Celda<>(true, TipoDato.BOOLEANO)
        );
        tabla.agregarFila(new EtiquetaString("Registro_3"), fila2);

        // Fila 3 (con varios NAs)
        List<Celda<?>> fila3 = Arrays.asList(
                new Celda<>(42, TipoDato.NUMERICO),
                new Celda<>(), // NA
                new Celda<>() // NA
        );
        tabla.agregarFila(fila3); // Etiqueta de fila auto-generada (Integer)

        // Fila 4 (valores cortos)
        List<Celda<?>> fila4 = Arrays.asList(
                new Celda<>(7, TipoDato.NUMERICO),
                new Celda<>("Sol", TipoDato.CADENA),
                new Celda<>(false, TipoDato.BOOLEANO)
        );
        tabla.agregarFila(fila4);

        System.out.println("--- TABLA DE EJEMPLO COMPLETA ---");
        Visualizador.imprimirTabla(tabla);

        // --- PRUEBA 2: Tabla Vacía ---
        System.out.println("\n--- TABLA VACÍA ---");
        Tabla tablaVacia = new Tabla();
        Visualizador.imprimirTabla(tablaVacia);

        // --- PRUEBA 3: Tabla con Columnas pero sin Filas ---
        System.out.println("\n--- TABLA CON COLUMNAS PERO SIN FILAS ---");
        Tabla tablaSinFilas = new Tabla();
        tablaSinFilas.agregarColumna(new EtiquetaString("Producto"), new Columna(TipoDato.CADENA));
        tablaSinFilas.agregarColumna(new EtiquetaString("Cantidad"), new Columna(TipoDato.NUMERICO));
        Visualizador.imprimirTabla(tablaSinFilas);

    }
}