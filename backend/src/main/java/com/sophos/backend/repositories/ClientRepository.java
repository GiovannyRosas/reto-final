package com.sophos.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sophos.backend.models.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
  ClientEntity findById(int id);
}
