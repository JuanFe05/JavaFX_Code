package model;

public class Operaciones {

    /* Atributos */
    private double numero1, numero2;

    /* Constructor */
    public Operaciones(double numero1, double numero2) {
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    /* Get y Set */
    public double getNumero1() {
        return this.numero1;
    }

    public void setNumero1(double numero1) {
        this.numero1 = numero1;
    }

    public double getNumero2() {
        return this.numero2;
    }

    public void setNumero2(double numero2) {
        this.numero2 = numero2;
    }

    /* Métodos */
    public double suma() {
        return numero1 + numero2;
    }

    public double resta() {
        return numero1 - numero2;
    }

    public double multiplicacion() {
        return numero1 * numero2;
    }

    public double division() {
        return numero1 / numero2;
    }

}
