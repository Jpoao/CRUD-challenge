package com.module01.clientCRUD.servicies;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.module01.clientCRUD.dto.ClientDTO;
import com.module01.clientCRUD.entities.Client;
import com.module01.clientCRUD.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest){
		Page<Client> result = repository.findAll(pageRequest);
		return result.map(x -> new ClientDTO(x));
		}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id){
		Optional<Client> entity = repository.findById(id);
		return entity.map(x -> new ClientDTO(x)).get();
		}
}
