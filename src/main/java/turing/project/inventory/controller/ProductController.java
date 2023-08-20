package turing.project.inventory.controller;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import turing.project.inventory.models.Product;
import turing.project.inventory.repository.IProductRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
@Setter
public class ProductController {

    @Autowired
    private IProductRepository repository;

    @GetMapping
    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    @GetMapping("/{idProduct}")
    public Product getProductById(@PathVariable int idProduct){
        if(repository.findById(idProduct).isPresent()){
            return new Product();
        } else{
            return repository.findById(idProduct).get();
        }
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        return repository.save(product);
    }

    @PutMapping("/{idProduct}")
    public Product updateProduct(@RequestBody Product product, @PathVariable int idProduct){
        product.setIdProduct(idProduct);
        return repository.save(product);
    }

    @DeleteMapping("/{idProduct}")
    public String deleteProduct(@PathVariable int idProduct){
        repository.deleteById(idProduct);
        return "Ok";
    }
}
