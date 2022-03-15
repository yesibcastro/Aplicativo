package com.springboot.metas.app.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.metas.app.Datos;
import com.springboot.metas.app.entity.Categoria;
import com.springboot.metas.app.service.CategoriaServiceImpl;

@WebMvcTest(CategoriaController.class)
public class CategoriaControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CategoriaServiceImpl categoriaService;
	
	ObjectMapper objectMapper;
	
	@BeforeEach
	public void setUp() {
		objectMapper = new ObjectMapper();
	}
	
	@Test
	public void testFindById() throws Exception {
		when(categoriaService.findById(1)).thenReturn(Datos.crearCategoria001());
		
		mvc.perform(get("/api/categorias/1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.nombre").value("Finanzas"))
			.andExpect(jsonPath("$.descripcion").value("Habitos financieros"));
		
		verify(categoriaService).findById(1);
	}
	
	@Test
	public void testFindByNombre() throws Exception {
		when(categoriaService.findByNombre("Viajes")).thenReturn(Datos.crearCategoria002());
		
		mvc.perform(get("/api/categorias/nombre/Viajes").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.nombre").value("Viajes"))
			.andExpect(jsonPath("$.descripcion").value("Viajes en avion"));
		
		verify(categoriaService).findByNombre("Viajes");
	}
	@Test
	public void testAgregarCategoria() throws Exception {
		Categoria categoria = new Categoria(null,"Ejercicio","Dos horas de ejercicio");
		when(categoriaService.save(any())).then(invocation ->{
			Categoria c = invocation.getArgument(0);
	            c.setId(3);
	            return c;
	        });
		mvc.perform(post("/api/categorias").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(categoria)))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensaje").value("La categoria ha sido creada con exito!!"))
		.andExpect(jsonPath("$.categoria.nombre").value(categoria.getNombre()));
	}
	
	@Test
    void testListarCategorias() throws Exception {
        // Given
        List<Categoria> categorias = Arrays.asList(Datos.crearCategoria001(),
        		Datos.crearCategoria002()
        );
        when(categoriaService.findAll()).thenReturn(categorias);

        // When
        mvc.perform(get("/api/categorias").contentType(MediaType.APPLICATION_JSON))
        // Then
        .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nombre").value("Finanzas"))
                .andExpect(jsonPath("$[1].nombre").value("Viajes"))
                .andExpect(jsonPath("$[0].descripcion").value("Habitos financieros"))
                .andExpect(jsonPath("$[1].descripcion").value("Viajes en avion"))
                .andExpect(content().json(objectMapper.writeValueAsString(categorias)));

        verify(categoriaService).findAll();
    }
	
	@Test
	public void testEliminarCategoria() throws Exception {
		when(categoriaService.findById(1)).thenReturn(Datos.crearCategoria001());
		
		mvc.perform(delete("/api/categorias/1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.mensaje").value("La categoria eliminado con exito!!"));
		
		verify(categoriaService).findById(1);
	}
	
	@Test
	public void testActualizarCategoria() throws Exception {
		when(categoriaService.findById(1)).thenReturn(Datos.crearCategoria001());
		
		Datos.crearCategoria001().setNombre("Prueba");
		Datos.crearCategoria001().setDescripcion("Prueba");

		mvc.perform(put("/api/categorias/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(Datos.crearCategoria001())))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensaje").value("La categoria ha sido actualizada con exito!!"));
	}
	
	
	
	
			
	
	
	
}
