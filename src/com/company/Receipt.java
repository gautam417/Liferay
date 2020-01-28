package com.company;

import java.util.ArrayList;
import java.util.List;

/** This class is used for holding the items purchased
 *  by a user with the modified price after adding
 *  the appropriate tax
 *  */
public class Receipt {
    List<Item> reciept;

    /**
     * Constructor that initializes a reciept that will hold multiple items
     */
    public Receipt() {
        reciept = new ArrayList<>();
    }
    /**
     * Adds an item to the receipt's ArrayList
     */
    public void addItem(Item item)
    {
        reciept.add(item);
    }

}
