package com.sophos.backend.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.backend.interfaces.ClientInterface;
import com.sophos.backend.models.ClientEntity;
import com.sophos.backend.repositories.ClientRepository;

@Service
public class ClientService implements ClientInterface {

	@Autowired
	ClientRepository clientRepository;

	public ArrayList<ClientEntity> getClients() {
		return (ArrayList<ClientEntity>) clientRepository.findAll();
	}

	public ClientEntity getClientById(int id) {
		return clientRepository.findById(id);
	}

	public ClientEntity saveClient(ClientEntity user) {
		return clientRepository.save(user);
	}

	public ClientEntity editClient(ClientEntity user) {
		return clientRepository.save(user);
	}

	public void deleteClient(int id) {
		clientRepository.deleteById(id);
	}
}