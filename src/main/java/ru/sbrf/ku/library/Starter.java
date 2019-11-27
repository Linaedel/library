package ru.sbrf.ku.library;

import org.h2.tools.Server;
import ru.sbrf.ku.library.dao.ClientDao;
import ru.sbrf.ku.library.dao.impl.ClientDaoImpl;
import ru.sbrf.ku.library.entities.Client;
import ru.sbrf.ku.library.utils.EntityManagerBuilder;

import java.sql.SQLException;

public class Starter {

    public static void main(String[] args) throws SQLException {
        Server server = Server.createTcpServer().start();
        ClientDao clientDao = new ClientDaoImpl(EntityManagerBuilder.build(Client.class.getName()));
        clientDao.list().forEach(c -> System.out.println(c));


        Client client = new Client();
        client.setName("Test3");
        client.setPhone("12");
        clientDao.insert(client);


        client = new Client();
        client.setName("Test4");
        client.setPhone("21");
        clientDao.insert(client);

        clientDao.list().forEach(c -> System.out.println(c));
        server.stop();
    }


}


