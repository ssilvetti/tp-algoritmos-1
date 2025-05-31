package gestiondedatos;

import java.util.*;

public class Columna {
    private TipoDato tipoDato; // El tipo de dato esperado para TODAS las celdas de esta columna
    private List<Celda<?>> listaCeldas;

    // Constructor que recibe una lista inicial de celdas
    public Columna(TipoDato tipoDato, List<Celda<?>> listaDeCeldas) {
        // Validaciones iniciales
        if (tipoDato == null) {
            throw new IllegalArgumentException("El tipo de dato de la columna no puede ser nulo.");
        }

        //Esto seria solo si NA lo dejamos para valores faltantes en celdas.
        if (tipoDato == TipoDato.NA) {
            throw new IllegalArgumentException("Una columna no puede ser de tipo NA. " +
                    "NA se utiliza para celdas con valores faltantes.");
        }
        if (listaDeCeldas == null) {
            throw new IllegalArgumentException("La lista inicial de celdas no puede ser nula.");
        }

        this.tipoDato = tipoDato;
        this.listaCeldas = new ArrayList<>(listaDeCeldas);

        //VALIDAR TODAS LAS CELDAS AL INICIAR LA COLUMNA
        for (Celda<?> celda : this.listaCeldas) {
            if (celda == null) { // Las celdas en la lista tampoco pueden ser nulas
                throw new IllegalArgumentException("La lista de celdas contiene un " +
                        "elemento nulo.");
            }
            if (!ValidadorTipoDato.esValido(celda.getValor(),
                    this.tipoDato, celda.getTipoDato())) {
                throw new IllegalArgumentException("La celda con valor '" + celda.getValor() +
                        "' y tipo '" + celda.getTipoDato() +
                        "' no es compatible con el tipo de columna '" + this.tipoDato + "'.");
            }
        }
    }

    // Constructor para una columna vacía
    public Columna(TipoDato tipoDato) {
        // Validaciones iniciales (igual que el constructor anterior)
        if (tipoDato == null) {
            throw new IllegalArgumentException("El tipo de dato de la columna no puede ser nulo.");
        }
        if (tipoDato == TipoDato.NA) {
            throw new IllegalArgumentException("Una columna no puede ser de tipo NA. NA se utiliza para celdas con valores faltantes.");
        }
        this.tipoDato = tipoDato;
        this.listaCeldas = new ArrayList<>(); // Inicializa una lista vacía
    }

    public void agregarCelda(Celda<?> celda) {
        if (celda == null) {
            throw new IllegalArgumentException("La celda a agregar no puede ser nula.");
        }
        // Validar la celda antes de agregarla
        if (ValidadorTipoDato.esValido(celda.getValor(), this.tipoDato, celda.getTipoDato())) {
            this.listaCeldas.add(celda);
        } else {
            // Lanza una excepción en vez de imprimir un error
            throw new IllegalArgumentException("No se pudo agregar la celda con valor '" + celda.getValor() +
                    "' y tipo '" + celda.getTipoDato() +
                    "' porque no es compatible con el tipo de columna esperado '" + this.tipoDato + "'.");
        }
    }

    public TipoDato getTipoDato() {
        return tipoDato;
    }

    public List<Celda<?>> getCeldas() {
        //Retornar una vista inmutable de las celda para proteger el encapsulamiento
        return Collections.unmodifiableList(listaCeldas);
    }

    // Obtener celda por indice
    public Celda<?> getCelda(int indiceFila) {
        if (indiceFila < 0 || indiceFila >= listaCeldas.size()) {
            throw new IndexOutOfBoundsException("Índice de fila fuera de rango: " + indiceFila);
        }
        return listaCeldas.get(indiceFila);
    }

    //Metodo para obtener el número de filas
    public int getCantidadFilas() {
        return listaCeldas.size();
    }
}