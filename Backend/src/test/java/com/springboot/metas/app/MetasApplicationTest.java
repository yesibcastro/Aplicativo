package com.springboot.metas.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.metas.app.controller.CategoriaController;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MetasApplicationTest {

	@Autowired
	 private CategoriaController controller;
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}


}
