package com.springboot.metas.app.entity;



import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoriaTest {

	Categoria categoria;
	@Test
	public void obtenerNombre() {
		categoria = new Categoria(1,"Finanzas","Finanzas Prueba");
		assertEquals("Finanzas", categoria.getNombre());
	}
	
	@Test
	public void obtenerDescripcion() {
		categoria = new Categoria();
		categoria.setId(1);
		categoria.setNombre("Viajes");
		categoria.setDescripcion("Viajes en Avion");
		assertEquals("Viajes en Avion", categoria.getDescripcion());
		
	}
	
}
