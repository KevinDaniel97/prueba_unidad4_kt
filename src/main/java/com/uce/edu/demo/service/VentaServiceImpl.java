package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IDetalleVentaRepository;
import com.uce.edu.demo.repository.IProductoRepository;
import com.uce.edu.demo.repository.IVentaRepository;
import com.uce.edu.demo.repository.modelo.DetalleVenta;
import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoRealizarVenta;
import com.uce.edu.demo.repository.modelo.Venta;
import com.uce.edu.demo.repository.modelo.VentaReporte;

@Service
public class VentaServiceImpl implements IVentaService {

	@Autowired
	private IVentaRepository ventaRepository;
	@Autowired
	private IProductoService productoService;
	@Autowired
	private IProductoRepository productoRepository;
	@Autowired
	private IDetalleVentaRepository detalleVentaRepository;
	
	@Override
	@Transactional(value = TxType.REQUIRED)
	public void realizarVenta(String numeroVenta, String cedulaCliente, List<ProductoRealizarVenta> productos) {
		// TODO Auto-generated method stub
		Venta venta = new Venta();
		venta.setCedulaCliente(cedulaCliente);
		venta.setNumero(numeroVenta);

		//Se crea una lista con todos los detalles por producto a comprar
		List<DetalleVenta> listaDetalle=productos.stream().map(prodTo -> { //Consumer
			
			//Control de cantidad de venta de un producto
			Integer cantidadComprada =productoService.controlarStock(prodTo.getCodigoBarras(), prodTo.getCantidad());
			Producto prod = this.productoRepository.buscar(prodTo.getCodigoBarras());
			
			DetalleVenta detalle = new DetalleVenta();
			detalle.setProducto(prod);
			detalle.setCantidad(cantidadComprada);
			detalle.setSubtotal(new BigDecimal(cantidadComprada).multiply(prod.getPrecio()));
			detalle.setPrecioUnitario(prod.getPrecio());
			detalle.setVenta(venta);
			
			return detalle;
			
		}).collect(Collectors.toList());

		//Suma todos los subtotales de los detalles de una Venta
		BigDecimal totalVenta = new BigDecimal(0);
		for(DetalleVenta d: listaDetalle) {
			totalVenta= totalVenta.add(d.getSubtotal());
		}
		
		venta.setTotalVenta(totalVenta);
		venta.setFecha(LocalDateTime.now());
		venta.setDetalles(listaDetalle);
		this.ventaRepository.insertar(venta);
	}
	
	@Override
	public List<VentaReporte> reporteVentas(LocalDateTime fecha, String categoria, Integer cantidad) {
		// TODO Auto-generated method stub
		List<DetalleVenta> ventasPorFecha = this.detalleVentaRepository.buscarPorFecha(fecha);
		
		List<VentaReporte> reporteFecha = ventasPorFecha.stream().map(det -> {
			VentaReporte ventaR = new VentaReporte();	
			String categoriaDB = det.getProducto().getCategoria();
			Integer cantidadDB = det.getCantidad();
			
			if (categoriaDB.equals(categoria) && cantidad > cantidadDB){
				ventaR.setCodigoBarras(det.getProducto().getCodigoBarras());
				ventaR.setNombreProducto(det.getProducto().getNombre());
				ventaR.setPrecioUnitario(det.getPrecioUnitario());
				ventaR.setCantidad(det.getCantidad());
				ventaR.setSubTotal(det.getSubtotal());
				return ventaR;
			}
			return ventaR;
				
		}).collect(Collectors.toList());
		return reporteFecha;
	}

}
