package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import model.Operaciones;

public class OperacionesAritmeticas {

    @FXML
    private Button btnCalcular;

    @FXML
    private RadioButton rdbDivision;

    @FXML
    private RadioButton rdbMultiplicacion;

    @FXML
    private RadioButton rdbResta;

    @FXML
    private RadioButton rdbSuma;

    @FXML
    private TextField txtNumero1;

    @FXML
    private TextField txtNumero2;

    @FXML
    private TextField txtResultado;

    @FXML
    void initialize() {
        ToggleGroup tg = new ToggleGroup();
        this.rdbSuma.setToggleGroup(tg);
        this.rdbResta.setToggleGroup(tg);
        this.rdbMultiplicacion.setToggleGroup(tg);
        this.rdbDivision.setToggleGroup(tg);
    }

    @FXML
    void realizarOperacion(ActionEvent event) {

        try {

            double num1 = Double.parseDouble(this.txtNumero1.getText());
            double num2 = Double.parseDouble(this.txtNumero2.getText());

            Operaciones op = new Operaciones(num1, num2);

            if (this.rdbSuma.isSelected()) {
                this.txtResultado.setText(String.valueOf(op.suma()));
            } else if (this.rdbResta.isSelected()) {
                this.txtResultado.setText(String.valueOf(op.resta()));
            } else if (this.rdbMultiplicacion.isSelected()) {
                this.txtResultado.setText(String.valueOf(op.multiplicacion()));
            } else if (this.rdbDivision.isSelected()) {

                if (num2 == 0) {
                    this.txtResultado.setText("undefined");
                } else {
                    this.txtResultado.setText(String.valueOf(op.division()));
                }
            }

        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ha ocurrido un error");
            alert.setContentText("No se pudo realizar la operación");
            alert.showAndWait();
        }

    }

}
