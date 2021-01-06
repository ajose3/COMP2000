package com;

import javax.swing.*;
import java.awt.*;

public class Basket extends JFrame{
    public JPanel Basket;
    public JTextArea basketItems;
    public JTextArea totalBox;

    public Basket(){
        setContentPane(Basket);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        pack();
    }

}
