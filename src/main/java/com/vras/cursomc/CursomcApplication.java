package com.vras.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vras.cursomc.domain.Categoria;
import com.vras.cursomc.domain.Cidade;
import com.vras.cursomc.domain.Cliente;
import com.vras.cursomc.domain.Endereco;
import com.vras.cursomc.domain.Estado;
import com.vras.cursomc.domain.Produto;
import com.vras.cursomc.domain.enums.TipoCliente;
import com.vras.cursomc.repositories.CategoriaRepository;
import com.vras.cursomc.repositories.CidadeRepository;
import com.vras.cursomc.repositories.ClienteRepository;
import com.vras.cursomc.repositories.EnderecoRepository;
import com.vras.cursomc.repositories.EstadoRepository;
import com.vras.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.setProdutos(Arrays.asList(p1,p2,p3));
		cat1.setProdutos(Arrays.asList(p2));
		
		p1.setCategorias(Arrays.asList(cat1));
		p2.setCategorias(Arrays.asList(cat1,cat2));
		p3.setCategorias(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		
		est1.setCidades(Arrays.asList(cid1));
		est2.setCidades(Arrays.asList(cid2));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "09808212398", TipoCliente.PESSOA_FISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("99882399","99882559"));
		
		Endereco ed1 = new Endereco(null, "Rua Flores", "300", "AP 303", "Jardim", "65043434", cli1, cid1);
		Endereco ed2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "65043222", cli1, cid2);
		
		cli1.setEnderecos(Arrays.asList(ed1,ed2));
		
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(ed1,ed2));
	}
}
