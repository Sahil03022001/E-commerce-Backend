package com.Shopping.Ecommerce.App.Repository;

import com.Shopping.Ecommerce.App.Model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Ordered, Integer> {
}
