package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.SumaModel;

public class SumaController {

    @FXML
    private Button btnSumar;

    @FXML
    private TextField txtNumero1;

    @FXML
    private TextField txtNumero2;

    @FXML
    private TextField txtResultado;

    @FXML
    void sumar(ActionEvent event) {

        try {
            double numero1 = Double.parseDouble(this.txtNumero1.getText());
            double numero2 = Double.parseDouble(this.txtNumero2.getText());

            SumaModel opSuma = new SumaModel(numero1, numero2);

            double resultado = opSuma.suma();

            this.txtResultado.setText(String.valueOf(resultado));
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ha ocurrido un error");
            alert.setContentText("No se pudo realizar la operación");
            alert.showAndWait();
        }

    }

}
