package com.sophos.backend.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sophos.backend.entity.ClientEntity;
import com.sophos.backend.entity.ProductEntity;
import com.sophos.backend.model.GeneralResponse;
import com.sophos.backend.services.ClientService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clients")
public class ClientController {

	public static final Logger logger = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	ClientService clientService;

	// Getting all the clients
	@GetMapping("")
	public ResponseEntity<GeneralResponse<ArrayList<ClientEntity>>> getUsers() {

		GeneralResponse<ArrayList<ClientEntity>> response = new GeneralResponse<>();
		HttpStatus status = null;
		ArrayList<ClientEntity> data = null;

		try {

			data = clientService.getClients();
			String msg = "It found " + data.size() + " Clients";

			response.setMessage(msg);
			response.setSuccess(true);
			response.setData(data);
			status = HttpStatus.OK;
		} catch (Exception e) {
			String msg = "Something has failed. Please contact suuport.";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setMessage(msg);
			response.setSuccess(false);

			String log = "End point GET/credit-cards has failed. " + e.getLocalizedMessage();
			logger.error(log);
		}

		return new ResponseEntity<>(response, status);

	}

	// Saving client
	@PostMapping("")
	public ResponseEntity<GeneralResponse<ClientEntity>> saveUser(@RequestBody ClientEntity user) {

		GeneralResponse<ClientEntity> response = new GeneralResponse<>();
		HttpStatus status = null;
		ClientEntity data = null;

		try {
			data = clientService.saveClient(user);
			String msg = "It save a client";

			response.setMessage(msg);
			response.setSuccess(true);
			response.setData(data);
			status = HttpStatus.OK;

		} catch (Exception e) {

			String msg = "Something was wring. Please contact support";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setMessage(msg);
			response.setSuccess(false);

			String log = "End point POST/clients has failed. " + e.getLocalizedMessage();
			logger.error(log);

		}

		return new ResponseEntity<>(response, status);

	}

	// Getting only one user by id
	@GetMapping("/{id}")
	public ClientEntity getUserById(@PathVariable("id") int id) {
		return clientService.getClientById(id);
	}

	// Editing client by id
	@PutMapping("/{id}")
	public ResponseEntity<GeneralResponse<ClientEntity>> editClient(@RequestBody ClientEntity client,
			@PathVariable("id") int id) {

		GeneralResponse<ClientEntity> response = new GeneralResponse<>();
		HttpStatus status = null;
		ClientEntity data = null;

		try {
			client.setId(id);
			data = clientService.editClient(client);

			String msg = "It save a client";
			response.setMessage(msg);
			response.setSuccess(true);
			response.setData(data);
			status = HttpStatus.OK;

		} catch (Exception e) {

			String msg = "Something was wring. Please contact support";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setMessage(msg);
			response.setSuccess(false);

			String log = "End point POST/clients has failed. " + e.getLocalizedMessage();
			logger.error(log);

		}

		return new ResponseEntity<>(response, status);

	}

	// Deleting a Client
	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable("id") int id) {

		GeneralResponse<ClientEntity> response = new GeneralResponse<>();
		HttpStatus status = null;

		try {
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

			response.setMessage("Deleted with success");
			response.setSuccess(true);
			response.setData(null);
			status = HttpStatus.OK;
		} catch (Exception e) {

			String msg = "Something was wring. Please contact support";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setMessage(msg);
			response.setSuccess(false);

			String log = "End point DELETE/clients/{id} has failed" + e.getLocalizedMessage();
			logger.error(log);
		}
	}

}