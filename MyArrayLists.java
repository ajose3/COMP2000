package com;

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
}
