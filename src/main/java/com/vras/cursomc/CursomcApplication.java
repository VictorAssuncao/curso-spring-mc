package com.vras.cursomc;

import java.text.SimpleDateFormat;
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
import com.vras.cursomc.domain.ItemPedido;
import com.vras.cursomc.domain.Pagamento;
import com.vras.cursomc.domain.PagamentoComBoleto;
import com.vras.cursomc.domain.PagamentoComCartao;
import com.vras.cursomc.domain.Pedido;
import com.vras.cursomc.domain.Produto;
import com.vras.cursomc.domain.enums.EstadoPagamento;
import com.vras.cursomc.domain.enums.TipoCliente;
import com.vras.cursomc.repositories.CategoriaRepository;
import com.vras.cursomc.repositories.CidadeRepository;
import com.vras.cursomc.repositories.ClienteRepository;
import com.vras.cursomc.repositories.EnderecoRepository;
import com.vras.cursomc.repositories.EstadoRepository;
import com.vras.cursomc.repositories.ItemPedidoRepository;
import com.vras.cursomc.repositories.PagamentoRepository;
import com.vras.cursomc.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Eletronico");
		Categoria cat4 = new Categoria(null, "Isso");
		Categoria cat5 = new Categoria(null, "Categoria 1");
		Categoria cat6 = new Categoria(null, "Categoria 4");
		Categoria cat7 = new Categoria(null, "Categoria 6");
		Categoria cat8 = new Categoria(null, "Todos");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.setProdutos(Arrays.asList(p1,p2,p3));
		cat1.setProdutos(Arrays.asList(p2));
		
		p1.setCategorias(Arrays.asList(cat1));
		p2.setCategorias(Arrays.asList(cat1,cat2));
		p3.setCategorias(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
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
		
		Pedido ped1 = new Pedido(null, new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("30/09/2017 10:32"), cli1, ed1);
		Pedido ped2 = new Pedido(null, new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("10/10/2017 19:35"), cli1, ed2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, 
				new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedido().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}
}
