package com.springboot.metas.app.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;

import com.springboot.metas.app.entity.Categoria;
import com.springboot.metas.app.service.ICategoriaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class CategoriaController {
	

	@Autowired
	private ICategoriaService categoriaService;
	
	@GetMapping("/categorias")
	@ResponseStatus(OK)
	public List<Categoria> listarCategorias() {
		return categoriaService.findAll();
	}
	
	@GetMapping("/categorias/{id}")
	public ResponseEntity<?> listarPorId(@PathVariable Integer id) {
		Categoria categoria = null;
		Map<String, Object> response = new HashMap<>();
		try {
			categoria = categoriaService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, INTERNAL_SERVER_ERROR);
		}
		
		if(categoria == null) {
			response.put("mensaje", "La categoria ".concat(id.toString()).concat(" no existe en la BD"));
			return new ResponseEntity<Map<String, Object>>(response, NOT_FOUND);
		}
		return new ResponseEntity<Categoria>(categoria, OK);
	}
	
	@PostMapping("/categorias")
	public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			categoriaService.save(categoria);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La categoria ha sido creada con exito!!");
		response.put("categoria", categoria);
		return new ResponseEntity<Map<String,Object>>(response, CREATED);
	}
	
	@PutMapping("/categorias/{id}")
	public ResponseEntity<?> actualizarCategoria(@RequestBody Categoria categoria, @PathVariable Integer id) {
		
		Categoria categoriaActual = categoriaService.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(categoriaActual == null) {
			response.put("mensaje", "Error: No se pudo editar, la categoria: ".concat(id.toString()).concat(" no existe en la BD"));
			return new ResponseEntity<Map<String,Object>>(response, NOT_FOUND);
		}
		try {
		categoriaActual.setNombre(categoria.getNombre());
		categoriaActual.setDescripcion(categoria.getDescripcion());
		categoriaService.save(categoriaActual);
		}catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar la categoria en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La categoria ha sido actualizada con exito!!");
		response.put("categoria", categoriaActual);
		return new ResponseEntity<Map<String, Object>>(response, CREATED);
	}

	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Map<String,Object> response = new HashMap<>();
		Categoria categoria = null;
		try {
			categoria = categoriaService.findById(id); 
			categoriaService.delete(categoria);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la categoria de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La categoria eliminado con exito!!");
		return new ResponseEntity<Map<String,Object>>(response, NO_CONTENT);

	}
	
	@GetMapping("/categorias/nombre/{nombre}")
	public ResponseEntity<?> listarPorNombre(@PathVariable String nombre) {
		Categoria categoria = null;
		Map<String, Object> response = new HashMap<>();
		try {
			categoria = categoriaService.findByNombre(nombre);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, INTERNAL_SERVER_ERROR);
		}
		
		if(categoria == null) {
			response.put("mensaje", "La categoria ".concat(nombre).concat(" no existe en la BD"));
			return new ResponseEntity<Map<String, Object>>(response, NOT_FOUND);
		}
		return new ResponseEntity<Categoria>(categoria, OK);
	}

}
