package com.flipkart.daily.model;

/**
 * Represents an item in the inventory with its brand, category, price, and quantity.
 * This class is used to store the result of inventory queries.
 */
public class InventoryResultItem {
    private final String brand;
    private final String category;
    private final double price;
    private final int quantity;

    public InventoryResultItem(String brand, String category, double price, int quantity) {
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %d", brand, category, quantity);
    }
}
