package com.company;

/** This class is used reading in the items
 */
public class Item {
    private int quantity;
    private String itemName;
    private double price;

    public Item() {
    }

    /**
     * Constructor that initializes an item with the appropriate properties
     */
    public Item(int quantity, String itemName, double price) {
        this.quantity = quantity;
        this.itemName = itemName;
        this.price = price;
    }

    /**
     * Getter to obtain the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Getter to obtain the name of the item
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Getter to obtain the price
     */
    public double getPrice() {
        return price;
    }
}

