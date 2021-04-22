package com.umg.ProductosAPI.repositories;

import com.umg.ProductosAPI.entities.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository <Producto, Long> {
}
