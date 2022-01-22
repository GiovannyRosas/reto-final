package com.sophos.backend.interfaces;

import java.util.ArrayList;

import com.sophos.backend.models.ClientModel;

public interface ClientInterface {
  
  public ArrayList<ClientModel> getClients();
  public ClientModel getClientById(int id);
  public ClientModel saveClient(ClientModel client);
  public ClientModel editClient(ClientModel client);
  public void deleteClient(int id);

}
