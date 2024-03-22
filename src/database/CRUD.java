package database;

import java.util.List;

public interface CRUD {
    //Se le añade todos los metodos que se usara en esta interfaz

    //Se generaliza con Object al ser la parte mas inicial de un objecto
    public Object insert(Object obj);
    public List<Object> findAll();
    public boolean update(Object obj);
    public boolean delete(Object obj);

}
