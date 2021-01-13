package Controller;

import Model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductData{

    //Products Arraylist
    public static ArrayList<Product> products = new ArrayList<Product>();

    //Scanned Products Arraylist
    public static ArrayList<Product> scannedProducts = new ArrayList<>();

    //Used to show which product has been selected
    public static int productIndex;

    //reading the file
    public static void readFile() throws FileNotFoundException {
        String filepath = "productList.txt";
        File file = new File(filepath);

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()){
            String pID, pName;
            int amount;
            double cost;

            pID = scanner.next();
            pName = scanner.next();
            amount = Integer.parseInt(scanner.next());
            cost = Double.parseDouble(scanner.next());

            Product newProduct = new Product(pID, pName, amount, cost);

            Controller.ProductData.products.add(newProduct);

            //Testing to see if it reads the file
            System.out.println("Product ID: " + pID + " " + "Name: " + pName + " " + "Stock: " + amount +  " " + "Price: " + String.format("%.2f", cost));
        }
        scanner.close();
    }


    //Writing to the file
    public static ArrayList<Product> writeFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("productList.txt"));

        for (Product p: products
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
