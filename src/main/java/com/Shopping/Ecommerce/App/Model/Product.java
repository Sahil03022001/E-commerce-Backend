package com.Shopping.Ecommerce.App.Model;

import com.Shopping.Ecommerce.App.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "product")
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    private String name;
    private int price;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    private int quantity;

    @ManyToOne
    @JoinColumn
    Seller seller;

    @ManyToOne
    @JoinColumn
    private Category category;
}
