package ru.sbrf.ku.library.dao;

import ru.sbrf.ku.library.entities.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDao {

    void insert(Client client) throws SQLException;
    List<Client> list() throws SQLException;

}
