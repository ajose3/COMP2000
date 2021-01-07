package com;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MyArrayLists {

    //Product Array List
    public static ArrayList<Product> products = new ArrayList<Product>();

    //Admin Array List
    public static ArrayList<Admin> admins = new ArrayList<>();

    //Scanned Product Array List
    public static ArrayList<Product> scannedProducts = new ArrayList<>();

    //Array List to get all Products
    public static ArrayList<Product> getAllProducts(){
        return products;
    }

    //Used to show which product has been selected
    public static int productIndex;

    public static ArrayList<Product> writeFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("productList.txt"));

        for (Product p: MyArrayLists.products
        ) {
            writer.write(p.getProductID());
            writer.newLine();
            writer.write(p.getProductName());
            writer.newLine();
            writer.write(Integer.toString(p.getStock()));
            writer.newLine();
            writer.write(Double.toString(p.getPrice()));
            writer.newLine();
        }
        writer.close();
        return products;
    }

}
