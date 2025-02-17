package com.vras.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vras.cursomc.domain.Cliente;
import com.vras.cursomc.dto.ClienteDTO;
import com.vras.cursomc.services.ClienteService;

@RestController
@RequestMapping( value = "clientes" )
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping( value = "/{id}" , method = RequestMethod.GET )
	public ResponseEntity<Cliente> find( @PathVariable Integer id ) {
		
		Cliente cat = service.find(id);

		return ResponseEntity.ok().body(cat);
	}
	
	@RequestMapping( method = RequestMethod.POST )
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO objDto ){
		
		Cliente obj = service.fromDTO(objDto);
		//obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping ( value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id ){
		
		Cliente obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();	
	}
	
	@RequestMapping( value = "/{id}" , method = RequestMethod.DELETE )
	public ResponseEntity<Void> delete( @PathVariable Integer id ) {
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping( method = RequestMethod.GET )
	public ResponseEntity<List<ClienteDTO>> findAll() {
		
		List<Cliente> cats = service.findAll();
		List<ClienteDTO> catsDTO = cats.stream().map( obj -> new ClienteDTO(obj)).collect(Collectors.toList()); 
		
		return ResponseEntity.ok().body(catsDTO);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET )
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam( name = "page", defaultValue = "0" ) Integer page,
			@RequestParam( name = "linesPerPage", defaultValue = "24" ) Integer linesPerPage,
			@RequestParam( name = "orderBy", defaultValue = "nome" ) String orderBy,
			@RequestParam( name = "direction", defaultValue = "ASC" ) String direction) {
		
		Page<Cliente> cats = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> catsDTO = cats.map( obj -> new ClienteDTO(obj)); 
		
		return ResponseEntity.ok().body(catsDTO);
	}

}
