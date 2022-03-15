package com.springboot.metas.app;

import com.springboot.metas.app.entity.Categoria;

public class Datos {

	
    public static Categoria crearCategoria001() {
        return new Categoria(1, "Finanzas", "Habitos financieros");
    }

    public static Categoria crearCategoria002() {
        return new Categoria(2, "Viajes", "Viajes en avion");
    }
}
