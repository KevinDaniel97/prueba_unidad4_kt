package com.uce.edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoRealizarVenta;
import com.uce.edu.demo.repository.modelo.Venta;
import com.uce.edu.demo.service.IProductoService;
import com.uce.edu.demo.service.IVentaService;

@Controller
@RequestMapping("/supermaxi")
public class SupermercadoController {

	@Autowired
	private IProductoService iProductoService;
	@Autowired
	private IVentaService iVentaService;

	@GetMapping("/buscar")
	public String buscarTodos(Model modelo){
	List<Producto> lista=this.iProductoService.consultarTodos();
	modelo.addAttribute("productos", lista);
	return "vistaListaProd";
	}
	
	@GetMapping("/buscarUno/{idProducto}")
	public String buscarEmpleado(@PathVariable("idProducto") String codigoBarras, Model modelo) {
	System.out.println("EL ID: "+codigoBarras);
	Producto per=this.iProductoService.consultarStock(codigoBarras);
	modelo.addAttribute("persona", per);
	return "vistaProd";
	}
	
	@PostMapping("/insertar")
	public String insertarProducto(Producto producto) {
		this.iProductoService.ingresarProducto(producto);
		return "redirect:/productos/buscar";
	}

    
	
	@PostMapping("/relaizarVenta")
	public String realizarVenta(Venta venta) {
		//this.iVentaService.realizarVenta(venta.getNumero(), venta.getCedulaCliente());
		return "redirect:/productos/buscar";
	}
	
	@GetMapping("/consultarStok")
	public String consultarStok(@PathVariable("idProducto") String codigoBarras, Model modelo){
		List<Producto> listaConsulta=(List<Producto>) this.iProductoService.consultarStock(codigoBarras);
		modelo.addAttribute("productos", listaConsulta);
		return "redirect:/productos/buscar";
	}
	
	
	@GetMapping("/reporteVentas")
	public String reporteVentas(@PathVariable("idProducto") String codigoBarras, Model modelo){
	List<Producto> lista=this.iProductoService.consultarTodos();
	modelo.addAttribute("productos", lista);
	return "vistaListaProd";
	}
}
