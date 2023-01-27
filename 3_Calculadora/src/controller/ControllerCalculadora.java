package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.ModelCalculadora;

public class ControllerCalculadora {

    @FXML
    private Button btnBorrar;

    @FXML
    private TextField txtResultado;

    @FXML
    void clickBorrar(ActionEvent event) {
        this.txtResultado.setText("");
    }

    private double numero1 = 0;
    private String operador = "";
    private boolean inicio = true;
    ModelCalculadora objCal = new ModelCalculadora();

    @FXML
    void tecladoNumerico(ActionEvent event) {
        if (inicio) {
            this.txtResultado.setText("");
            inicio = false;
        }

        String valor = ((Button) event.getSource()).getText();
        if (!this.txtResultado.getText().contains(".") || !valor.equals(".")) {
            this.txtResultado.setText(this.txtResultado.getText() + valor);
        }
    }

    @FXML
    void procesandoOperador(ActionEvent event) {
        String valor = ((Button) event.getSource()).getText();

        if (!"=".equals(valor)) {
            if (!operador.isEmpty()) {
                return;
            }
            operador = valor;
            numero1 = Double.parseDouble(this.txtResultado.getText());
            this.txtResultado.setText("");
        }

        else {
            if (operador.isEmpty()) {
                return;
            }

            this.txtResultado.setText(objCal.operaciones(numero1, Double
                    .parseDouble(this.txtResultado.getText()),
                    operador));
            operador = "";
            inicio = true;
        }
    }

}
