package com.springboot.metas.app.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.metas.app.dao.ICategoriaDao;
import com.springboot.metas.app.entity.Categoria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CategoriaServiceImplTest {

	@Mock
	private ICategoriaDao categoriaDao;
	@InjectMocks
    CategoriaServiceImpl categoriaService;
	
	Categoria[] categoria;

	@BeforeEach
	void setUp() {

		categoria = new Categoria[] { 
				new Categoria(1, "Finanzas", "Prueba"), 
				new Categoria(2, "Viajes", "Prueba") };
	}

	@Test
	void listarCategorias() {

		when(categoriaDao.findAll()).thenReturn(Arrays.asList(categoria[0], categoria[1]));
		List<Categoria> listaCategoria = categoriaService.findAll();
		assertEquals(2, listaCategoria.size());
	}
	
	@Test
    void categoriaPorId() {
		
        when(categoriaDao.findById(1)).thenReturn(Optional.of(categoria[0]));
        
        Optional<Categoria> categoria = Optional.ofNullable(categoriaService.findById(1));
     
        assertTrue(categoria.isPresent());
        assertEquals(1, categoria.orElseThrow().getId());
        assertEquals("Finanzas", categoria.get().getNombre());
    }
	
	@Test
    void categoriaPorNombre() {

        when(categoriaDao.findByNombre("Viajes")).thenReturn(categoria[1]);
        Categoria categoria = categoriaService.findByNombre("Viajes");

        
        assertEquals(2, categoria.getId());
        assertEquals("Viajes", categoria.getNombre());
    }
	
	@Test
    void guardarCategoria() {
        
        Categoria nuevaCategoria = new Categoria(null, "Ejercicio", "Prueba");

        when(categoriaDao.save(any(Categoria.class))).then(new Answer<Categoria>(){

            Integer secuencia = 3;

   
			@Override
			public Categoria answer(InvocationOnMock invocation) throws Throwable {
			Categoria cat = invocation.getArgument(0);
			cat.setId(secuencia++);
			
				return cat;
			}
        });

        // When
        categoriaService.save(nuevaCategoria);

        // Then
        assertNotNull(nuevaCategoria.getId());
        assertEquals(3, nuevaCategoria.getId());
        assertEquals("Ejercicio", nuevaCategoria.getNombre());

        verify(categoriaDao).save(any(Categoria.class));
    }
	
	@Test
    void eliminarCategoria() {
		
		   Categoria nuevaCategoria = new Categoria(3, "Ejercicio", "Prueba");
    
	        categoriaService.delete(nuevaCategoria);
	        verify(categoriaDao).delete(nuevaCategoria);
    }
}
