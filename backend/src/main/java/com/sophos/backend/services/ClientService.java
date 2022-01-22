package com.sophos.backend.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.backend.interfaces.ClientInterface;
import com.sophos.backend.models.ClientModel;
import com.sophos.backend.repositories.ClientRepository;

@Service
public class ClientService implements ClientInterface{

	@Autowired
	ClientRepository clientRepository;
	
	public ArrayList<ClientModel> getClients(){
		return (ArrayList<ClientModel>)clientRepository.findAll();
	}
	public ClientModel getClientById(int id) {
		return clientRepository.findById(id);
	}
	public ClientModel saveClient(ClientModel user) {
		return clientRepository.save(user);
	}
	public ClientModel editClient(ClientModel user) {
		return clientRepository.save(user);
	}
	public void deleteClient(int id) {
		clientRepository.deleteById(id);
	}
}