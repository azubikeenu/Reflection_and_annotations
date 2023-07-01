package com.azubike.ellipsis.dependency_injection;

import com.azubike.ellipsis.dependency_injection.annotations.Component;

import java.util.List;

@Component
public class ProductRepository {
    public List<Product> getPrice(List<Product> items) {
        for (Product product : items) {
            final double price = (double) Math.round(Math.random() * 30);
            System.out.println("Original price of product " + product.getName() + " is $" + price);
            product.setPrice(price);
        }
        return items;
    }
}
