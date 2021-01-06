package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminPage extends JFrame{
    public JPanel adminPage;
    private JComboBox<String> selectProductBox;
    private JTextArea editStock;
    private JTextArea editPrice;
    private JTextArea editName;
    private JButton updateSave;
    private JTextArea editID;

    public AdminPage(){
        setContentPane(adminPage);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        fillProductBox();
        pack();
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
                    writeFile();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    private void fillProductBox(){
        for (Product p : MyArrayLists.products
        ) {
            selectProductBox.addItem(p.getProductName());
        }
    }

    public void writeFile() throws IOException {
        String newID = editID.getText();
        String newName = editName.getText();
        String newStock = editStock.getText();
        String newPrice = editPrice.getText();

        File writeFile = new File("productList.txt");
        FileWriter writer = new FileWriter(writeFile);
        PrintWriter pw = new PrintWriter(writer);

        pw.println(newID);
        pw.println(newName);
        pw.println(newStock);
        pw.println(newPrice);

        pw.close();
    }

    public void wirte() throws IOException {
        FileWriter fileWrite = new FileWriter("productList");
        for (int i = 0; i < MyArrayLists.getAllProducts().size(); i++){
            String split = "";

            if (i > 0){
                split = "\n";
            }
            split = MyArrayLists.getAllProducts().get(i).getProductID();
            split = "\n";

            split = MyArrayLists.getAllProducts().get(i).getProductName();
            split = "\n";

            split = Integer.toString(MyArrayLists.getAllProducts().get(i).getStock());
            split = "\n";

            split = Double.toString(MyArrayLists.getAllProducts().get(i).getPrice());
            split = "\n";
        }
    }
    
}
