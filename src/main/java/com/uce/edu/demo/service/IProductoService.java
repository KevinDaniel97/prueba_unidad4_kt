package com.uce.edu.demo.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Producto;

public interface IProductoService {
	public void ingresarProducto(Producto producto);
	public Integer controlarStock(String codigoBarras,Integer cantidad);
	public Producto consultarStock(String codigoBarras);
	public List<Producto> consultarTodos();
}
