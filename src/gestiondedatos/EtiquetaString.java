package gestiondedatos;

public class EtiquetaString implements Etiqueta<String> {
    private String etiqueta;

    public EtiquetaString(String etiqueta) {
        //verico que no sea nula o texto vacio
        if (etiqueta == null || etiqueta.trim().isEmpty()) {
            throw new IllegalArgumentException("La etiqueta de tipo String no puede ser " +
                    "nula o vacía.");
        }
        this.etiqueta = etiqueta;
    }

    @Override
    public String getValor() { // Retorna String directamente
        return etiqueta;
    }

    // Métodos sobrescritos de Object (sin cambios)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EtiquetaString otraEtiqueta = (EtiquetaString) obj;
        return etiqueta.equals(otraEtiqueta.etiqueta);
    }

    @Override
    public int hashCode() {
        return etiqueta.hashCode();
    }

    @Override
    public String toString() {
        return etiqueta;
    }
}