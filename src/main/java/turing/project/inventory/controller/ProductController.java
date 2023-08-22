package turing.project.inventory.controller;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import turing.project.inventory.models.Product;
import turing.project.inventory.repository.IProductRepository;

import java.util.List;

/**
 * Se declara la clase como un controlador de la API a través de la anotación -RESTCONTROLLER-
 * Se declara el ENDPOINT para el acceso a los datos
 * Se importan los seter a través de la librería de -LOMBOK-
 * Se configura el cruce de datos entre el servidor del front y el back
 */
@RestController
@RequestMapping(path = "/products")
@Setter
@CrossOrigin("http://localhost:4200")
public class ProductController {
    /**
     * Se declara la intrerfaz correspondiente al repo y se implementa la inyección de dependencias para cargar las instancias
     */
    @Autowired
    private IProductRepository repository;

    /**
     * ENDPOINT-METODO GET para obtener el listado completo desde la base de datos
     * @return
     */
    @GetMapping
    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    /**
     * ENDPOINT-METODO GET para obtener un solo producto a través de su ID
     * @param idProduct
     * @return
     */
    @GetMapping("/{idProduct}")
    public Product getProductById(@PathVariable int idProduct){
        if(repository.findById(idProduct).isPresent()){
            return repository.findById(idProduct).get();
        } else{
            return null;
        }
    }

    /**
     * ENDPOINT-METODO POST para guardar productos en la base de datos
     * @param product
     * @return
     */
    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        return repository.save(product);
    }

    /**
     * ENDPOINT-METODO PUT para realizar actualizaciones a los datos previamente guardados
     * @param product
     * @param idProduct
     * @return
     */
    @PutMapping("/{idProduct}")
    public Product updateProduct(@RequestBody Product product, @PathVariable int idProduct){
        product.setIdProduct(idProduct);
        return repository.save(product);
    }

    /**
     * ENDPOINT-METODO DELETE para borrar registros
     * @param idProduct
     * @return
     */
    @DeleteMapping("/{idProduct}")
    public Integer deleteProduct(@PathVariable int idProduct){
        repository.deleteById(idProduct);
        return idProduct;
    }
}
