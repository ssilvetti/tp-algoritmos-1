package gestiondedatos;

import java.util.Map;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Set;

public class Tabla {

    private Map<Etiqueta, Columna> columnas;
    private List<Etiqueta> etiquetasFilas;
    private List<Etiqueta> etiquetasColumnas;

    public Tabla() {
        this.columnas = new LinkedHashMap<>();
        this.etiquetasFilas = new ArrayList<>();
        this.etiquetasColumnas = new ArrayList<>();
    }

    public void agregarColumna(Etiqueta etiquetasColumnas, Columna columna) {
        // Verificacar que la columna no sea nula
        if (columna == null) {
            throw new IllegalArgumentException("La columna a agregar no puede ser nula.");
        }
        // Verificar si ya existe una columna con la misma etiqueta
        if (columnas.containsKey(etiquetasColumnas)) {
            throw new IllegalArgumentException("Ya existe una columna con la etiqueta: " +
                    etiquetasColumnas.getValor());
        }

        this.columnas.put(etiquetasColumnas, columna);
        this.etiquetasColumnas.add(etiquetasColumnas);
    }

    //Sobrecarga del constructor anterior para cuando no hay etiquetas
    //Etiquetas numéricas automáticas
    public void agregarColumna(Columna columna) {
        // Reutiliza el metodo existente con validaciones
        // Genera los items 0,1, 2, 3, etc
        EtiquetaEntero nuevaEtiqueta = new EtiquetaEntero(this.contarColumnas());
        agregarColumna(nuevaEtiqueta, columna);
    }

    public Columna getColumna(Etiqueta etiquetasColumnas) {
        if (!columnas.containsKey(etiquetasColumnas)) {
            throw new IllegalArgumentException("No existe una columna con la etiqueta: " +
                    etiquetasColumnas.getValor());
        }
        return columnas.get(etiquetasColumnas);
    }

    public boolean eliminarColumna(Etiqueta etiquetasColumnas) {
        if (!columnas.containsKey(etiquetasColumnas)) {
            // Lanzar una excepción si la columna no existe, no puede ser eliminada.
            throw new IllegalArgumentException("No se puede eliminar la columna. " +
                    "No existe una columna con la etiqueta: " + etiquetasColumnas.getValor());
        }

        return columnas.remove(etiquetasColumnas) != null;
        // remove devuelve el valor anterior o null si no existía
    // si encuentra ese elemento, que elimine la etiqueta de la lista de etiquetas de columnas
    }

    // Para obtener todas las etiquetas de las columnas:
    public List<Etiqueta> getEtiquetasColumnas() {
        return etiquetasColumnas;
    }

    public List<Etiqueta> getEtiquetasFilas() {
        return etiquetasFilas;
    }

    // Para obtener el número de columnas:
    public int contarColumnas() {
        return columnas.size();
    }

    public int contarFilas() {
        int cantidadFilas = 0;
        if( columnas.size()> 0 ){
            //Existe al menos una columna, obtengo su etiqueta
            Etiqueta clave = etiquetasColumnas.get(0);
            Columna columna;
            //Utilizo la lista creada para no tener que pasar por un iterador
            columna = columnas.get(clave);
            cantidadFilas = columna.contarCeldas();
            return cantidadFilas;
        }
        return cantidadFilas;
    }

    public void agregarEtiquetasFila(List<Etiqueta>  etiquetasFilas) {
        if (contarFilas() < etiquetasFilas.size()) {
            System.out.println("Cantidad de Etiquetas ingresada (" +
                    etiquetasFilas.size() + ") es MAYOR a la cantidad de filas(" +
                    contarFilas() + ")");
        } else if (contarFilas() > etiquetasFilas.size()) {
            System.out.println("Cantidad de Etiquetas ingresada (" +
                    etiquetasFilas.size() + ") es MENOR a la cantidad de filas(" +
                    contarFilas() + ")");
        } else { // la cantidd es la misma
            this.etiquetasFilas = etiquetasFilas;
        }
    }

