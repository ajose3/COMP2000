package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Basket extends JFrame{
    public JPanel Basket;
    public JTextArea basketItems;
    public JTextArea totalBox;
    private JButton payCashBtn;
    private JButton payCardbtn;

    public Basket(){
        setContentPane(Basket);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        pack();
        payCashBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                PayCash newCashPay = new PayCash();
                newCashPay.setVisible(true);

                String totalPrice;
                totalPrice = totalBox.getText();

                newCashPay.totalNeedBox.append(totalPrice);
            }
        });
        payCardbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                PayCard newCardPay = new PayCard();
                newCardPay.setVisible(true);

                String totalPrice;
                totalPrice = totalBox.getText();

                newCardPay.totalNeedBox.append(totalPrice);
            }
        });
    }
}
