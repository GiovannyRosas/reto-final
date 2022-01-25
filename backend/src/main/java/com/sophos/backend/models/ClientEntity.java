package com.sophos.backend.models;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class ClientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;
	private String typeId;
	private String numberId;
	private String lastname;
	private String name;
	private String email;
	private String dateBirth;
	private String telephone;
	private String dateCreation;

	public ClientEntity() {

	}

	public ClientEntity(int id, String typeId, String numberId, String lastname, String name, String email,
			String dateBirth,
			String telephone, String dateCreation) {
		this.id = id;
		this.typeId = typeId;
		this.numberId = numberId;
		this.lastname = lastname;
		this.name = name;
		this.email = email;
		this.dateBirth = dateBirth;
		this.telephone = telephone;
		this.dateCreation = dateCreation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getNumberId() {
		return numberId;
	}

	public void setNumberId(String numberId) {
		this.numberId = numberId;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

}