package com.flipkart.daily.model;

import java.util.Objects;

/**
 * This class represents an item with a category, brand, and price.
 * It is used to uniquely identify an item in the inventory.
 */
public class Item {
    private final String category;
    private final String brand;
    private final double price;

    public Item(String category, String brand, double price) {
        this.category = category;
        this.brand = brand;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(category, item.category) && Objects.equals(brand, item.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, brand);
    }
}
