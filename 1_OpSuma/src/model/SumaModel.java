package model;

public class SumaModel {

    /* Atributos */
    private double n1, n2;

    /* Constructor */
    public SumaModel(double n1, double n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    /* Get y Set */
    public double getN1() {
        return this.n1;
    }

    public void setN1(double n1) {
        this.n1 = n1;
    }

    public double getN2() {
        return this.n2;
    }

    public void setN2(double n2) {
        this.n2 = n2;
    }

    /* Método */
    public double suma() {
        return n1 + n2;
    }

}