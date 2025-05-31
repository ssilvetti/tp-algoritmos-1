package gestiondedatos;

public class Celda<T> {

    private T valor;
    private TipoDato tipoDato; // El tipo de dato de esta celda específica

    public Celda(T valor, TipoDato tipoDato) {
        if (tipoDato == null) {
            throw new IllegalArgumentException("El tipo de dato de la celda no puede ser nulo.");
        }

        //Chequeamos que si el valor es nulo, el tipo de dato tambien sea nulo.
        //Y que si no es nulo, el tipo de dato tampoco.

        if (valor == null) {
            if (tipoDato != TipoDato.NA) {
                throw new IllegalArgumentException("Error de consistencia en gestiondedatos.Celda: " +
                        "Si el valor es nulo, el tipo de dato debe ser gestiondedatos.TipoDato.NA.");
            }
        } else {
            if (tipoDato == TipoDato.NA) {
                throw new IllegalArgumentException("Error de consistencia en gestiondedatos.Celda: " +
                        "Si el valor no es nulo, el tipo de dato no puede ser gestiondedatos.TipoDato.NA.");
            }

        }

        this.valor = valor;
        this.tipoDato = tipoDato;
    }

    // Constructor para crear una celda NA (valor nulo)
    public Celda() {
        this(null, TipoDato.NA);
    }

    public T getValor() {
        return valor;
    }

    public TipoDato getTipoDato() {
        return tipoDato;
    }

    // Metodo para saber si la celda es NA
    public boolean isNA() {
        return this.tipoDato == TipoDato.NA;
    }

    //Imprime el valor de la celda
    @Override
    public String toString() {
        return isNA() ? "NA" : String.valueOf(valor);
    }
}