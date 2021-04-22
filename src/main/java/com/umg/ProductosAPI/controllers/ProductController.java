package com.umg.ProductosAPI.controllers;

import com.umg.ProductosAPI.entities.Producto;
import com.umg.ProductosAPI.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/productos")
public class ProductController {

    @Autowired
    ProductRepository repository;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Collection<Producto> getListaProductos(){
        Iterable<Producto> listaProductos = repository.findAll();
        return (Collection<Producto>) listaProductos;
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Producto getProducto(@PathVariable(name = "id") Long id){
        Optional<Producto> producto = repository.findById(id);
        Producto result = null;
        if(producto.isPresent()){
            result = producto.get();
        }
        return result;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Producto createProducto(@RequestBody Producto producto){
        Producto nuevoProducto  = repository.save(producto);
        return nuevoProducto;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteproducto(@PathVariable(name = "id") Long id){

        repository.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Producto updateProduct(@PathVariable(name = "id") Long id , @RequestBody Producto producto){
        Optional<Producto> oProducto = repository.findById(id);
        if(oProducto.isPresent()){
           Producto actual = oProducto.get();
           actual.setId(id);
           actual.setName(producto.getName());
           actual.setPrice(producto.getPrice());
            Producto updatedProduct = repository.save(actual);
            return updatedProduct;
        }
        return null;
    }

}
