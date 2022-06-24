package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CuerpoDeAgua;

public class ControllerReto4 implements Initializable {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtMunicipio;

    @FXML
    private TextField txtTipoDeAgua;

    @FXML
    private TextField txtTipoDeCuerpoAgua;

    @FXML
    private TextField txtClasificacionIrca;

    @FXML
    private Button btnGuardarDatos;

    @FXML
    private TableView<CuerpoDeAgua> tblDatosIrca;

    @FXML
    private TableColumn<CuerpoDeAgua, Integer> colId;

    @FXML
    private TableColumn<CuerpoDeAgua, String> colNombre;

    @FXML
    private TableColumn<CuerpoDeAgua, String> colMunicipio;

    @FXML
    private TableColumn<CuerpoDeAgua, String> colTipoDeAgua;

    @FXML
    private TableColumn<CuerpoDeAgua, String> colTipoDeCuerpoAgua;

    @FXML
    private TableColumn<CuerpoDeAgua, Double> colClasificacionIrca;

    @FXML
    private TextArea txtObtenerDatos;

    @FXML
    private Button btnObtenerDatos;

    @FXML
    private TextArea txtProcesarDatos;

    @FXML
    private Button btnProcesarDatos;

    @FXML
    private Button btnLimpiarTexto;

    @FXML
    private TextField txtId_UD;

    @FXML
    private TextField txtNombre_UD;

    @FXML
    private TextField txtMunicipio_UD;

    @FXML
    private TextField txtTipoAgua_UD;

    @FXML
    private TextField txtTipoCuerpo_UD;

    @FXML
    private TextField txtIrca_UD;

    @FXML
    private TextField txtBuscarPorId;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnEditarDatos;

