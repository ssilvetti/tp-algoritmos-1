package gestiondedatos;

public class Main {
    public static void main(String[] args) {
        Celda <String> celda1 = new Celda<>("Mica",TipoDato.CADENA);
        Celda <String> celda2 = new Celda<>("Ludmi",TipoDato.CADENA);
        Celda <String> celda3 = new Celda<>("Lean",TipoDato.CADENA);
        Celda <String> celda4 = new Celda<>("Santi",TipoDato.CADENA);

        Columna columna1 = new Columna(TipoDato.CADENA);
        Columna columna2 = new Columna(TipoDato.CADENA);

        columna1.agregarCelda(celda1);
        columna1.agregarCelda(celda2);
        columna2.agregarCelda(celda3);
        columna2.agregarCelda(celda4);

        EtiquetaString etiqueta1 = new EtiquetaString("nenas");
        EtiquetaString etiqueta2 = new EtiquetaString("nenes");

        Tabla tabla = new Tabla();

        tabla.agregarColumna(etiqueta1,columna1);
        tabla.agregarColumna(etiqueta2,columna2);

        tabla.imprimirColumnasDetalle();

    }
}