    public void generarEtiquetasFilas(){
//En caso de no agregar EtiquetasFila, si la lista esta vacia, que genere esa cantidad de indices
        if(etiquetasFilas.isEmpty()) {
            int fin = contarFilas();
            for (int i = 0; i < fin; i++) {
                EtiquetaEntero nuevaEtiqueta = new EtiquetaEntero(i);
                etiquetasFilas.add(nuevaEtiqueta); // Agrega la etiqueta a la lista
            }
        } else {
            System.out.println("No se puede generar Etiquetas atomaticamente. " +
                    "Ya existen Etiquetas");
        }
    }

    //Obtener una fila a partir de su etiqueta
    public List<Celda<?>> getFila(Etiqueta etiquetaFila) {
        int indice = etiquetasFilas.indexOf(etiquetaFila);
        List<Celda<?>> fila = new ArrayList<>();
        for (Columna columna : columnas.values()) {
            fila.add(columna.getCelda(indice));
        }
        // Retornamos esa fila
        return fila;
    }

    // Para agregar una fila
    public void agregarFila(Etiqueta etiquetasFilas,List<Celda<?>> fila) {
        if (fila == null) {
            throw new IllegalArgumentException("La fila a agregar no puede ser nula.");
        }
        if (fila.size() != this.contarColumnas()) {
            throw new IllegalArgumentException("El número de celdas en la fila ("+ fila.size()+
                    ") no coincide con el número de columnas de la tabla ("
                    + this.contarColumnas() + ").");
        }
        int i = 0;
        for(Columna columna: columnas.values()){
            Celda<?> celda = fila.get(i);
            columna.agregarCelda(celda);
            i++;
        }
        this.etiquetasFilas.add(etiquetasFilas);
    }

    //Si la fila no tiene etiqueta
    public void agregarFila(List<Celda<?>> fila) {
        // Genera una etiqueta numérica para la fila (0, 1, 2, ...)
        EtiquetaEntero nuevaEtiquetaFila = new EtiquetaEntero(this.contarFilas());
        // Reutiliza el metodo existente que maneja la lógica de agregar celdas a las columnas
        agregarFila(nuevaEtiquetaFila, fila);
    }

    //Obtener una fila a partir de su indice
    public List<Celda<?>> getFila(int indiceFila) {
        if (indiceFila < 0 || indiceFila >= contarFilas()) {
            throw new IndexOutOfBoundsException("Índice de fila fuera de rango: " + indiceFila);
        }
        List<Celda<?>> filaObtenida = new ArrayList<>();
        // Iterar sobre las columnas en su orden de inserción
        for (Columna columna : columnas.values()) {
            filaObtenida.add(columna.getCelda(indiceFila));
        }
        return filaObtenida;
    }

    public void eliminarFila(Etiqueta etiquetaFila){
        if(etiquetasFilas.contains(etiquetaFila)){
            int indice = etiquetasColumnas.indexOf(etiquetaFila);
            etiquetasFilas.remove(indice); //elimino la etiqueta de la fila de etiquetas
            for (Columna columna: columnas.values()){
                columna.getCeldas().remove(indice); // accedemos a columna, accedemos su
                // lista de celda, removemos la celda con el INDICE
            }
        } else {
            System.out.println("No existe la Etiqueta de fila ("+etiquetaFila+").");
        }
    }

    //Obtener celda a partir de fila y columna
    public Celda<?> getCelda(Etiqueta etiquetaColumna, Etiqueta etiquetaFila){
        if (!(etiquetasFilas.contains(etiquetaFila) &&
                etiquetasColumnas.contains(etiquetaColumna))){
            if (!etiquetasFilas.contains(etiquetaFila)){
                throw new IllegalArgumentException("La Etiqueta de Fila ("+etiquetaFila+")" +
                        " no es válida.");
            } else {
                throw new IllegalArgumentException("La Etiqueta de Columna ("+etiquetaColumna+")" +
                        " no es válida.");
            }
        }
        int indice = etiquetasFilas.indexOf(etiquetaFila);
        Columna columna = columnas.get(etiquetaColumna);
        Celda<?> celda = columna.getCelda(indice);
        return celda;
    }

}
