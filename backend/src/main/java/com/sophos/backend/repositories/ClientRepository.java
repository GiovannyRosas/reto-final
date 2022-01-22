package com.sophos.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sophos.backend.models.ClientModel;


@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Integer> {
 ClientModel findById(int id); 
}
