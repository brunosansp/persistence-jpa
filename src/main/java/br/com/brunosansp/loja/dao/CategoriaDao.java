package br.com.brunosansp.loja.dao;

import javax.persistence.EntityManager;

import br.com.brunosansp.loja.modelo.Categoria;

public class CategoriaDao {
	
	private EntityManager manager;

	public CategoriaDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public void cadastrar(Categoria categoria) {
		this.manager.persist(categoria);
	}
	
	public void atualizar(Categoria categoria) {
		this.manager.merge(categoria);
	}
	
	public void remover(Categoria categoria) {
		categoria = manager.merge(categoria);
		this.manager.remove(categoria);
	}

}
