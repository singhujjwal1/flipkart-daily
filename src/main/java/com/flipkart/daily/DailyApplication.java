package com.flipkart.daily;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.flipkart.daily.utils.FilterCriteria;
import com.flipkart.daily.utils.SortBy;
import com.flipkart.daily.utils.SortCriteria;
import com.flipkart.daily.utils.SortDirection;
import com.flipkart.daily.exception.InvalidQuantityException;
import com.flipkart.daily.exception.ItemExistsException;
import com.flipkart.daily.exception.ItemNotFoundException;
import com.flipkart.daily.model.InventoryResultItem;
import com.flipkart.daily.service.InventoryService;
import com.flipkart.daily.service.SearchService;

@SpringBootApplication
public class DailyApplication {

	public static void main(String[] args) {
		InventoryService inventoryService = new InventoryService();
		SearchService searchService = new SearchService(inventoryService);

		try {
			// Add Items
			inventoryService.addItem("Milk", "Amul", 100);
			inventoryService.addItem("Curd", "Amul", 50);
			inventoryService.addItem("Milk", "Nestle", 60);
			inventoryService.addItem("Curd", "Nestle", 90);

			// Add Inventory
			inventoryService.addInventory("Milk", "Amul", 10);
			inventoryService.addInventory("Milk", "Nestle", 5);
			inventoryService.addInventory("Curd", "Nestle", 10);
			inventoryService.addInventory("Milk", "Amul", 10);
			inventoryService.addInventory("Curd", "Amul", 5);

			// Search Examples
			System.out.println("Search by brand Nestle:");
			FilterCriteria brandFilter = new FilterCriteria();
			brandFilter.setBrands(new HashSet<>(Collections.singletonList("Nestle")));
			printResults(searchService.searchItems(brandFilter, null));

			System.out.println("\nSearch by category Milk:");
			FilterCriteria categoryFilter = new FilterCriteria();
			categoryFilter.setCategories(new HashSet<>(Collections.singletonList("Milk")));
			printResults(searchService.searchItems(categoryFilter, null));

			System.out.println("\nSearch by category Milk sorted by price descending:");
			SortCriteria sortDesc = new SortCriteria(SortBy.PRICE, SortDirection.DESC);
			printResults(searchService.searchItems(categoryFilter, sortDesc));

			System.out.println("\nSearch by price [70, 100]:");
			FilterCriteria priceFilter = new FilterCriteria();
			priceFilter.setMinPrice(70.0);
			priceFilter.setMaxPrice(100.0);
			printResults(searchService.searchItems(priceFilter, null));

			System.out.println("\nSearch by category Milk and price [70,100] sorted by price descending:");
			FilterCriteria categoryPriceFilter = new FilterCriteria();
			categoryPriceFilter.setCategories(new HashSet<>(Collections.singletonList("Milk")));
			categoryPriceFilter.setMinPrice(70.0);
			categoryPriceFilter.setMaxPrice(100.0);
			printResults(searchService.searchItems(categoryPriceFilter, sortDesc));

		} catch (ItemExistsException | ItemNotFoundException | InvalidQuantityException e) {
			e.printStackTrace();
		}
	}

	private static void printResults(List<InventoryResultItem> results) {
		results.forEach(
				item -> System.out.println(item.getBrand() + ", " + item.getCategory() + ", " + item.getQuantity()));
	}

}
