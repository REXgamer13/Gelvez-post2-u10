package com.universidad.patrones.productosservice.service;

import com.universidad.patrones.productosservice.domain.Producto;
import com.universidad.patrones.productosservice.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductoService {
    /* Code Smell: campo no final (debería ser inyección por constructor)
    @Autowired
    private ProductoRepository repo; // Code Smell: nombre genérico
    Code Smell: método largo con múltiples responsabilidades (CC alta)
    ANTES (Code Smell: @Autowired en campo, dificulta pruebas)
     */

    // DESPUÉS (inyección por constructor — recomendada por Spring) ya no es repo sino productoRepository
    private final ProductoRepository productoRepository;
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    /*
    public Producto procesarProducto(String nombre, Double p, Integer s, String cat, boolean activo, String proveedor) {
        Producto producto = new Producto();
        ANTES (Code Smell: comparación de cadena vacía con equals)
        if (n == null || n.equals("")) { // Code Smell: usar isBlank()
            throw new IllegalArgumentException("nombre requerido");
        }

        // DESPUÉS (uso correcto de isBlank() — cubre null, "", "  ")
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }


        if (p == null) {
            throw new IllegalArgumentException("precio requerido");
        } else if (p <= 0) {
            throw new IllegalArgumentException("precio invalido");
        } else if (p > 999999) {
            throw new IllegalArgumentException("precio excesivo");
        }
        if (s == null || s < 0) {
            throw new IllegalArgumentException("stock invalido");
        }
        producto.setNombre(nombre);
        producto.setPrecio(p);
        producto.setStock(s);
        // implementar lógica de categoría y proveedor
        return repo.save(producto);
    }
    */

    // Método principal — reducido a orquestación
    public Producto procesarProducto(String nombre, Double precio, Integer stock) {
        validarDatos(nombre, precio, stock);
        Producto producto = new Producto();
        producto.setNombre(nombre.strip());
        producto.setPrecio(precio);
        producto.setStock(stock);
        return productoRepository.save(producto);
    }
    // Método extraído — validación separada (CC reducida)
    private void validarDatos(String nombre, Double precio, Integer stock)
    {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (precio == null || precio <= 0)
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        if (precio > 999999)
            throw new IllegalArgumentException("El precio excede el máximo permitido");
        if (stock == null || stock < 0)
            throw new IllegalArgumentException("El stock no puede ser negativo");
    }


    public List<Producto> listar() { return productoRepository.findAll(); }

    /* Bug: no lanza excepción si el producto no existe
    public Producto buscar(Long id) {
        return repo.findById(id).orElse(null); // Bug: retorna null
    }
     */

    // DESPUÉS (corrección: lanzar excepción con mensaje descriptivo)
    public Producto buscar(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Producto no encontrado: " + id));
    }


}