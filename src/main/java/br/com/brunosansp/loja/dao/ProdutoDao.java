package br.com.brunosansp.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.brunosansp.loja.modelo.Produto;

public class ProdutoDao {
	
	private EntityManager manager;

	public ProdutoDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public void cadastrar(Produto produto) {
		this.manager.persist(produto);
	}
	
	public void atualizar(Produto produto) {
		this.manager.merge(produto);
	}
	
	public void remover(Produto produto) {
		produto = manager.merge(produto);
		this.manager.remove(produto);
	}
	
	public Produto buscarPorId(Long id) {
		return manager.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos() {
		//JPQL
		String jpql = "SELECT p FROM Produto p";
		return manager.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> buscarPeloNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		return manager.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public List<Produto> buscarPeloNomeDaCategoria(String categoria) {
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
		return manager.createQuery(jpql, Produto.class)
				.setParameter("nome", categoria)
				.getResultList();
	}
	
	public BigDecimal buscarPrecoPeloNome(String nome) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		return manager.createQuery(jpql, BigDecimal.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}
}
