package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;
import entity.Book;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        //1.  Open the connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert obj to local object
        Book objBook = (Book) obj;

        try{

            //3. Create SQL request
            String sql = "INSERT INTO book (title, year_publication, price, id_author) VALUES (?,?,?,?);";

            //
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //
            objPrepare.setString(1, objBook.getTitle());
            objPrepare.setString(2, objBook.getYearPublication());
            objPrepare.setFloat(3, objBook.getPrice());
            objPrepare.setInt(4, objBook.getIdAuthor());

            objPrepare.execute();
            //
            ResultSet objResult = objPrepare.getGeneratedKeys();

            //
            while(objResult.next()){
                //
                objBook.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Book insertion was successful");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR: "+ e.getMessage());
        }

        return objBook;
    }


    @Override
    public List<Object> findAll() {
        //
        AuthorModel objAuthorModel = new AuthorModel();
        List<Object> booksList  = new ArrayList<>();
        //
        Connection objConnection = ConfigDB.openConnection();
        //
        try {
            String sql = "SELECT * FROM book;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                //
                Book objBook = new Book();
                //
                objBook.setId(Integer.parseInt(objResult.getString("id")));
                objBook.setTitle(objResult.getString("title"));
                objBook.setYearPublication(objResult.getString("year_publication"));
                objBook.setPrice(objResult.getFloat("price"));
                objBook.setIdAuthor(objResult.getInt("id_author"));
//
                //Llenar el objeto Author para ingresar al objeto del libro
                Author objAuthor = objAuthorModel.findById(objBook.getIdAuthor());
                objBook.setAuthor(objAuthor);
                
                //
                booksList.add(objBook);
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to prepare statement\n"+e.getMessage());
        }

        //
        ConfigDB.closeConnection();

        return booksList;
    }

    @Override
    public boolean update(Object obj) {
// 1.
        Connection objConnection =  ConfigDB.openConnection();
        // 2.
        Book objBook = (Book) obj;
        // 3.
        boolean isUpdate = false;

        try {
            // 4. Sentencia SQL
            String sql = "UPDATE book SET title = ?, year_publication = ?, price = ?, id_autor = ?  WHERE id = ?; ";

            // 5. Prepara el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Se le da valores a los ???
            objPrepare.setString(1, objBook.getTitle());
            objPrepare.setString(2, objBook.getYearPublication());
            objPrepare.setFloat(3, objBook.getPrice());
            objPrepare.setInt(4, objBook.getIdAuthor());
            objPrepare.setInt(5, objBook.getId());
            

            // 7. Ejecutar Query
            int totalRowAffected = objPrepare.executeUpdate();
            System.out.println(totalRowAffected);

            //8. Verificar si se actualizaron las filas
            if(totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to prepare statement\n"+e.getMessage());
        }finally{
            return isUpdate;
        }
    }

    @Override
    public boolean delete(Object obj) {
        //1. Convertir el objeto a la entidad
        Book objBook = (Book) obj;

        //2 Abrimos la conexion
        Connection objConection = ConfigDB.openConnection();

        //Creamos una variable de estado llamada bandera
        boolean isDeleted = false;

        //3. Creamos el try catch

        try {
            //5 Escribimos sentencia de SQL para eliminar
            String sql = "DELETE FROM book WHERE id = ?;";

            //5. Creamos el prepare statement
            PreparedStatement objPrepare = objConection.prepareStatement(sql);

            //6. Dar valor al ?
            objPrepare.setInt(1,objBook.getId());

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

    public Book findById (int  id){
        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear el Author que vamos a retornar
        Book objBook = null;
        AuthorModel objAuthorModel = new AuthorModel();
//        ArrayList<Author>  authorList = new ArrayList<>();

        try{
            //3. Sentencia SQL
            String sql ="SELECT * FROM book Where id = ?;";

            //4. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al parametro del Query (?)
            objPrepare.setInt(1,id);

            //6. Ejecutar el Query (Query) trae un resultado de la consulta, se debe guardar con el tipo de variable ResultSet para guardar la consulta pedida
            ResultSet objResult = objPrepare.executeQuery();


            while(objResult.next()){//Definicion del next

                objBook = new Book();
                //
                objBook.setId(Integer.parseInt(objResult.getString("id")));
                objBook.setTitle(objResult.getString("title"));
                objBook.setYearPublication(objResult.getString("year_publication"));
                objBook.setPrice(objResult.getFloat("price"));
                objBook.setIdAuthor(objResult.getInt("id_author"));

                //Llenar el objeto Author para ingresar al objeto del libro
                Author objAuthor = objAuthorModel.findById(objBook.getIdAuthor());
                objBook.setAuthor(objAuthor);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar  connection
        ConfigDB.closeConnection();
        return objBook;
    }
    public ArrayList<Book> findByTitle (String name){
        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear el Book que vamos a retornar
        Book objBook = null;
        AuthorModel objAuthorModel = new AuthorModel();
        ArrayList<Book>  BookList = new ArrayList<>();

        try{
            //3. Sentencia SQL
            String sql ="SELECT * FROM book Where title like ?;";

            //4. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al parametro del Query (?)
            objPrepare.setString(1,"%"+name +"%");

            //6. Ejecutar el Query (Query) trae un resultado de la consulta, se debe guardar con el tipo de variable ResultSet para guardar la consulta pedida
            ResultSet objResult = objPrepare.executeQuery();


            while(objResult.next()){//Definicion del next

                objBook = new Book();
                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setYearPublication(objResult.getString("year_publication"));
                objBook.setPrice(objResult.getFloat("price"));
                objBook.setIdAuthor(objResult.getInt("id_author"));

                //Llenar el objeto Author para ingresar al objeto del libro
                Author objAuthor = objAuthorModel.findById(objBook.getIdAuthor());
                objBook.setAuthor(objAuthor);

                BookList.add(objBook);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar  connection
        ConfigDB.closeConnection();
        return BookList;
    }

    public ArrayList<Book> findByIdAuthor (int idAuthor){
        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear el Book que vamos a retornar
        Book objBook = null;
        AuthorModel objAuthorModel = new AuthorModel();
        ArrayList<Book>  BookList = new ArrayList<>();

        try{
            //3. Sentencia SQL
            String sql ="SELECT * FROM  book INNER JOIN author ON book.id_author = author.id WHERE author.id = ?; ";

            //4. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al parametro del Query (?)
            objPrepare.setInt(1, idAuthor);

            //6. Ejecutar el Query (Query) trae un resultado de la consulta, se debe guardar con el tipo de variable ResultSet para guardar la consulta pedida
            ResultSet objResult = objPrepare.executeQuery();


            while(objResult.next()){//Definicion del next

                objBook = new Book();
                objBook.setId(objResult.getInt("book.id"));
                objBook.setTitle(objResult.getString("book.title"));
                objBook.setYearPublication(objResult.getString("book.year_publication"));
                objBook.setPrice(objResult.getFloat("book.price"));
                objBook.setIdAuthor(objResult.getInt("book.id_author"));

                //Llenar el objeto Author para ingresar al objeto del libro
                Author objAuthor = new Author();
                objAuthor.setId(objBook.getIdAuthor());
                objAuthor.setName(objResult.getString("author.name"));
                objAuthor.setNationality(objResult.getString("author.nationality"));
                objBook.setAuthor(objAuthor);

                BookList.add(objBook);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar  connection
        ConfigDB.closeConnection();
        return BookList;
    }
}
