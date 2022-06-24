package model;

import java.util.Objects;

public class ObjetoGeografico {
    
    // Atributos
    private String nombreCuerpo;
    private int idCuerpoAgua;
    private String nombreMunicipio;
    private String tipoDeAgua;
    private String tipoDeCuerpo;
    private double clasificacionIrca;

    // Constructores
    public ObjetoGeografico() {
    }

    public ObjetoGeografico(String nombreCuerpo, int idCuerpoAgua, String nombreMunicipio, String tipoDeAgua, String tipoDeCuerpo, double clasificacionIrca) {
        this.nombreCuerpo = nombreCuerpo;
        this.idCuerpoAgua = idCuerpoAgua;
        this.nombreMunicipio = nombreMunicipio;
        this.tipoDeAgua = tipoDeAgua;
        this.tipoDeCuerpo = tipoDeCuerpo;
        this.clasificacionIrca = clasificacionIrca;
    }

    // Get and set
    public String getNombreCuerpo() {
        return this.nombreCuerpo;
    }

    public void setNombreCuerpo(String nombreCuerpo) {
        this.nombreCuerpo = nombreCuerpo;
    }

    public int getIdCuerpoAgua() {
        return this.idCuerpoAgua;
    }

    public void setIdCuerpoAgua(int idCuerpoAgua) {
        this.idCuerpoAgua = idCuerpoAgua;
    }

    public String getNombreMunicipio() {
        return this.nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public String getTipoDeAgua() {
        return this.tipoDeAgua;
    }

    public void setTipoDeAgua(String tipoDeAgua) {
        this.tipoDeAgua = tipoDeAgua;
    }

    public String getTipoDeCuerpo() {
        return this.tipoDeCuerpo;
    }

    public void setTipoDeCuerpo(String tipoDeCuerpo) {
        this.tipoDeCuerpo = tipoDeCuerpo;
    }

    public double getClasificacionIrca() {
        return this.clasificacionIrca;
    }

    public void setClasificacionIrca(double clasificacionIrca) {
        this.clasificacionIrca = clasificacionIrca;
    }

    // verficación de datos
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ObjetoGeografico)) {
            return false;
        }
        ObjetoGeografico objetoGeografico = (ObjetoGeografico) o;
        return Objects.equals(nombreCuerpo, objetoGeografico.nombreCuerpo) && idCuerpoAgua == objetoGeografico.idCuerpoAgua && Objects.equals(nombreMunicipio, objetoGeografico.nombreMunicipio) && Objects.equals(tipoDeAgua, objetoGeografico.tipoDeAgua) && Objects.equals(tipoDeCuerpo, objetoGeografico.tipoDeCuerpo) && clasificacionIrca == objetoGeografico.clasificacionIrca;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreCuerpo, idCuerpoAgua, nombreMunicipio, tipoDeAgua, tipoDeCuerpo, clasificacionIrca);
    }

    // toString
    @Override
    public String toString() {
        return "{" +
            " nombreCuerpo='" + getNombreCuerpo() + "'" +
            ", idCuerpoAgua='" + getIdCuerpoAgua() + "'" +
            ", nombreMunicipio='" + getNombreMunicipio() + "'" +
            ", tipoDeAgua='" + getTipoDeAgua() + "'" +
            ", tipoDeCuerpo='" + getTipoDeCuerpo() + "'" +
            ", clasificacionIrca='" + getClasificacionIrca() + "'" +
            "}";
    }

}
