package com.azubike.ellipsis.dependency_injection;

import com.azubike.ellipsis.dependency_injection.annotations.Autowired;
import com.azubike.ellipsis.dependency_injection.annotations.Component;

import java.util.List;

@Component
public class ProductService {
    @Autowired
    final private ProductRepository productRepository = new ProductRepository();

    public List<Product> getFinalPrice(List<Product> items) {
        final List<Product> productList = productRepository.getPrice(items);
        for (Product product : productList) {
            product.setPrice(product.getPrice() * (100 - product.getDiscount()) / 100);
            System.out.println("Price of " +  product.getName() + " after discount of " + product.getDiscount() + " is $" + product.getPrice());
        }
        return productList;
    }
}
