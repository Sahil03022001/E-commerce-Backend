package com.Shopping.Ecommerce.App.Repository;

import com.Shopping.Ecommerce.App.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {

    Seller getSellerByPanNo(String panNo);
}
