package com.universidad.patrones.productosservice.repository;

import com.universidad.patrones.productosservice.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // JpaRepository proporciona métodos CRUD básicos:
    // - save(Producto) - guardar o actualizar un producto
    // - findAll() - obtener todos los productos
    // - findById(Long) - buscar producto por ID
    // - deleteById(Long) - eliminar producto por ID
    // - delete(Producto) - eliminar un producto
    // - count() - contar total de productos
}
