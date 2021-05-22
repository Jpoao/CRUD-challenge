package com.module01.clientCRUD.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.module01.clientCRUD.dto.ClientDTO;
import com.module01.clientCRUD.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

	@Autowired
	private ClientService service;

	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageRequest) {
		Page<ClientDTO> result = service.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
		ClientDTO idDTO = service.findById(id);
		return ResponseEntity.ok().body(idDTO);
	}

	@PostMapping
	public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO newClient) {
		newClient = service.insert(newClient);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newClient.getId())
				.toUri();
		return ResponseEntity.created(uri).body(newClient);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO updated) {
		updated = service.update(id, updated);
		return ResponseEntity.ok().body(updated);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
