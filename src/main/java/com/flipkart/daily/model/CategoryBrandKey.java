package com.flipkart.daily.model;

import java.util.*;

/**
 * This class represents a composite key for a category and brand.
 * It is used to uniquely identify a combination of category and brand in a map.
 */
public class CategoryBrandKey {
    private final String category;
    private final String brand;

    public CategoryBrandKey(String category, String brand) {
        this.category = category;
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryBrandKey that = (CategoryBrandKey) o;
        return Objects.equals(category, that.category) && Objects.equals(brand, that.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, brand);
    }
}