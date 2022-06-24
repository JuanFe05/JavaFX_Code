package model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.DBconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CuerpoDeAgua extends ObjetoGeografico {
    
    // Atributo
    private String nivelDeRiesgo;

    // Constructores
    public CuerpoDeAgua() {
        super();
    }

    public CuerpoDeAgua(String nombreCuerpo, int idCuerpoAgua, String nombreMunicipio, String tipoDeAgua,
            String tipoDeCuerpo, double clasificacionIrca) {
        super(nombreCuerpo, idCuerpoAgua, nombreMunicipio, tipoDeAgua, tipoDeCuerpo, clasificacionIrca);
    }

    // Get and set
    public String getNivelDeRiesgo() {
        return this.nivelDeRiesgo;
    }

    public void setNivelDeRiesgo(String nivelDeRiesgo) {
        this.nivelDeRiesgo = nivelDeRiesgo;
    }

    // Método nivel
    public String nivel(double valorClasificacion) {
        if (valorClasificacion >= 0 && valorClasificacion <= 5) {
            this.nivelDeRiesgo = "SIN RIESGO";
        } else if (valorClasificacion >= 5.1 && valorClasificacion <= 14) {
            this.nivelDeRiesgo = "BAJO";
        } else if (valorClasificacion >= 14.1 && valorClasificacion <= 35) {
            this.nivelDeRiesgo = "MEDIO";
        } else if (valorClasificacion >= 35.1 && valorClasificacion <= 80) {
            this.nivelDeRiesgo = "ALTO";
        } else {
            this.nivelDeRiesgo = "INVIABLE SANITARIAMENTE";
        }
        return this.nivelDeRiesgo;
    }

    // Método para guardar los datos
    public boolean guardarRegistros(){
        try {
            DBconnection conexion = new DBconnection();
            Connection conectar = conexion.connect();

            String guardarDatos = "INSERT INTO datos_irca (nombre, municipio, tipo_agua, tipo_cuerpo, clasificacion_irca) VALUES (?,?,?,?,?)";

            PreparedStatement stm = conectar.prepareStatement(guardarDatos);

            stm.setString(1, getNombreCuerpo());
            stm.setString(2, getNombreMunicipio());
            stm.setString(3, getTipoDeAgua());
            stm.setString(4, getTipoDeCuerpo());
            stm.setDouble(5, getClasificacionIrca());

            stm.executeUpdate();
            stm.close();

            System.out.println("Registro exitoso." + guardarDatos.toString());

            return true;

        } catch (Exception e) {
            System.err.println("ERROR AL REGISTRAR LOS DATOS");
            System.err.println("DETALLE DEL ERROR" + e.getMessage());
            e.printStackTrace();

            return false;
        }
    }

    // Método para listar los datos en la tabla y en le txtArea (obtenerDatos)
    public ObservableList<CuerpoDeAgua> listarDatos(){

        ObservableList<CuerpoDeAgua> datosIrcaList = FXCollections.observableArrayList();

        try {
            String consulta_sql = "SELECT * FROM datos_irca";

            DBconnection conexion = new DBconnection();
            Connection conectar = conexion.connect();

            PreparedStatement sentencia = conectar.prepareStatement(consulta_sql);

            ResultSet datos = sentencia.executeQuery();

            while (datos.next()) {
                int id = datos.getInt("id");
                String nombre = datos.getString("nombre");
                String municipio = datos.getString("municipio");
                String tipo_agua = datos.getString("tipo_agua");
                String tipo_cuerpo = datos.getString("tipo_cuerpo");
                double irca = datos.getDouble("clasificacion_irca");

                CuerpoDeAgua aggDatos = new CuerpoDeAgua(nombre, id, municipio, tipo_agua, tipo_cuerpo, irca);

                datosIrcaList.add(aggDatos);
            }
            datos.close();
            sentencia.close();

        } catch (Exception e) {
            System.err.println("ERROR AL LISTAR LOS DATOS");
            System.err.println("DETALLE DEL ERROR" + e.getMessage());
            e.printStackTrace();
        }
        
        return datosIrcaList;
    }
    
    // Metodo consultar los datos por medio del id
    public ResultSet consultarPorId(int id){

        ResultSet respuesta = null;

        try {
            DBconnection conexion = new DBconnection();
            Connection conectar = conexion.connect();

            String sqlRead = "SELECT * FROM datos_irca WHERE id =" + id;
            System.out.println(sqlRead);

            Statement stm = conectar.createStatement();

            respuesta = stm.executeQuery(sqlRead);

        } catch (Exception e) {
            System.out.println("ERROR AL CONSULTAR LOS DATOS");
            System.out.println("DETALLE DEL ERROR" + e.getMessage());
            e.printStackTrace();
        }

        return respuesta;
    }

    // Mètodo para actualizar o modificar los datos
    public boolean editarDatos(int id) {
        try {
            DBconnection conexion = new DBconnection();
            Connection conectar = conexion.connect();

            String update = "UPDATE datos_irca SET nombre = '" + this.getNombreCuerpo() + "', municipio = '"
                    + this.getNombreMunicipio() + "', tipo_agua = '" + this.getTipoDeAgua() + "', tipo_cuerpo = '"
                    + this.getTipoDeCuerpo() + "', clasificacion_irca = " + this.getClasificacionIrca() + " WHERE id = " + id;

            PreparedStatement stm = conectar.prepareStatement(update);

            stm.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println("ERROR AL EDITAR LOS DATOS");
            System.out.println("DETALLE DEL ERROR" + e.getMessage());
            e.printStackTrace();

            return false;
        }
    }

    // Mètodo para eliminar los datos - delete
    public boolean eliminarDatos(int id) {

        try {
            DBconnection conexion = new DBconnection();
            Connection conectar = conexion.connect();

            String delete = "DELETE FROM datos_irca WHERE id = " + id;

            PreparedStatement stm = conectar.prepareStatement(delete);

            stm.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println("ERROR AL ELIMINAR LOS DATOS");
            System.out.println("DETALLE DEL ERROR" + e.getMessage());
            e.printStackTrace();

            return false;
        }
    }

}
