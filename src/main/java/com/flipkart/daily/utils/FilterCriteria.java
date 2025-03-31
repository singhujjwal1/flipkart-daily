package com.flipkart.daily.utils;

import java.util.Set;

/**
 * FilterCriteria class to encapsulate the filtering criteria for products.
 * It includes fields for brands, categories, and price range.
 */
public class FilterCriteria {
    private Set<String> brands;
    private Set<String> categories;
    private Double minPrice;
    private Double maxPrice;

    public Set<String> getBrands() {
        return brands;
    }

    public void setBrands(Set<String> brands) {
        this.brands = brands;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }
}