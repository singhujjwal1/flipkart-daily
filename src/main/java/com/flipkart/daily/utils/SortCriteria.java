package com.flipkart.daily.utils;

/**
 * Class representing the sorting criteria for items.
 */
public class SortCriteria {
    private SortBy sortBy;
    private SortDirection direction;

    public SortCriteria(SortBy sortBy, SortDirection direction) {
        this.sortBy = sortBy;
        this.direction = direction;
    }

    public SortBy getSortBy() {
        return sortBy;
    }

    public SortDirection getDirection() {
        return direction;
    }
}