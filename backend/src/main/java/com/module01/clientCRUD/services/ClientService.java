package com.module01.clientCRUD.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.module01.clientCRUD.dto.ClientDTO;
import com.module01.clientCRUD.entities.Client;
import com.module01.clientCRUD.repositories.ClientRepository;
import com.module01.clientCRUD.servicies.expetions.EntityNotFoundExpetions;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(Pageable pageRequest){
			Page<Client> result = repository.findAll(pageRequest);
			return result.map(x -> new ClientDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id){
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new EntityNotFoundExpetions("Sorry id: " + id + " does not exist.. entity not found"));
		return new ClientDTO(entity);
		}
	
	@Transactional
	public ClientDTO insert(ClientDTO created) {
			Client client = new Client();
			client.setBirthDate(created.getBirthDate());
			client.setChildren(created.getChildren());
			client.setCpf(created.getCpf());
			client.setIncome(created.getIncome());
			client.setName(created.getName());
			client = repository.save(client);
			return new ClientDTO(client);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO updated) {
		try {
			Client client = repository.getOne(id);
			client.setBirthDate(updated.getBirthDate());
			client.setChildren(updated.getChildren());
			client.setCpf(updated.getCpf());
			client.setIncome(updated.getIncome());
			client.setName(updated.getName());
			client = repository.save(client);
			return new ClientDTO(client);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundExpetions("Client id: " + id + " not found");
		}
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundExpetions("sorry id: " + id + " not found");
		}
	}
}
