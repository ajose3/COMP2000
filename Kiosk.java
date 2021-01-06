package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Kiosk extends JFrame{
    private JPanel kiosk;
    private JComboBox<String> productBox;
    private JTextArea priceDisplay;
    private JButton loginPageBtn;
    private JButton addBasket;
    private JButton viewBasket;

    public Kiosk(){
        setContentPane(kiosk);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        Basket basket = new Basket();
        basket.setVisible(false);
        pack();

        productBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                priceDisplay.selectAll();
                priceDisplay.replaceSelection("");
                MyArrayLists.productIndex = productBox.getSelectedIndex();

                Product selectedProduct = MyArrayLists.products.get(MyArrayLists.productIndex);
                priceDisplay.append(String.valueOf(String.format("%.2f", selectedProduct.getPrice())));
            }
        });
        loginPageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login loginPage = new Login();
                loginPage.setVisible(true);
                try {
                    loginPage.readAdminFile();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                loginPage.verifyAdmin();
            }
        });
        viewBasket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                basket.setVisible(true);
            }
        });
        addBasket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyArrayLists.productIndex = productBox.getSelectedIndex();
                Product selectedProduct = MyArrayLists.products.get(MyArrayLists.productIndex);

                basket.basketItems.append(" \n Product: " + selectedProduct.getProductName() + "          Price: £" + String.format("%.2f", selectedProduct.getPrice()));

                Product scannedItem = new Product(selectedProduct.getProductID(), selectedProduct.getProductName(), selectedProduct.getStock(), selectedProduct.getPrice());

                MyArrayLists.scannedProducts.add(scannedItem);

                System.out.println(MyArrayLists.scannedProducts);

                double total = 0.00;
                if (MyArrayLists.scannedProducts.isEmpty()){
                    total = 0.00;
                } else {
                    for (Product p: MyArrayLists.scannedProducts
                    ) {
                        total = total + (p.getPrice());
                    }

                    basket.totalBox.selectAll();
                    basket.totalBox.replaceSelection("");
                    basket.totalBox.append(String.format("%.2f", total));
                }
            }
        });
    }


    //reading the file
    private void readFile() throws IOException {
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

            MyArrayLists.products.add(newProduct);

            System.out.println("Product ID: " + pID + " " + "Name: " + pName + " " + "Stock: " + amount +  " " + "Price: " + String.format("%.2f", cost));
        }
        scanner.close();

    }

    // fill combobox
    private void fillProductBox(){
        for (Product p : MyArrayLists.products
        ) {
        productBox.addItem(p.getProductName());
        }
    }

    public static void main(String[] args) throws IOException {
        Kiosk kiosk = new Kiosk();
        kiosk.setVisible(true);
        System.out.println("Working");
        kiosk.readFile();
        kiosk.fillProductBox();
    }
}
