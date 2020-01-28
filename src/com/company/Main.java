package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        String f1 = "./src/resources/input1.txt";
        String o1 = "./src/out/output1.txt";
        calcTax(f1,o1);
        String f2 = "./src/resources/input2.txt";
        String o2 = "./src/out/output2.txt";
        calcTax(f2,o2);
        String f3 = "./src/resources/input3.txt";
        String o3 = "./src/out/output3.txt";
        calcTax(f3,o3);
    }
    /**
     * This function traverses the receipt,
     * writes to an output file with the decimal format
     * rounded to 0.05
     */
    public static void fileWrite(String o, Receipt r, double total, double salesTax)
    {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(o));
            for (Item thisItem: r.reciept)
            {
                String str = "" + thisItem.getQuantity();
                String str1 = "" + String.format("%.2f", thisItem.getPrice());
                out.write( str + " " + thisItem.getItemName() + ": " +  str1);
                out.newLine();
                total += thisItem.getPrice();
            }
            String str2 = "" + String.format("%.2f", salesTax);
            String str3 = "" + String.format("%.2f", total);
            out.write("Sales Taxes: " + str2);
            out.newLine();
            out.write("Total: " + str3);
            out.close();
        }
        catch (IOException e) {
        }
    }
    /**
     * This function adds the sales tax accordingly
     */
    public static void calcTax(String f, String o)
    {
        List<Item> items = new ArrayList<>();
        Receipt r = new Receipt();
        try
        {
            /** While loop reads in the file
             * using a line scanner to handle integers and text
             * with a delimeter storing them into Item objects */
            Scanner scanner = new Scanner(new File(f));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                while (lineScanner.hasNext())
                {
                    int q = lineScanner.nextInt();
                    lineScanner.useDelimiter(" at ");
                    String item = lineScanner.next();
                    double p = lineScanner.nextDouble(); // might error
                    items.add(new Item (q,item,p));
                }
                lineScanner.close();
            }
            double salesTax = 0;
            double total = 0;
            /** Traversing through the item objects
             *  to apply the appropriate tax */
            for (Item i:items)
            {
                int quantity = i.getQuantity();
                String itemName = i.getItemName().trim();
                double price = i.getPrice();
                /** Applies Imported and Sales tax */
                if (itemName.contains("imported") && (!(itemName.contains("book")) && !(itemName.contains("chocolate")) && !(itemName.contains("pills"))))
                {
                    double newPrice = Math.round(((price * 1.15 *quantity)+price)*100.00)/100.00;
                    newPrice = Math.round((newPrice-price)*20.00)/20.00;
                    salesTax += Math.round((newPrice-price)*100.00)/100.00;
                    // Generate a new item here with the modified price
                    Item item = new Item(quantity,itemName, newPrice);
                    // Pass into Receipt
                    r.addItem(item);
                }
                /** Apply Sales tax */
                else if (!(itemName.contains("book")) && !(itemName.contains("chocolate")) && !(itemName.contains("pills")))
                {
                    double newPrice = Math.round((((Math.ceil(price * 20.00 * 0.10)/ 20.00)*quantity)+price)*100.00)/100.00;
                    salesTax += Math.round((newPrice-price)*100.00)/100.00;
                    // Generate a new item here with the modified price
                    Item item = new Item(quantity,itemName, newPrice);
                    // Pass into Receipt
                    r.addItem(item);
                }
                /** Apply the Imported tax */
                else if (itemName.contains("imported"))
                {
                    double newPrice = Math.round((((Math.ceil(price * 20.00 * 0.05)/ 20.00)*quantity)+price)*100.00)/100.00;
                    salesTax += Math.round((newPrice-price)*100.00)/100.00;
                    // Generate a new item here with the modified price
                    Item item = new Item(quantity,itemName, newPrice);
                    // Pass into Receipt
                    r.addItem(item);
                }
                /** Apply no sales tax*/
                else
                {
                    Item item = new Item(quantity,itemName, price);
                    r.addItem(item);
                }
            }
            fileWrite(o,r,total,salesTax);
            scanner.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
