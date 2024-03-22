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

    public Book(int id, String title, String yearPublication, float price, int idAuthor, Author author) {
        this.id = id;
        this.title = title;
        this.yearPublication = yearPublication;
        this.price = price;
        this.idAuthor = idAuthor;
        this.author = author;
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
                ", price=" + price +
                ", idAuthor=" + idAuthor +
                '}';
    }

    public String bookInformationbyAuthor(){
        return "Book: " + title + "\n" +
               "Year Publication: " + yearPublication + "\n" +
               "Price: " + price + "\n" +
               "ID: " + id + "\n" +
               "___________________________________________________________________\n"    ;

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
