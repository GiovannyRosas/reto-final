package com.sophos.backend.repositories;

import com.sophos.backend.entity.ClientEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
  ClientEntity findById(int id);
}
