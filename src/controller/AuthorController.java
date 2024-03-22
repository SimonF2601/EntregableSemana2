package controller;

import entity.Author;
import model.AuthorModel;

import javax.swing.*;
import java.util.ArrayList;

public class AuthorController {
    public static void create (){
        //Create and use the Author model
        AuthorModel objAuthorModel = new AuthorModel();

        ///Request data to fill the objAuthorder
        String name = JOptionPane.showInputDialog("Insert name");
        String nationality = JOptionPane.showInputDialog("Insert nationality");

        //Create an instance of Author and fill in the objAuthor
        Author objAuthor = new Author(name,nationality);

        //We call the insertion method
        //Here we call the function made in the model to retrieve the id of the object, i.e. we take the data to fill the record, and then return certain values.
        objAuthor = (Author) objAuthorModel.insert(objAuthor);

        JOptionPane.showMessageDialog(null, "Author:\n "+objAuthor.toString(),"Succesfull", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void getAllAuthors(){
        AuthorModel objAuthorModel = new AuthorModel();
        String listAuthors = "Author list \n";

        for(Object iterator: objAuthorModel.findAll() ){

            Author objAuthor = (Author) iterator;

            listAuthors += objAuthor.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listAuthors);

    }

    /**
     * Este metodo tiene como funcion el
     * obtener todos los registros en una cadena en forma de listado.
     * @param listAuthors es una cadena que guardara la lista de los autores
     * @return Lista con la información de los autores
     */
    public static String getAllAuthors(String listAuthors){
        AuthorModel objAuthorModel = new AuthorModel();
        listAuthors = "Author list \n";

        for(Object iterator: objAuthorModel.findAll() ){

            Author objAuthor = (Author) iterator;

            listAuthors += objAuthor.toString() + "\n";
        }

        return listAuthors;
    }

    //TODO  Organizar logica
    public static void searchById(){
        AuthorModel objAuthorModel = new AuthorModel();

        String listAuthors = "";
        listAuthors = getAllAuthors(listAuthors);

        int id = Integer.parseInt(JOptionPane.showInputDialog(null, listAuthors + "\n Enter Author's ID:"));

          Author objAuthor = objAuthorModel.findById(id);

        if (objAuthor == null) {
            JOptionPane.showMessageDialog(null, "Author not found");
        } else {
            String infoAuthor = "";
                infoAuthor +=
                        "Author information:\n\n" +
                        "ID: " + objAuthor.getId() + "\n" +
                        "Name: " + objAuthor.getName() + "\n" +
                        "Nationality: " + objAuthor.getNationality() + "\n";
            JOptionPane.showMessageDialog(null, "\n"+ infoAuthor, "BookStore-Author", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void delete(){
        AuthorModel objAuthorModel = new AuthorModel();

        String listAuthors = "";
        listAuthors = getAllAuthors(listAuthors);

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(null, listAuthors + "\n Enter Author's id to delete"));

        Author objAuthor = objAuthorModel.findById(idDelete);

        if(objAuthor == null){
            JOptionPane.showMessageDialog(null,"Author not found");
        }else{
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure what to delete the Author?\n "+ objAuthor.toString());
            if (confirm == 0) objAuthorModel.delete(objAuthor);
        }
    }

    public static void update(){
        // 1. Utilizar el modelo
        AuthorModel objAuthorModel = new AuthorModel();

        String listAuthors = "";
        listAuthors = getAllAuthors(listAuthors);

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listAuthors+ "\nEnter the Author's ID to edit: "));

        // Obtenemos un Author por el id ingresado
        Author objAuthor = objAuthorModel.findById(idUpdate);

        if (objAuthor == null){
            JOptionPane.showMessageDialog(null, "Author not found");
        } else {
            String name = JOptionPane.showInputDialog(null, "Enter new name:" , objAuthor.getName());
            String nationality = JOptionPane.showInputDialog(null, "Enter new nationality:" , objAuthor.getNationality());


            objAuthor.setName(name);
            objAuthor.setNationality(nationality);
            objAuthorModel.update(objAuthor);

//            JOptionPane.showMessageDialog(null, "Author successfully updated");
        }



    }
}
