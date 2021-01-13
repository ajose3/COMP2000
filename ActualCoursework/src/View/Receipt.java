package View;

import javax.swing.*;
import java.awt.*;

public class Receipt extends JFrame{
    private JPanel receipt;
    public JTextArea receiptBox;

    public Receipt(){
        setContentPane(receipt);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        pack();
    }
}
