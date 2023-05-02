package com.Shopping.Ecommerce.App.Repository;

import com.Shopping.Ecommerce.App.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
}
