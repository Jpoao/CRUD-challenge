package com.module01.clientCRUD.servicies;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.module01.clientCRUD.entities.Client;
import com.module01.clientCRUD.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public Page<Client> findAllPaged(PageRequest pageRequest){
		return repository.findAll(pageRequest);
		}
	
	@Transactional(readOnly = true)
	public Client findById(Long id){
		Optional<Client> entity = repository.findById(id);
		return entity.get();
		}
}
