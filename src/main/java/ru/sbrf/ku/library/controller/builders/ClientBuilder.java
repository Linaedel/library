package ru.sbrf.ku.library.controller.builders;

import ru.sbrf.ku.library.dao.ClientDao;
import ru.sbrf.ku.library.entities.Client;

public class ClientBuilder {
    private Client client;
    private ClientDao clientDao;

    public ClientBuilder(ClientDao clientDao) {
        this.client  = new Client();
        this.clientDao = clientDao;
    }

    public Client build() {
        client.setType(2);
        clientDao.add(client);
        return client;
    }

    public ClientBuilder setName(String name){
        client.setName(name);
        return this;
    }

    public ClientBuilder setAddress(String address ) {
        client.setAddress(address);
        return this;
    }

    public ClientBuilder setPhone(String phone ) {
        client.setPhone(phone);
        return this;
    }
}
