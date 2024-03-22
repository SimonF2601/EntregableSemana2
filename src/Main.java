import controller.AuthorController;
import controller.BookController;
import database.ConfigDB;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Create global variables
        String option = "";

        do {
            String authorOption = "", bookOption = "";
            String messageError = "ERROR\n option is invalid ";
            option = JOptionPane.showInputDialog("""
                    Which service do you wish to join?
                    1. Authors
                    2. Books
                    3. Log out
                    
                    Choose an option:
                    """);

            switch (option) {
                case "1":
                    do {
                        authorOption = JOptionPane.showInputDialog("""
                                Author Section
                                Which service do you wish to join?
                                1. Add author
                                2. Consult authors by id
                                3. See all authors
                                4. Update author
                                5. Delete author
                                6. Return to previous menu
                                                    
                                Choose an option:
                                """);

                        switch (authorOption) {
                            case "1":
                                AuthorController.create();
                                break;
                            case "2":
                                AuthorController.searchById();
                                break;
                            case "3":
                                AuthorController.getAllAuthors();
                                break;
                            case "4":
                                AuthorController.update();
                                break;
                            case "5":
                                AuthorController.delete();
                                break;
                            case "6":
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, messageError, "Error", JOptionPane.ERROR_MESSAGE);
                                break;
                        }

                    }while(!authorOption.equals("6"));

                break;
                case "2":
                    do{
                        bookOption = JOptionPane.showInputDialog("""
                        Book Section
                        Which service do you wish to join?
                        1. Add book
                        2. Consult book
                        3. See all books
                        4. Update book
                        5. Delete book
                        6. Return to previous menu
                        
                        Choose an option:
                        """);

                        switch (bookOption) {
                            case "1":
                                BookController.create();
                                break;
                            case "2":
                                String optionConsult = "";
                                do {
                                    optionConsult = JOptionPane.showInputDialog("""
                                            Consult Book Section
                                            Which method do you use for search a book?
                                            1. By name
                                            2. By ID
                                            3. By author
                                            4. Return to previous menu
                                                                
                                            Choose an option:
                                            """);

                                    switch (optionConsult) {
                                        case "1":
                                            BookController.searchByName();
                                            break;
                                        case "2":
                                            BookController.searchById();
                                            break;
                                        case "3":
                                            BookController.getByIdAuthor();
                                            break;
                                        case "4":
                                            break;
                                        default:
                                            JOptionPane.showMessageDialog(null, messageError, "Error", JOptionPane.ERROR_MESSAGE);
                                            break;

                                    }
                                }while(!optionConsult.equals("4"));
                                break;
                            case "3":
                                BookController.getAllBooks();
                                break;
                            case "4":
                                BookController.update();
                                break;
                            case "5":
                                BookController.delete();
                                break;
                            case "6":
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, messageError, "Error", JOptionPane.ERROR_MESSAGE);
                                break;
                        }

                    }while(!bookOption.equals("6"));
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null,"Thank you for using the library system\nCome back soon 👌👌");
                break;
                default:
                    JOptionPane.showMessageDialog(null, messageError, "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }

        }while(!option.equals("3"));
    }
}