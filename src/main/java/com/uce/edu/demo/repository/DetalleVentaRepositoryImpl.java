package com.uce.edu.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.DetalleVenta;

@Repository
@Transactional
public class DetalleVentaRepositoryImpl implements IDetalleVentaRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(value = TxType.MANDATORY)
	public void insertar(DetalleVenta detalle) {
		// TODO Auto-generated method stub
		this.entityManager.persist(detalle);
	}
	
	@Override 
	public List<DetalleVenta> buscarPorFecha(LocalDateTime fecha) {
		// TODO Auto-generated method stub
		String sql = "SELECT d FROM DetalleVenta d JOIN FETCH d.venta v WHERE v.fecha < :fecha";
		TypedQuery<DetalleVenta> query = this.entityManager.createQuery(sql, DetalleVenta.class);
		query.setParameter("fecha", fecha);
		return query.getResultList();
	}

}
