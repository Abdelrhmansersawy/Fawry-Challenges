package org.example.jakarta.product;

public class Product {
    private String name;
    private double price;
    private int id;
    private static int counter= 0;
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        id = ++counter;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    public double getPrice() {
        return price;
    }
}
