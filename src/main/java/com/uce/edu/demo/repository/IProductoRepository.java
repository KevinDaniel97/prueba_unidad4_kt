package com.uce.edu.demo.repository;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Producto;

public interface IProductoRepository {
	public void insertar(Producto producto);
	public void actualizarStock(String codigoBarras, Integer stock);
	public Producto buscar(String codigoBarras);
	public Producto consultarStock(String codigoBarras);
	public List<Producto> consultarTodos();

}
