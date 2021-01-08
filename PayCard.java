package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayCard extends JFrame implements IPay{
    private JPanel PayCard;
    public JTextArea totalNeedBox;
    private JTextArea userAmountBox;
    private JButton verifyBtn;
    private JButton printReceiptBtn;

    public PayCard() {
        setContentPane(PayCard);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        pack();
        printReceiptBtn.setEnabled(false);
        verifyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payment();
            }
        });
        printReceiptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getReceipt();
            }
        });
    }

    public void payment(){
        String readNeedAmount = totalNeedBox.getText();
        double needAmount = Double.parseDouble(readNeedAmount);

        String userInAmount = userAmountBox.getText();
        double userAmount = Double.parseDouble(userInAmount);

        if (userAmount == needAmount){
            int response = JOptionPane.showConfirmDialog(null, "Contacting Bank. Verify Payment?", "Verifying with bank", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION ){
                System.out.println("Payment complete");
                printReceiptBtn.setEnabled(true);
            } else  if (response == JOptionPane.NO_OPTION){
                JOptionPane.showMessageDialog(null, "Transaction was not successful");
            }
        }
        else if (userAmount != needAmount){
            JOptionPane.showMessageDialog(null, "Transaction was not successful");
        }

    }

    public void getReceipt(){
        Receipt newReceipt = new Receipt();
        newReceipt.setVisible(true);
        newReceipt.receiptBox.append("\n" + java.time.LocalDate.now() + "\nThe Kiosk Company");
        newReceipt.receiptBox.append("\n");

        for (Product p: MyArrayLists.scannedProducts
        ) {
            newReceipt.receiptBox.append("\n Product: " + p.getProductName() + "          Price: £" + String.format("%.2f", p.getPrice()));
        }

        double total = 0.00;

        if (MyArrayLists.scannedProducts.isEmpty()){
            total = 0.00;
        } else {
            for (Product p : MyArrayLists.scannedProducts
            ) {
                total = total + (p.getPrice());
            }
        }

        newReceipt.receiptBox.append("\n");
        newReceipt.receiptBox.append("\n Total Price: £" + String.format("%.2f", total));
        newReceipt.receiptBox.append("\n");
        newReceipt.receiptBox.append("\n Payment Method:    Card");
    }
}
