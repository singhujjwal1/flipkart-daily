package com.flipkart.daily.service;

import com.flipkart.daily.exception.ItemExistsException;
import com.flipkart.daily.exception.ItemNotFoundException;
import com.flipkart.daily.exception.InvalidQuantityException;
import com.flipkart.daily.model.Item;
import com.flipkart.daily.model.CategoryBrandKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * InventoryService class to manage items and their inventory.
 */
public class InventoryService {
    private final Map<CategoryBrandKey, Item> items = new HashMap<>();
    private final Map<CategoryBrandKey, Integer> inventory = new HashMap<>();

    /**
     * Adds an item to the inventory.
     * 
     * @param category - The category of the item.
     * @param brand    - The brand of the item.
     * @param price    - The price of the item.
     * @throws ItemExistsException - If the item already exists in the inventory.
     */
    public void addItem(String category, String brand, double price) throws ItemExistsException {
        CategoryBrandKey key = new CategoryBrandKey(category, brand);
        if (items.containsKey(key)) {
            throw new ItemExistsException("Item already exists for category " + category + " and brand " + brand);
        }
        items.put(key, new Item(category, brand, price));
        inventory.put(key, 0);
    }

    /**
     * Adds inventory for an item.
     * @param category - The category of the item.
     * @param brand    - The brand of the item.
     * @param quantity - The quantity to add.
     * @throws ItemNotFoundException    - If the item does not exist in the inventory.
     * @throws InvalidQuantityException - If the quantity is not a positive integer.
     */
    public void addInventory(String category, String brand, int quantity)
            throws ItemNotFoundException, InvalidQuantityException {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be a positive integer");
        }
        CategoryBrandKey key = new CategoryBrandKey(category, brand);
        if (!items.containsKey(key)) {
            throw new ItemNotFoundException("Item not found for category " + category + " and brand " + brand);
        }
        inventory.put(key, inventory.getOrDefault(key, 0) + quantity);
    }

    /**
     * Retrieves all items in the inventory.
     * 
     * @return A list of all items.
     */
    public List<Item> getAllItems() {
        return new ArrayList<>(items.values());
    }

    /**
     * Retrieves the quantity of a specific item in the inventory.
     * 
     * @param item - The item to check.
     * @return The quantity of the item in the inventory.
     */
    public int getQuantity(Item item) {
        CategoryBrandKey key = new CategoryBrandKey(item.getCategory(), item.getBrand());
        return inventory.getOrDefault(key, 0);
    }
}