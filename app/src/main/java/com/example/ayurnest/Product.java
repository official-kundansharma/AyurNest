package com.example.ayurnest;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int price;
    private int imageResource;
    private String description;

    public Product(String name, int price, int imageResource) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
        this.description = "Detailed description of " + name; // Example description
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getDescription() {
        return description;
    }
}
