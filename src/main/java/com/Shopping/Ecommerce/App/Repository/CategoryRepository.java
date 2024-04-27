package com.Shopping.Ecommerce.App.Repository;

import com.Shopping.Ecommerce.App.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT * FROM Category WHERE name = :name", nativeQuery = true)
    Category getCategoryByName(String name);
}
