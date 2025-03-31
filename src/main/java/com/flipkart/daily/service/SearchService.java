package com.flipkart.daily.service;

import com.flipkart.daily.utils.FilterCriteria;
import com.flipkart.daily.utils.SortCriteria;
import com.flipkart.daily.utils.SortDirection;
import com.flipkart.daily.model.InventoryResultItem;
import com.flipkart.daily.model.Item;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SearchService is responsible for searching items in the inventory based on filter and sort criteria.
 */
public class SearchService {
    private final InventoryService inventoryService;

    public SearchService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Search for items in the inventory based on filter and sort criteria.
     * @param filter - Filter criteria to filter items.
     * @param sort - Sort criteria to sort the filtered items.
     * @return List of InventoryResultItem matching the filter and sorted as per the sort criteria.
     */
    public List<InventoryResultItem> searchItems(FilterCriteria filter, SortCriteria sort) {
        List<Item> allItems = inventoryService.getAllItems();
        List<InventoryResultItem> results = allItems.stream()
                .filter(item -> passesFilter(item, filter))
                .map(item -> new InventoryResultItem(
                        item.getBrand(),
                        item.getCategory(),
                        item.getPrice(),
                        inventoryService.getQuantity(item)))
                .collect(Collectors.toList());
        sortResults(results, sort);
        return results;
    }

    /**
     * Check if an item passes the filter criteria.
     * @param item - The item to check.
     * @param filter - The filter criteria.
     * @return true if the item passes the filter, false otherwise.
     */
    private boolean passesFilter(Item item, FilterCriteria filter) {
        if (filter.getBrands() != null && !filter.getBrands().isEmpty()
                && !filter.getBrands().contains(item.getBrand())) {
            return false;
        }
        if (filter.getCategories() != null && !filter.getCategories().isEmpty()
                && !filter.getCategories().contains(item.getCategory())) {
            return false;
        }

        Double minPrice = filter.getMinPrice();
        Double maxPrice = filter.getMaxPrice();
        if (minPrice != null && maxPrice != null && minPrice > maxPrice) {
            Double temp = minPrice;
            minPrice = maxPrice;
            maxPrice = temp;
        }

        double price = item.getPrice();
        if (minPrice != null && price < minPrice) {
            return false;
        }
        if (maxPrice != null && price > maxPrice) {
            return false;
        }
        return true;
    }

    /**
     * Sort the results based on the sort criteria.
     * @param results - The list of results to sort.
     * @param sort - The sort criteria.
     * If sort is null, the results are sorted by price in ascending order.
     */
    private void sortResults(List<InventoryResultItem> results, SortCriteria sort) {
        if (sort == null) {
            results.sort(Comparator.comparingDouble(InventoryResultItem::getPrice));
            return;
        }

        Comparator<InventoryResultItem> comparator;
        switch (sort.getSortBy()) {
            case PRICE:
                comparator = Comparator.comparingDouble(InventoryResultItem::getPrice);
                break;
            case QUANTITY:
                comparator = Comparator.comparingInt(InventoryResultItem::getQuantity);
                break;
            default:
                comparator = Comparator.comparingDouble(InventoryResultItem::getPrice);
        }

        if (sort.getDirection() == SortDirection.DESC) {
            comparator = comparator.reversed();
        }

        results.sort(comparator);
    }
}