    @FXML
    private Button btnEliminarDatos;

    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL args, ResourceBundle arg1) {
        cargarDatosTabla();
    }
    
    /* Métodos btn */
    @FXML
    void buscarDatos(ActionEvent event) {
        CuerpoDeAgua cdp = new CuerpoDeAgua();

        if (txtBuscarPorId.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR!");
            alerta.setHeaderText(null);
            alerta.setContentText("EL CAMPO PARA BUSCAR UN ID NO PUEDE ESTAR VACÍO");
            alerta.showAndWait();
        } else {
            ResultSet rps = cdp.consultarPorId(Integer.parseInt(txtBuscarPorId.getText()));

            if (rps == null) {
                System.err.println("ERROR!");
            } else {
                try {
                    System.out.println(rps.getString("id"));
                    while (rps.next()) {
                        this.txtId_UD.setText(rps.getString("id"));
                        this.txtNombre_UD.setText(rps.getString("nombre"));
                        this.txtMunicipio_UD.setText(rps.getString("municipio"));
                        this.txtTipoAgua_UD.setText(rps.getString("tipo_agua"));
                        this.txtTipoCuerpo_UD.setText(rps.getString("tipo_cuerpo"));
                        this.txtIrca_UD.setText(rps.getString("clasificacion_irca"));

                        btnEditarDatos.setDisable(false);
                        btnEliminarDatos.setDisable(false);
                        btnCancelar.setDisable(false);
                    }
                } catch (Exception e) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR");
                    alerta.setHeaderText(null);
                    alerta.setContentText("EL CÓDGIO INGRESADO NO EXISTE");
                    alerta.showAndWait();

                    btnEditarDatos.setDisable(true);
                    btnEliminarDatos.setDisable(true);
                    btnCancelar.setDisable(true);

                    limpiarCampos();
                }
            }
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        this.txtBuscarPorId.clear();
        this.txtId_UD.clear();
        this.txtNombre_UD.clear();
        this.txtMunicipio_UD.clear();
        this.txtTipoAgua_UD.clear();
        this.txtTipoCuerpo_UD.clear();
        this.txtIrca_UD.clear();

        btnEditarDatos.setDisable(true);
        btnEliminarDatos.setDisable(true);
        btnCancelar.setDisable(true);
    }

    @FXML
    void editarDatos(ActionEvent event) {
        int idCuerpoAgua = Integer.parseInt(this.txtId_UD.getText());
        String nombreCuerpo = this.txtNombre_UD.getText();
        String nombreMunicipio = this.txtMunicipio_UD.getText();
        String tipoDeAgua = this.txtTipoAgua_UD.getText();
        String tipoDeCuerpo = this.txtTipoCuerpo_UD.getText();
        double clasificacionIrca = Double.parseDouble(this.txtIrca_UD.getText());

        CuerpoDeAgua datosUpdate = new CuerpoDeAgua(nombreCuerpo, idCuerpoAgua, nombreMunicipio, tipoDeAgua,
                tipoDeCuerpo, clasificacionIrca);

        boolean rps = datosUpdate.editarDatos(idCuerpoAgua);

        if (rps) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("ÉXITO");
            alerta.setHeaderText(null);
            alerta.setContentText("DATOS EDITADOS");
            alerta.showAndWait();
            limpiarCampos();
            cargarDatosTabla();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText(null);
            alerta.setContentText("ERROR, AL EDITAR LOS DATOS");
            alerta.showAndWait();
        }

        System.err.println(datosUpdate);
    }

    @FXML
    void eliminarDatos(ActionEvent event) {
        CuerpoDeAgua cdp = new CuerpoDeAgua();

        int idCuerpoAgua = Integer.parseInt(this.txtId_UD.getText());

        boolean rps = cdp.eliminarDatos(idCuerpoAgua);

        if (rps) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("ÉXITO");
            alerta.setHeaderText(null);
            alerta.setContentText("DATOS ELIMINADOS");
            alerta.showAndWait();
            limpiarCampos();
            cargarDatosTabla();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText(null);
            alerta.setContentText("ERROR, AL ELIMINAR LOS DATOS");
            alerta.showAndWait();
        }
    }

    @FXML
    void guardarDatos(ActionEvent event) {
        int idCuerpoAgua = 0;
        String nombreCuerpo = this.txtNombre.getText();
        String nombreMunicipio = this.txtMunicipio.getText();
        String tipoDeAgua = this.txtTipoDeAgua.getText();
        String tipoDeCuerpo = this.txtTipoDeCuerpoAgua.getText();
        double clasificacionIrca = Double.parseDouble(this.txtClasificacionIrca.getText());

        CuerpoDeAgua datosIrcaAgg = new CuerpoDeAgua(nombreCuerpo, idCuerpoAgua, nombreMunicipio, tipoDeAgua,
                tipoDeCuerpo, clasificacionIrca);

        boolean respuesta = datosIrcaAgg.guardarRegistros();

        if (respuesta) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("ÉXITO");
            alerta.setHeaderText(null);
            alerta.setContentText("REGISTRO EXITOSO");
            alerta.showAndWait();
            limpiarCampos();
            cargarDatosTabla();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText(null);
            alerta.setContentText("ERROR, DEBES VOLVER A INTENTARLO");
            alerta.showAndWait();
        }
        System.err.println(datosIrcaAgg);
    }

    @FXML
    void obtenerDatos(ActionEvent event) {
        CuerpoDeAgua cdp = new CuerpoDeAgua();
        List<CuerpoDeAgua> datosIrcaList = cdp.listarDatos();

        for (int i = 0; i < datosIrcaList.size(); i++) {
            txtObtenerDatos.appendText(datosIrcaList.get(i).getIdCuerpoAgua() + " "
                    + datosIrcaList.get(i).getNombreCuerpo() + " " + datosIrcaList.get(i).getNombreMunicipio() + " "
                    + datosIrcaList.get(i).getTipoDeAgua() + " " + datosIrcaList.get(i).getTipoDeCuerpo() + " "
                    + datosIrcaList.get(i).getClasificacionIrca() + "\n");
        }

        btnObtenerDatos.setDisable(true);
        btnProcesarDatos.setDisable(false);
    }

    @FXML
    void procesarDatos(ActionEvent event) {
        CuerpoDeAgua cdp = new CuerpoDeAgua();
        List<CuerpoDeAgua> datosIrcaList = cdp.listarDatos();

        double nivelRiesgo = 0, sumaDatosIrca = 0, promedio, clasificacion = 0;
        String datosSinRiesgo = "";
        Vector<String> nombreDatosSinRiesgo = new Vector<String>();

        for (int i = 0; i < datosIrcaList.size(); i++) {

            clasificacion = datosIrcaList.get(i).getClasificacionIrca();
            String nomClasificacion = cdp.nivel(clasificacion);

            sumaDatosIrca += datosIrcaList.get(i).getClasificacionIrca();

            if (datosIrcaList.get(i).getClasificacionIrca() >= 0 && datosIrcaList.get(i).getClasificacionIrca() <= 14) {
                nivelRiesgo++;
            }

            if (datosIrcaList.get(i).getClasificacionIrca() >= 0 && datosIrcaList.get(i).getClasificacionIrca() <= 5) {
                datosSinRiesgo = datosIrcaList.get(i).getNombreCuerpo();
                nombreDatosSinRiesgo.add(datosSinRiesgo);
            }

            txtProcesarDatos
                    .appendText(String.format("%.2f", datosIrcaList.get(i).getClasificacionIrca()).replace(",", ".")
                            + " -> " + nomClasificacion);
            txtProcesarDatos.appendText("\n");
        }
        promedio = sumaDatosIrca / datosIrcaList.size();

        txtProcesarDatos.appendText(String.format("%.2f", nivelRiesgo).replace(",", "."));
        txtProcesarDatos.appendText("\n");

        if (nombreDatosSinRiesgo.size() < 1)
            txtProcesarDatos.appendText("NA");
        else {
            for (String imprimeDatos : nombreDatosSinRiesgo)
                txtProcesarDatos.appendText(imprimeDatos + " ");
        }

        txtProcesarDatos.appendText("\n");
        txtProcesarDatos.appendText(String.format("%.2f", promedio).replace(",", "."));

        btnProcesarDatos.setDisable(true);
        btnLimpiarTexto.setDisable(false);
    }

    @FXML
    void limpiarTextArea(ActionEvent event) {
        btnLimpiarTexto.setDisable(true);
        btnProcesarDatos.setDisable(true);
        btnObtenerDatos.setDisable(false);

        txtObtenerDatos.clear();
        txtProcesarDatos.clear();
    }

    /* Otros Métodos */
    private void limpiarCampos() {
        txtNombre.clear();
        txtMunicipio.clear();
        txtTipoDeAgua.clear();
        txtTipoDeCuerpoAgua.clear();
        txtClasificacionIrca.clear();

        this.txtBuscarPorId.clear();
        this.txtId_UD.clear();
        this.txtNombre_UD.clear();
        this.txtMunicipio_UD.clear();
        this.txtTipoAgua_UD.clear();
        this.txtTipoCuerpo_UD.clear();
        this.txtIrca_UD.clear();
    }

    private void cargarDatosTabla(){
        this.colId.setCellValueFactory(new PropertyValueFactory("idCuerpoAgua"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombreCuerpo"));
        this.colMunicipio.setCellValueFactory(new PropertyValueFactory("nombreMunicipio"));
        this.colTipoDeAgua.setCellValueFactory(new PropertyValueFactory("tipoDeAgua"));
        this.colTipoDeCuerpoAgua.setCellValueFactory(new PropertyValueFactory("tipoDeCuerpo"));
        this.colClasificacionIrca.setCellValueFactory(new PropertyValueFactory("clasificacionIrca"));

        CuerpoDeAgua cdp = new CuerpoDeAgua();
        ObservableList<CuerpoDeAgua> datosIrcaList = cdp.listarDatos();
        this.tblDatosIrca.setItems(datosIrcaList);
    }

}
