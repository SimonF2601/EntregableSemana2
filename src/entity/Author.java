package entity;

public class Author {
    //Attributes
    private int id;
    private String name;
    private String nationality;
    //Constructor

    public Author() {
    }

    public Author(int id, String name, String nationality) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
    }

    public Author(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    //Methods

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }

    public String information() {
        return "ID: " + id + "\n" +
               "Name: " + name + "\n" +
               "Nationality: " + nationality;
    }

    public String listInformation() {
        return "ID: "+ id + " - Name: " + name;
    }


    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
