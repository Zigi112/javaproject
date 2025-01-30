package Services;

import java.util.List;
import java.sql.SQLException;

public interface IService<T> {
    void ajouter(T t) throws SQLException;
    void supprimer(T t) throws SQLException;
    void update(T t) throws SQLException;
    T getById(int id) throws SQLException;
    List<T> getAll() throws SQLException;
}