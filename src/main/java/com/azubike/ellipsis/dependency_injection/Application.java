package com.azubike.ellipsis.dependency_injection;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        ApplicationContext<AppConfig> applicationContext = new ApplicationContext<>(AppConfig.class);
        ProductService productService = applicationContext.getBean(ProductService.class);
        List<Product> items = List.of(new Product("Tomatoes", 12),
                new Product("Mangoes", 23), new Product("Oranges", 10));
        productService.getFinalPrice(items);

    }
}
