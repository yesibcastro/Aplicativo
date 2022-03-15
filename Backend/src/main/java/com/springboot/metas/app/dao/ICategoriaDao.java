package com.springboot.metas.app.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.metas.app.entity.Categoria;

@Repository
public interface ICategoriaDao extends CrudRepository<Categoria, Integer>{

	public Categoria findByNombre(String nombre);
	
}
