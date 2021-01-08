package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendOrders extends JFrame{
    public JPanel SendOrders;
    private JComboBox<String> productBox;
    private JTextArea inputStockBox;
    private JTextArea totalOrderBox;
    private JButton checkPriceBtn;
    private JButton sendFinalOrder;

    public SendOrders() {
        setContentPane(SendOrders);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        pack();
        fillProductBox();
        checkPriceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalPrice();
            }
        });
        sendFinalOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Your order has been sent. You will receive a confirmation email soon with an invoice");
                inputStockBox.selectAll();
                inputStockBox.replaceSelection("");
                totalOrderBox.selectAll();
                totalOrderBox.replaceSelection("");
            }
        });
    }

    private void fillProductBox(){
        for (Product p : MyArrayLists.products
        ) {
            productBox.addItem(p.getProductName());
        }
    }

    private void totalPrice(){
        MyArrayLists.productIndex = productBox.getSelectedIndex();
        Product selectedProduct = MyArrayLists.products.get(MyArrayLists.productIndex);

        double total = 0.00;
        double price = selectedProduct.getPrice();

        String numStock = inputStockBox.getText();
        double stockNum = Double.parseDouble(numStock);

        total = (stockNum * price) + 0.50;

        totalOrderBox.selectAll();
        totalOrderBox.replaceSelection("");
        totalOrderBox.append("£" + String.format("%.2f", total));
    }
}
