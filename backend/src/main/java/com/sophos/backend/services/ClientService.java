package com.sophos.backend.services;

import java.util.ArrayList;

import com.sophos.backend.models.ClientEntity;

public interface ClientService {

  public ArrayList<ClientEntity> getClients();

  public ClientEntity getClientById(int id);

  public ClientEntity saveClient(ClientEntity client);

  public ClientEntity editClient(ClientEntity client);

  public void deleteClient(int id);

}
