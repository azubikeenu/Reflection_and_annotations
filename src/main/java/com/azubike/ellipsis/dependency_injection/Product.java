package com.azubike.ellipsis.dependency_injection;

public class Product {
    private String name;
    private Integer discount;
    private double price;

    public Product() {
    }

    public Product(final String name, final Integer discount) {
        this.name = name;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(final Integer discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", discount=" + discount +
                ", price=" + price +
                '}';
    }
}
