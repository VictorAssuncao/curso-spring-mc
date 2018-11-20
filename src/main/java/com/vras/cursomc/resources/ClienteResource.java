package com.vras.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vras.cursomc.domain.Cliente;
import com.vras.cursomc.services.ClienteService;

@RestController
@RequestMapping( value = "clientes" )
public class ClienteResource {
	
	@Autowired
	private ClienteService cliService;

	@RequestMapping( value = "/{id}" , method = RequestMethod.GET )
	public ResponseEntity<Cliente> buscarPorId( @PathVariable Integer id ) {
		
		Cliente cat = cliService.find(id);

		return ResponseEntity.ok().body(cat);
	}

}
