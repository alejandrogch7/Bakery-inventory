package com.parquesoftti.panaderia.controller;

import com.parquesoftti.panaderia.dto.BuscarProductoDTO;
import com.parquesoftti.panaderia.dto.ProductoDTO;
import com.parquesoftti.panaderia.dto.ProductoDTORespuesta;
import com.parquesoftti.panaderia.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Obtener todos los productos como DTOs
    @GetMapping
    public ResponseEntity<List<ProductoDTORespuesta>> getAllProductos() {
        List<ProductoDTORespuesta> productos = productoService.getAllProductos();
        return ResponseEntity.ok(productos);
    }

    @PostMapping("/buscar")
    public ResponseEntity<List<ProductoDTORespuesta>> getProductosPorNombreParcial(@RequestBody BuscarProductoDTO dto) {
        List<ProductoDTORespuesta> productos = productoService.getProductosPorNombreParcial(dto);
        return ResponseEntity.ok(productos);
    }

    @PostMapping("/exact")
    public ResponseEntity<ProductoDTORespuesta> getProductoPorNombreExacto(@RequestBody BuscarProductoDTO dto) {
        ProductoDTORespuesta respuesta = productoService.getProductoPorNombreExacto(dto);
        return ResponseEntity.ok(respuesta);
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<ProductoDTORespuesta> createProducto(@RequestBody ProductoDTO productoDTO) {
        ProductoDTORespuesta nuevoProducto = productoService.save(productoDTO);
        return ResponseEntity.ok(nuevoProducto);
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTORespuesta> getProductoById(@PathVariable Long id) {
        ProductoDTORespuesta respuesta = productoService.findById(id);
        return ResponseEntity.ok(respuesta);
    }

    // Actualizar producto por ID
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTORespuesta> updateProducto(
            @PathVariable Long id,
            @RequestBody ProductoDTO productoDTO) {
        ProductoDTORespuesta respuesta = productoService.update(id, productoDTO);
        return ResponseEntity.ok(respuesta);
    }

    // Eliminar producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}