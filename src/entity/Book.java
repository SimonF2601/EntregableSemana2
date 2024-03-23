package entity;

public class Book {
    //Attributes
    private int id;
    private String title;
    private String yearPublication;
    private float price;
    private int idAuthor;

    private Author author;

    //Constructor

    public Book() {
    }

    public Book( String title, String yearPublication, float price, int idAuthor) {
        this.title = title;
        this.yearPublication = yearPublication;
        this.price = price;
        this.idAuthor = idAuthor;
    }
//Methods

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", yearPublication='" + yearPublication + '\'' +
                ", price= boo" + price +
                ", idAuthor=" + idAuthor +
                '}';
    }

    public String bookInformationbyAuthor(){
        return "Book: " + title + "\n" +
               "Year Publication: " + yearPublication + "\n" +
               "Price: $" + price + "\n" +
               "ID: " + id + "\n" +
               "___________________________________________________________________\n"    ;

    }

    /**
     * The purpose of this function is to organize the information to be displayed in the query section by book title.
    @return Returns a string representation of the book information depending on the title
     **/
    public String bookInformationbyTitle(){
        return "Book: " + title + "\n" +
               "Year Publication: " + yearPublication + "\n" +
               "Price: $" + price + "\n" +
               "ID: " + id + "\n" +
               "Author: " + author.getName() + "\n" +
               "___________________________________________________________________\n"    ;

    }

    public String bookInformation(){
        return "▶ ID: " + id + "-  Bookname: " + title + " - Author: " + author.getName() + "\n" ;
    }
    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(String yearPublication) {
        this.yearPublication = yearPublication;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
