package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    //Este atributo tendra el estado de la conexion
    public static Connection objConnection = null;
    //Método para concetar la BD
    public static Connection openConnection (){
        try {
            //Llamamos el driver, y carga la clase llamada para que se utilice en tiempo de ejecucion
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Creamos las variables de conexion
            String url ="jdbc:mysql://bfswlkilfg8qvtqlejta-mysql.services.clever-cloud.com:3306/bfswlkilfg8qvtqlejta";
            String username = "uhehgsoysvuacshb";
            String password = "VRAESuEvcyivxcChEoIG";

            //Establcer conexion, tener muy encuenta los parametros
            objConnection = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("Conecto exitosamente");
        } catch (ClassNotFoundException error) {
            System.out.println("ERROR >> Driver not installed "+ error.getMessage());
        } catch (SQLException error) {
            System.out.println("ERROR >> error al conectar la base de datos");
        }

        return objConnection;
    }

    //Método para finalizar la conexion
    public static void closeConnection(){
        try {
            //Si hay una conexion activa entonces la cierra
            if(objConnection != null) objConnection.close();
            System.out.println("Se finalizo la conexion con exito");
        }catch (SQLException error) {
            System.out.println("Error: "+ error.getMessage());
        }
    }
}
