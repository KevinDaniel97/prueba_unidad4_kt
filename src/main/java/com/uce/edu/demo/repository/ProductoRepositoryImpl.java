package com.uce.edu.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Producto;

@Repository
@Transactional
public class ProductoRepositoryImpl implements IProductoRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(value = TxType.MANDATORY)
	public void insertar(Producto producto) {
		this.entityManager.persist(producto);
	}
	
	@Override
	@Transactional(value = TxType.MANDATORY)//REQUIRED es por defecto
	public void actualizarStock(String codigoBarras, Integer stock) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Producto p SET p.stock = :stock WHERE p.codigoBarras = :codigoBarras";
		Query query = this.entityManager.createQuery(sql);
		query.setParameter("codigoBarras", codigoBarras);
		query.setParameter("stock", stock);
		query.executeUpdate();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Producto buscar(String codigoBarras) {
		// TODO Auto-generated method stub
		String sql = "SELECT p FROM Producto p WHERE p.codigoBarras = :codigoBarras";
		TypedQuery<Producto> query = this.entityManager.createQuery(sql, Producto.class);
		query.setParameter("codigoBarras", codigoBarras);
		
		Producto producto = null;
		try {producto = query.getSingleResult();
		}catch(Exception e) {}

		return producto;
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Producto consultarStock(String codigoBarras) {
		// TODO Auto-generated method stub
		String sql = "SELECT prod_id, prod_codigo_barras, prod_nombre, prod_categoria, prod_stock,prod_precio "
				+ "FROM producto WHERE prod_codigo_barras = :codigoBarras";
		Query myQuery = this.entityManager.createNativeQuery(sql, Producto.class);
		myQuery.setParameter("codigoBarras", codigoBarras);
		return (Producto) myQuery.getSingleResult();
	}

	@Override
	public List<Producto> consultarTodos() {
		TypedQuery<Producto> myQuery=this.entityManager.createQuery("SELECT p From Producto p",Producto.class);
		return myQuery.getResultList();
	}
	

}
