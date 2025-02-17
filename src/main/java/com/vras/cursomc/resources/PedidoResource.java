package com.vras.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vras.cursomc.domain.Pedido;
import com.vras.cursomc.services.PedidoService;

@RestController
@RequestMapping( value = "pedidos" )
public class PedidoResource {
	
	@Autowired
	private PedidoService pedService;

	@RequestMapping( value = "/{id}" , method = RequestMethod.GET )
	public ResponseEntity<Pedido> buscarPorId( @PathVariable Integer id ) {
		
		Pedido cat = pedService.buscar(id);

		return ResponseEntity.ok().body(cat);
	}
}
