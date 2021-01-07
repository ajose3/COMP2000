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

    public PayCard() {
        setContentPane(PayCard);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        pack();
        verifyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payment();
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
            } else  if (response == JOptionPane.NO_OPTION){
                JOptionPane.showMessageDialog(null, "Transaction was not successful");
            }
        }
        else if (userAmount != needAmount){
            JOptionPane.showMessageDialog(null, "Transaction was not successful");
        }

    }
}
