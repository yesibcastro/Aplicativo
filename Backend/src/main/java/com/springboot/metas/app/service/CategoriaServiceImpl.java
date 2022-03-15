package com.springboot.metas.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.metas.app.dao.ICategoriaDao;
import com.springboot.metas.app.entity.Categoria;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

	@Autowired
	private ICategoriaDao categoriaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
		return (List<Categoria>) categoriaDao.findAll();
	}

	@Override
	@Transactional
	public Categoria save(Categoria categoria) {
		System.out.println(categoria);
		return categoriaDao.save(categoria);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Categoria findById(Integer id) {
		return categoriaDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void delete(Categoria categoria) {
		categoriaDao.delete(categoria);
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria findByNombre(String nombre) {
		return categoriaDao.findByNombre(nombre);
	}

}
