package com.springboot.metas.app.service;

import java.util.List;

import com.springboot.metas.app.entity.Categoria;

public interface ICategoriaService {

	public List<Categoria> findAll();
	
	public Categoria save(Categoria categoria);
	
	public Categoria findById(Integer id);
	
	public void delete(Categoria categoria);
	
	public Categoria findByNombre(String nombre);
	
}
