package View;

import Controller.AdminData;
import Model.Admin;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AdminPage extends JFrame{
    public JPanel adminPage;
    private JTextArea lowStockBox;
    private JButton sendOrdersBtn;
    private JButton exitBtn;
    private JButton addDelProducts;
    private JButton addDelAdmins;

    public AdminPage(){
        setContentPane(adminPage);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 700));
        pack();

        for (Product p: Controller.ProductData.products
             ) {
            if (p.getStock() <= 3){
                lowStockBox.append("\n Product Name: "  + p.getProductName() + "       Stock level: " + p.getStock());
            }
        }

        sendOrdersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendOrders newOrders = new SendOrders();
                newOrders.setVisible(true);
            }
        });
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addDelProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDelProducts newPage = new AddDelProducts();
                newPage.setVisible(true);
            }
        });
        addDelAdmins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDelAdmin newPage = new AddDelAdmin();
                newPage.setVisible(true);
            }
        });
    }





}
