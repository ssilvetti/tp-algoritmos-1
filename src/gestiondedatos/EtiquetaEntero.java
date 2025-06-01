package gestiondedatos;

public class EtiquetaEntero implements Etiqueta<Integer> {
    private Integer etiqueta;

    public EtiquetaEntero(Integer etiqueta) {
        if (etiqueta == null) {
            throw new IllegalArgumentException("La etiqueta de tipo Entero no puede ser nula.");
        }
        this.etiqueta = etiqueta;
    }

    @Override
    public Integer getValor() { // Retorna Integer directamente
        return etiqueta;
    }

    // Métodos sobrescritos de Object (sin cambios)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EtiquetaEntero otraEtiqueta = (EtiquetaEntero) obj;
        return etiqueta.equals(otraEtiqueta.etiqueta);
    }

    @Override
    public int hashCode() {
        return etiqueta.hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(etiqueta);
    }
}