package com.module01.clientCRUD.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.module01.clientCRUD.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
