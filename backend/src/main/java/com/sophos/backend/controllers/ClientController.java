package com.sophos.backend.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sophos.backend.models.ClientModel;
import com.sophos.backend.models.ProductModel;
import com.sophos.backend.interfaces.ClientInterface;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	ClientInterface clientService;

	// Getting all the clients
	@GetMapping("")
	public ArrayList<ClientModel> getUsers(){
		return clientService.getClients();
	}
	
	//Saving client
	@PostMapping("")
	public ClientModel saveUser(@RequestBody ClientModel user) {
		return this.clientService.saveClient(user);
	}
	
	//Getting only one user by id
	@GetMapping("/{id}")
	public ClientModel getUserById( @PathVariable("id") int id) {
		return clientService.getClientById(id);
	}

	//Editing client by id
	@PutMapping("/{id}")
	public ClientModel editClient(@RequestBody ClientModel client, @PathVariable("id") int id) {
		client.setId(id);
		return clientService.editClient(client);
	}

	//Deleting a Client
	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable("id") int id) {
		ArrayList<ProductModel> product = new ArrayList<ProductModel>(id);
		int count = 0;
		for(ProductModel productModel: product) {
			if(productModel.getState().equals("activa") || productModel.getState().equals("inactiva")) {
				count++;
			}
		}
		if(count > 0) {
			System.out.println("Productos activos o inactivos");
		}else {
			System.out.println("Todos productos cancelados");
			clientService.deleteClient(id);
		}
	}
	
}