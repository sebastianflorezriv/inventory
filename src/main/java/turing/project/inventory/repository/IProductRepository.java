package turing.project.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import turing.project.inventory.models.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {}
