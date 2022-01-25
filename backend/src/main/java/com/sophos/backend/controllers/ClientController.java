package com.sophos.backend.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sophos.backend.models.ClientEntity;
import com.sophos.backend.models.ProductEntity;
import com.sophos.backend.services.ClientService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	ClientService clientService;

	// Getting all the clients
	@GetMapping("")
	public ArrayList<ClientEntity> getUsers() {
		return clientService.getClients();
	}

	// Saving client
	@PostMapping("")
	public ClientEntity saveUser(@RequestBody ClientEntity user) {
		return this.clientService.saveClient(user);
	}

	// Getting only one user by id
	@GetMapping("/{id}")
	public ClientEntity getUserById(@PathVariable("id") int id) {
		return clientService.getClientById(id);
	}

	// Editing client by id
	@PutMapping("/{id}")
	public ClientEntity editClient(@RequestBody ClientEntity client, @PathVariable("id") int id) {
		client.setId(id);
		return clientService.editClient(client);
	}

	// Deleting a Client
	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable("id") int id) {
		ArrayList<ProductEntity> product = new ArrayList<ProductEntity>(id);
		int count = 0;
		for (ProductEntity productModel : product) {
			if (productModel.getState().equals("activa") || productModel.getState().equals("inactiva")) {
				count++;
			}
		}
		if (count > 0) {
			System.out.println("Productos activos o inactivos");
		} else {
			System.out.println("Todos productos cancelados");
			clientService.deleteClient(id);
		}
	}

}