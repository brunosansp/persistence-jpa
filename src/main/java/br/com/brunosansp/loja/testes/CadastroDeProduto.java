package br.com.brunosansp.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.brunosansp.loja.dao.CategoriaDao;
import br.com.brunosansp.loja.dao.ProdutoDao;
import br.com.brunosansp.loja.modelo.Categoria;
import br.com.brunosansp.loja.modelo.Produto;
import br.com.brunosansp.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastrarProduto();
		
		EntityManager manager = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(manager);
		
		Produto produto = produtoDao.buscarPorId(1l);
		System.out.println(produto.getPreco());
		
		List<Produto> todosOsProdutos = produtoDao.buscarTodos();
		todosOsProdutos.forEach(cadaProduto -> System.out.println(cadaProduto.getNome()));
		
		List<Produto> todosOsProdutosPeloNome = produtoDao.buscarPeloNome("Motorola Edge 30 Fusion");
		todosOsProdutosPeloNome.forEach(cadaProdutoPeloNome -> System.out.println(cadaProdutoPeloNome.getNome()));
		
		List<Produto> todosOsProdutosPeloNomeDaCategoria = produtoDao.buscarPeloNomeDaCategoria("CELULARES");
		todosOsProdutosPeloNomeDaCategoria.forEach(cadaProdutoPelaCategoria -> System.out.println(cadaProdutoPelaCategoria.getNome()));
		
		BigDecimal precoDoProdutoPeloNome = produtoDao.buscarPrecoPeloNome("Motorola Edge 30 Fusion");
		System.out.print("Preço do produto: R$" + precoDoProdutoPeloNome);
	}

	private static void cadastrarProduto() {
		Categoria categoria = new Categoria("CELULARES");
		Produto celular = new Produto(
				"Motorola Edge 30 Fusion",
				"Smartphone",
				new BigDecimal("2.499"),
				categoria
				);
		
		EntityManager manager = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(manager);
		CategoriaDao categoriaDao = new CategoriaDao(manager);
		
		manager.getTransaction().begin();
		
		categoriaDao.cadastrar(categoria);
		produtoDao.cadastrar(celular);
		
		manager.getTransaction().commit();
		manager.close();
	}

}
