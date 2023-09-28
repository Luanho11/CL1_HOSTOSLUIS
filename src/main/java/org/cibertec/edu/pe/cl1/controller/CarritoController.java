package org.cibertec.edu.pe.cl1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.cibertec.edu.pe.cl1.model.CarritoItem;
import org.cibertec.edu.pe.cl1.model.Producto;
import org.cibertec.edu.pe.cl1.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class CarritoController {
    @Autowired
    private ProductoRepository productoRepository;

    private List<CarritoItem> carrito = new ArrayList<>();

    @GetMapping("/")
    public String mostrarProductos(Model model) {
        List<Producto> productos = productoRepository.findAll();
        model.addAttribute("productos", productos);
        model.addAttribute("carrito", carrito);
        return "productos";
    }

    @PostMapping("/agregarAlCarrito")
    public String agregarAlCarrito(@RequestParam Long productoId, @RequestParam int cantidad) {
        Producto producto = productoRepository.findById(productoId).orElse(null);
        if (producto != null && cantidad > 0) {
            CarritoItem item = new CarritoItem();
            item.setProducto(producto);
            item.setCantidad(cantidad);
            carrito.add(item);
        }
        return "redirect:/";
    }

    @GetMapping("/carrito")
    public String mostrarCarrito(Model model) {
        model.addAttribute("carrito", carrito);
        return "carrito";
    }
    
    @GetMapping("/finalizado")
    public String finish(Model model) {
        return "finalizado";
    }

    @PostMapping("/actualizarCarrito")
    public String actualizarCarrito(@RequestParam Map<String, String> requestParams) {
        for (CarritoItem item : carrito) {
            Long productoId = Long.parseLong(requestParams.get("producto_" + item.getProducto().getId()));
            int cantidad = Integer.parseInt(requestParams.get("cantidad_" + item.getProducto().getId()));
            item.setCantidad(cantidad);
        }
        return "redirect:/carrito";
    }
}

