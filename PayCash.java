package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayCash extends JFrame implements IPay{

    public JPanel PayCash;
    public JTextArea totalNeedBox;
    private JTextArea userCashBox;
    private JTextArea changeBox;
    private JButton payBtn;

    public PayCash(){
        setContentPane(PayCash);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        pack();
        payBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payment();
            }
        });
    }

    public void payment(){
        String readNeedCash = totalNeedBox.getText();
        double needCash = Double.parseDouble(readNeedCash);

        String userInCash = userCashBox.getText();
        double userCash = Double.parseDouble(userInCash);

        double change = 0.00;
        if (userCash == needCash){
            change = 0.00;
            changeBox.append(String.format("%.2f", change));
            totalNeedBox.append("0.00");
        }
        else if (userCash > needCash){
            change = userCash - needCash;
            changeBox.append(String.format("%.2f", change));
            totalNeedBox.selectAll();
            totalNeedBox.replaceSelection("");
            totalNeedBox.append("0.00");
        }
        else if (userCash < needCash){
            double morePay = needCash - userCash;

            totalNeedBox.selectAll();
            totalNeedBox.replaceSelection("");
            totalNeedBox.append(String.format("%.2f", morePay));
            userCashBox.selectAll();
            userCashBox.replaceSelection("");
        }
    }
}
