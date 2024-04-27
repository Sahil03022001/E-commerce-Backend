package com.Shopping.Ecommerce.App.Repository;

import com.Shopping.Ecommerce.App.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * from product order by price desc limit 5", nativeQuery = true)
    List<Product> find5HighestPrice();

    @Query(value = "select * from product order by price limit 5", nativeQuery = true)
    List<Product> find5LeastPrice();
}
