package com.parquesoftti.panaderia.service;

import com.parquesoftti.panaderia.dto.BuscarProductoDTO;
import com.parquesoftti.panaderia.dto.ProductoDTO;
import com.parquesoftti.panaderia.dto.ProductoDTORespuesta;
import com.parquesoftti.panaderia.model.Producto;
import com.parquesoftti.panaderia.repository.ProductoRepository;
import com.parquesoftti.panaderia.exception.ProductoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los productos como DTOs
    public List<ProductoDTORespuesta> getAllProductos() {
        return productoRepository.findAll().stream()
                .map(this::convertirADTORespuesta)
                .collect(Collectors.toList());
    }

    // Búsqueda parcial: contiene el texto dado
    public List<ProductoDTORespuesta> getProductosPorNombreParcial(BuscarProductoDTO dto) {
        List<Producto> productos = productoRepository.findByNombreContainingIgnoreCase(dto.getNombre());
        if (productos.isEmpty()) {
            throw new ProductoNotFoundException("No se encontraron productos con nombre: " + dto.getNombre());
        }
        return productos.stream()
                .map(this::convertirADTORespuesta)
                .collect(Collectors.toList());
    }

    // Búsqueda exacta: nombre completo
    public ProductoDTORespuesta getProductoPorNombreExacto(BuscarProductoDTO dto) {
        return productoRepository.findByNombre(dto.getNombre())
                .map(this::convertirADTORespuesta)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con nombre: " + dto.getNombre()));
    }

    private ProductoDTORespuesta convertirADTORespuesta(Producto producto) {
        return new ProductoDTORespuesta(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio().doubleValue(),
                producto.getStock()
        );
    }

    // Guardar nuevo producto
    @Transactional
    public ProductoDTORespuesta save(ProductoDTO productoDTO) {
        Producto producto = convertirAEntidad(productoDTO);
        Producto guardado = productoRepository.save(producto);
        return convertirADTORespuesta(guardado);
    }

    // Eliminar producto por ID
    @Transactional
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    // Buscar producto por ID como DTORespuesta
    @Transactional(readOnly = true)
    public ProductoDTORespuesta findById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con ID: " + id));
        return convertirADTORespuesta(producto);
    }

    // Actualizar producto por ID
    @Transactional
    public ProductoDTORespuesta update(Long id, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con ID: " + id));

        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(java.math.BigDecimal.valueOf(productoDTO.getPrecio()));
        producto.setStock(productoDTO.getStock());

        Producto guardado = productoRepository.save(producto);
        return convertirADTORespuesta(guardado);
    }

    // Métodos privados de conversión

    private Producto convertirAEntidad(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(java.math.BigDecimal.valueOf(dto.getPrecio()));
        producto.setStock(dto.getStock());
        return producto;
    }

}