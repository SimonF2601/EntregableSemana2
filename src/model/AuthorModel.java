package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        //1.  Open the connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert obj to local object
        Author objAuthor = (Author) obj;

        try{

            //3. Create SQL request
            String sql = "INSERT INTO author (name, nationality) VALUES (?, ?);";

            //
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //
            objPrepare.setString(1, objAuthor.getName());
            objPrepare.setString(2, objAuthor.getNationality());

            objPrepare.execute();
            //
            ResultSet objResult = objPrepare.getGeneratedKeys();

            //
            while(objResult.next()){
                //
                objAuthor.setId(objResult.getInt(1)); //TODO POR QUE NO FUNCIONA CON EL NOMBRE DE LA COLUMNA
            }
                JOptionPane.showMessageDialog(null, "Author insertion was successful");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR: "+ e.getMessage());
        }

        return objAuthor;
    }

    @Override
    //Funcion para obtener todos los registros de la base de datos
    public List<Object> findAll() {
        //
        List<Object> authorsList  = new ArrayList<>();
        //
        Connection objConnection = ConfigDB.openConnection();
        //
        try {
            String sql = "SELECT * FROM author;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                //
                Author objAuthor = new Author();
                //
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNationality(objResult.getString("nationality"));
                objAuthor.setId(Integer.parseInt(objResult.getString("id")));
                //
                authorsList.add(objAuthor);
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to prepare statement\n"+e.getMessage());
        }

        //
        ConfigDB.closeConnection();

        return authorsList;
    }

    @Override
    public boolean update(Object obj) {
        // 1.
        Connection objConnection =  ConfigDB.openConnection();
        // 2.
        Author objAuthor = (Author) obj;
        // 3.
        boolean isUpdate = false;

        try {
            // 4. Sentencia SQL
            String sql = "UPDATE author SET name = ? , nationality = ? WHERE id = ?; ";

            // 5. Prepara el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Se le da valores a los ???
            objPrepare.setString(1, objAuthor.getName());
            objPrepare.setString(2, objAuthor.getNationality());
            objPrepare.setInt(3, objAuthor.getId());

            // 7. Ejecutar Query
            int totalRowAffected = objPrepare.executeUpdate();

            //8. Verificar si se actualizaron las filas
            if(totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }
        }catch(Exception e){

        }finally{
            return isUpdate;
        }
    }

    @Override
    public boolean delete(Object obj) {
        //1. Convertir el objeto a la entidad
        Author objAuthor = (Author) obj;

        //2 Abrimos la conexion
        Connection objConection = ConfigDB.openConnection();

        //Creamos una variable de estado llamada bandera
        boolean isDeleted = false;

        //3. Creamos el try catch

        try {
            //5 Escribimos sentencia de SQL para eliminar
            String sql = "DELETE FROM author WHERE id = ?;";

            //5. Creamos el prepare statement
            PreparedStatement objPrepare = objConection.prepareStatement(sql);

            //6. Dar valor al ?
            objPrepare.setInt(1,objAuthor.getId());

            //7. Ejecutamos el Query (executeUpdate) devuelve el numero de registros alterados
            int totalAffectedRows = objPrepare.executeUpdate();

            //Si las filas afectadas fueron mayor a cero quiere decir que si elimino algo
            if(totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"The update was successful");

            }

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }


        //8. Cerramos la conexion
        ConfigDB.closeConnection();
        return isDeleted;
    }

    public Author findById (int  id){
        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear el Author que vamos a retornar
        Author objAuthor = null;
//        ArrayList<Author>  authorList = new ArrayList<>();

        try{
            //3. Sentencia SQL
            String sql ="SELECT * FROM author Where id = ?;";

            //4. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al parametro del Query (?)
            objPrepare.setInt(1,id);

            //6. Ejecutar el Query (Query) trae un resultado de la consulta, se debe guardar con el tipo de variable ResultSet para guardar la consulta pedida
            ResultSet objResult = objPrepare.executeQuery();


            while(objResult.next()){//Definicion del next

                objAuthor = new Author();
                objAuthor.setId(objResult.getInt("id"));
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNationality(objResult.getString("nationality"));
//                authorList.add(objAuthor);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar  connection
        ConfigDB.closeConnection();
        return objAuthor;
    }
}
