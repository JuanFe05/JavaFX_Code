package model;

public class ModelCalculadora {

    /* Métodos */
    public String operaciones(Double numero1, Double numero2, String operador) {
        switch (operador) {
            case "+":
                return String.valueOf(numero1 + numero2);
            case "-":
                return String.valueOf(numero1 - numero2);
            case "*":
                return String.valueOf(numero1 * numero2);
            case "/":
                if (numero2 == 0) {
                    return "undefined";
                }
                return String.valueOf(numero1 / numero2);
            case "^":
                return String.valueOf(Math.pow(numero1, numero2));
        }

        return "0";
    }

}
