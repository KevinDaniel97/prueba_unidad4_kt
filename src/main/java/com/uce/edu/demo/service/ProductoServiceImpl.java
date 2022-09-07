package com.uce.edu.demo.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IProductoRepository;
import com.uce.edu.demo.repository.modelo.Producto;



@Service
public class ProductoServiceImpl implements IProductoService {

	private static Logger log = Logger.getLogger(ProductoServiceImpl.class);	
	
	@Autowired
	private IProductoRepository productoRepository;
	
	@Override
	@Transactional(value = TxType.REQUIRED)
	public void ingresarProducto(Producto producto) {
		
		Producto productoDB = this.productoRepository.buscar(producto.getCodigoBarras());
		if(productoDB==null) {
			log.info(producto.getNombre());
			this.productoRepository.insertar(producto);
		}else {
			log.info(producto.getNombre());
			Integer stockFinal =productoDB.getStock()+producto.getStock();
			this.productoRepository.actualizarStock(producto.getCodigoBarras(),stockFinal);
		}	
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public Integer controlarStock(String codigoBarras, Integer cantidad) {
		// TODO Auto-generated method stub
		Producto productoDB=this.productoRepository.buscar(codigoBarras);
		Integer stockActual = productoDB.getStock();
		Integer cantidadComprada = cantidad;
		if(productoDB==null || stockActual==0) {
			throw new RuntimeException();
		}
		else if(cantidad<=stockActual) {
			stockActual = stockActual-cantidad;
			this.productoRepository.actualizarStock(codigoBarras, stockActual);
		}else {
			cantidadComprada = stockActual;
			stockActual = stockActual-stockActual;//0
			this.productoRepository.actualizarStock(codigoBarras, stockActual);
		}

		return cantidadComprada;
	}
	
	@Override
	public Producto consultarStock(String codigoBarras) {
		return this.productoRepository.consultarStock(codigoBarras);
	}

	@Override
	public List<Producto> consultarTodos() {
		// TODO Auto-generated method stub
		return this.productoRepository.consultarTodos();
	}

}
