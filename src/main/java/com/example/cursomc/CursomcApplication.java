package com.example.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Cidade;
import com.example.cursomc.domain.Cliente;
import com.example.cursomc.domain.Endereco;
import com.example.cursomc.domain.Estado;
import com.example.cursomc.domain.Produto;
import com.example.cursomc.domain.enums.TipoCliente;
import com.example.cursomc.repositories.CategoriaRepository;
import com.example.cursomc.repositories.CidadeRepository;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.repositories.EnderecoRepository;
import com.example.cursomc.repositories.EstadoRepository;
import com.example.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoria;
	
	@Autowired
	private ProdutoRepository produto;
	
	@Autowired
	private CidadeRepository cidade;
	
	@Autowired
	private EstadoRepository estado;
	
	@Autowired
	private EnderecoRepository endereco;
	
	@Autowired
	private ClienteRepository cliente;
	
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
		 
		 cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		 cat2.getProdutos().addAll(Arrays.asList(p2));
		 
		 p1.getCategorias().addAll(Arrays.asList(cat1));
		 p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		 p3.getCategorias().addAll(Arrays.asList(cat1));
		 
		 categoria.saveAll(Arrays.asList(cat1,cat2));
			
		 produto.saveAll(Arrays.asList(p1,p2,p3));
		 
		 Estado est1 = new Estado(null, "Minas Gerais");
		 Estado est2 = new Estado(null, "São Paulo");
		 
		 Cidade c1 = new Cidade(null, "Uberlândia", est1);
		 Cidade c2 = new Cidade(null, "São Paulo", est2);
		 Cidade c3 = new Cidade(null, "Campinas", est2);
		 
		 est1.getCidades().addAll(Arrays.asList(c1));
		 est2.getCidades().addAll(Arrays.asList(c2,c3));
		 
		 estado.saveAll(Arrays.asList(est1, est2));
		 
		 cidade.saveAll(Arrays.asList(c1,c2,c3));
		 
		 Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		 
		 cli1.getTelefones().add("27363323");
		 cli1.getTelefones().add("93838393");
		 
		 Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		 
		 Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		 
		 cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		 
		 cliente.saveAll(Arrays.asList(cli1));

		 endereco.saveAll(Arrays.asList(e1,e2));
	}

}
