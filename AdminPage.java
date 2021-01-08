package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AdminPage extends JFrame{
    public JPanel adminPage;
    private JComboBox<String> selectProductBox;
    private JTextArea editStock;
    private JTextArea editPrice;
    private JTextArea editName;
    private JButton updateSave;
    private JTextArea editID;
    private JTextArea lowStockBox;
    private JButton sendOrdersBtn;

    public AdminPage(){
        setContentPane(adminPage);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        fillProductBox();
        pack();

        for (Product p: MyArrayLists.products
             ) {
            if (p.getStock() <= 3){
                lowStockBox.append("\n Product Name: "  + p.getProductName() + "       Stock level: " + p.getStock());
            }
        }

        selectProductBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editID.selectAll();
                editID.replaceSelection("");
                editName.selectAll();
                editName.replaceSelection("");
                editStock.selectAll();
                editStock.replaceSelection("");
                editPrice.selectAll();
                editPrice.replaceSelection("");

                MyArrayLists.productIndex = selectProductBox.getSelectedIndex();
                Product selectedProduct = MyArrayLists.products.get(MyArrayLists.productIndex);

                editID.append(selectedProduct.getProductID());
                editName.append(selectedProduct.getProductName());
                editStock.append(Integer.toString(selectedProduct.getStock()));
                editPrice.append((String.format("%.2f", selectedProduct.getPrice())));
            }
        });
        updateSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    editProducts();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        sendOrdersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendOrders newOrders = new SendOrders();
                newOrders.setVisible(true);
            }
        });
    }

    private void fillProductBox(){
        for (Product p : MyArrayLists.products
        ) {
            selectProductBox.addItem(p.getProductName());
        }
    }

    public void editProducts() throws IOException {
        String newPId = editID.getText();
        String newPName = editName.getText();
        String newStock = editStock.getText();
        String newPrice = editPrice.getText();

        int stock = Integer.parseInt(newStock);
        double price = Double.parseDouble(newPrice);

        MyArrayLists.productIndex = selectProductBox.getSelectedIndex();
        Product selectedProduct = MyArrayLists.products.get(MyArrayLists.productIndex);

        int indexSelectedProduct = MyArrayLists.products.indexOf(selectedProduct);

        Product changeProduct = new Product(newPId, newPName, stock, price);

        MyArrayLists.products.set(indexSelectedProduct, changeProduct);

        //Test to see if product is changed
        for (Product p: MyArrayLists.products
             ) {
            System.out.println("ID: " + p.getProductID() + " Name: " + p.getProductName() + " Stock: " + p.getStock() + " Price: " + p.getPrice());
        }

        MyArrayLists.writeFile();
    }
}
