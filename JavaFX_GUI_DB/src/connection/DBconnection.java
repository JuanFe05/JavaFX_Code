package connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DBconnection {
    
    public Connection connect() throws ClassNotFoundException {

        String url_db = "C:\\laragon\\www\\Java_MinTIC\\Retos\\reto4_GUI_DB\\src\\DB\\db_DatosCuerpoAgua.db";
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + url_db);
            System.out.println("CONECTADO");

        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: " + cnfe.getMessage());
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return connection;
    }

}
