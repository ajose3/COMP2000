package View;

import Model.IPay;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.*;

public class PayCard extends JFrame implements IPay {
    private JPanel PayCard;
    public JTextArea totalNeedBox;
    private JTextArea userAmountBox;
    private JButton verifyBtn;
    private JButton printReceiptBtn;
    private JTextField userPinBox;

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
                try {
                    dispose();

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("Thread 1 ran");
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }

                        }
                    });
                    System.out.println("Number of threads: " + Thread.activeCount());
                    thread.start();
                    run();

                    thread.join(4000);

                    getReceipt();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });
    }

    public void payment(){
        String readNeedAmount = totalNeedBox.getText();
        double needAmount = Double.parseDouble(readNeedAmount);

        String userInAmount = userAmountBox.getText();
        double userAmount = Double.parseDouble(userInAmount);

        try{
            int pin = Integer.parseInt(userPinBox.getText());
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
        } catch (NumberFormatException e
        ){
            JOptionPane.showMessageDialog(null, "Please enter your pin");
        }

    }

    public void getReceipt() throws InterruptedException {

        Thread newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Receipt newReceipt = new Receipt();
                newReceipt.setVisible(true);

                newReceipt.receiptBox.append("\n" + java.time.LocalDate.now() + "\nThe Kiosk Company");
                newReceipt.receiptBox.append("\n");

                for (Product p: Controller.ProductData.scannedProducts
                ) {
                    newReceipt.receiptBox.append("\n Barcode: " + p.getProductID() + "      Product: " + p.getProductName() + "          Price: £" + String.format("%.2f", p.getPrice()));
                }

                double total = 0.00;

                if (Controller.ProductData.scannedProducts.isEmpty()){
                    total = 0.00;
                } else {
                    for (Product p : Controller.ProductData.scannedProducts
                    ) {
                        total = total + (p.getPrice());
                    }
                }

                newReceipt.receiptBox.append("\n");
                newReceipt.receiptBox.append("\n Total Price: £" + String.format("%.2f", total));
                newReceipt.receiptBox.append("\n");
                newReceipt.receiptBox.append("\n Payment Method:    Card");
            }
        });
        newThread.start();

        run();
    }

    public void run(){
        System.out.println(Thread.currentThread().getName() + "is running");
    }


}
