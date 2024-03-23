package controller;

import entity.Author;
import entity.Book;
import model.AuthorModel;
import model.BookModel;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static controller.AuthorController.getAllAuthors;

public class BookController {

    public static void create (){
        ////Create and use the Book model
        BookModel objBookModel = new BookModel();
        AuthorModel objAuthorModel = new AuthorModel();
        String listAuthors = "";
        listAuthors = getAllAuthors(listAuthors);

        ///Request data to fill the objBook
        String title = JOptionPane.showInputDialog("Insert title");
        String yearPublication = JOptionPane.showInputDialog("Insert year of publication");
        List<Object> list = objAuthorModel.findAll();
        Author[] arrAuthor = new  Author[list.size()];

        int index = 0;
        for (Object ite : list){
            Author objAuthor = (Author) ite;
            arrAuthor[index] = objAuthor;
            index++;
        }

        Author idAuthor = (Author) JOptionPane.showInputDialog(null,
                "Insert Authors ID ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                arrAuthor,
                arrAuthor[0]);

        float price = Float.parseFloat(JOptionPane.showInputDialog("Insert price"));

        //Create an instance of Author and fill in the objBook2
        Book objBook = new Book(title, yearPublication, price, idAuthor.getId());

        // //We call the insertion method
        //Here we call the function made in the model to retrieve the id of the object, i.e. we take the data to fill the record, and then return certain values.
        objBook = (Book) objBookModel.insert(objBook);

        JOptionPane.showMessageDialog(null, "Book:\n "+objBook.bookInformation(),"Succesfull", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void getAllBooks(){
        BookModel objBookModel = new BookModel();
        String listBooks = "Books list \n";
        for(Object iterator: objBookModel.findAll() ){

            Book objBook = (Book) iterator;
            listBooks += objBook.bookInformation();
        }

        JOptionPane.showMessageDialog(null, listBooks);

    }

    /**
     * Este metodo tiene como funcion el
     * obtener todos los registros en una cadena en forma de listado.
     * @param listBooks es una cadena que guardara la lista de los autores
     * @return Lista con la información de los autores
     */
    public static String getAllBooks(String listBooks){
        BookModel objBookModel = new BookModel();
        listBooks = "Books list \n";

        for(Object iterator: objBookModel.findAll() ){

            Book objBook = (Book) iterator;

            listBooks += objBook.bookInformation();
        }

        return listBooks;
    }

    public static void delete(){
        BookModel objBookModel = new BookModel();

        String listBooks = "";
        listBooks = getAllBooks(listBooks);

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(null, listBooks + "\n Enter Book's id to delete"));

        Book objBook = objBookModel.findById(idDelete);

        if(objBook == null){
            JOptionPane.showMessageDialog(null,"Book not found");
        }else{
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure what to delete the Author?\n "+ objBook.bookInformation());
            if (confirm == 0) objBookModel.delete(objBook);
        }
    }

    public static void update(){
        // 1. Utilizar el modelo
        BookModel objBookModel = new BookModel();

        String listBooks = "";
        listBooks = getAllBooks(listBooks);

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listBooks+ "\nEnter the Book's ID to edit: "));

        // Obtenemos un Author por el id ingresado
        Book   objBook = objBookModel.findById(idUpdate);

        if (objBook == null){
            JOptionPane.showMessageDialog(null, "Book not found");
        } else {
            String title = JOptionPane.showInputDialog(null, "Enter new title:" , objBook.getTitle());
            String yearPublication = JOptionPane.showInputDialog(null, "Enter new year of publication:" , objBook.getYearPublication());
            int idAuthor = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new Author's ID", objBook.getIdAuthor()));
            float price = Float.parseFloat(JOptionPane.showInputDialog(null, "Enter new price", objBook.getPrice()));

            objBook.setTitle(title);
            objBook.setYearPublication(yearPublication);
            objBook.setPrice(price);
            objBook.setIdAuthor(idAuthor);
            objBookModel.update(objBook);

//            JOptionPane.showMessageDialog(null, "Author successfully updated");
        }



    }

    public static void searchByName() {
        BookModel objBookModel = new BookModel();

        String name = JOptionPane.showInputDialog(null, "Enter Book's title to search 🔎🔎");

        ArrayList<Book> BookList = objBookModel.findByTitle(name);

        if (BookList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Book not found");
        } else {
            String list = "Coincidence with the title \" "+ name +" \" \n";
            for (Book Book : BookList) {
                list += Book.bookInformationbyTitle() + "\n";
            }
            JOptionPane.showMessageDialog(null, "List Books \n"+ list);
        }
    }

    public static void searchById(){
        BookModel objBookModel = new BookModel();

        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Search by Book ID\n Enter Book's ID:"));

        Book objBook = objBookModel.findById(id);

        if (objBook == null) {
            JOptionPane.showMessageDialog(null, "Book not found");
        } else {
            String infoBook = "";
            infoBook +=
                    "Book information:\n\n" +
                            "ID: " + objBook.getId() + "\n" +
                            "Title: " + objBook.getTitle() + "\n" +
                            "Price: " + objBook.getPrice() + "\n" +
                            "Year: " + objBook.getYearPublication() + "\n" +
                            "Author: " + objBook.getAuthor().getName() + "\n";
            JOptionPane.showMessageDialog(null, "\n"+ infoBook, "BookStore-Book", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void getByIdAuthor(){
        BookModel objBookModel = new BookModel();
        AuthorModel objAuthorModel = new AuthorModel();

        List<Object> list = objAuthorModel.findAll();
        Author[] arrAuthor = new  Author[list.size()];

        int index = 0;
        for (Object ite : list){
            Author objAuthor = (Author) ite;
            arrAuthor[index] = objAuthor;
            index++;
        }

        Author idSearch = (Author) JOptionPane.showInputDialog(null,
                "Insert Authors ID to search",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                arrAuthor,
                arrAuthor[0].getName());

        String listBooks = "LIST " +idSearch.getName() + " BOOKS \n";
        for (Book iterator:objBookModel.findByIdAuthor(idSearch.getId())){
            listBooks += iterator.bookInformationbyAuthor() + "\n";
        }
        JOptionPane.showMessageDialog(null,listBooks);
    }
}
