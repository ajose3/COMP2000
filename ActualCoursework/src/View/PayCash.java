package View;

import Model.IPay;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayCash extends JFrame implements IPay {

    public JPanel PayCash;
    public JTextArea totalNeedBox;
    private JTextArea userCashBox;
    private JTextArea changeBox;
    private JButton payBtn;
    private JButton printReceiptBtn;

    public PayCash(){
        setContentPane(PayCash);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        pack();
        printReceiptBtn.setEnabled(false);
        payBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payment();
            }
        });
        printReceiptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                try {
                    thread.join(4000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

                getReceipt();
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
            totalNeedBox.selectAll();
            totalNeedBox.replaceSelection("");
            totalNeedBox.append("0.00");
            printReceiptBtn.setEnabled(true);
        }
        else if (userCash > needCash){
            change = userCash - needCash;
            changeBox.append(String.format("%.2f", change));
            totalNeedBox.selectAll();
            totalNeedBox.replaceSelection("");
            totalNeedBox.append("0.00");
            printReceiptBtn.setEnabled(true);
        }
        else if (userCash < needCash){
            double morePay = needCash - userCash;

            JOptionPane.showMessageDialog(null, "You still have £" + String.format("%.2f", morePay) + " left to pay");
            totalNeedBox.selectAll();
            totalNeedBox.replaceSelection("");
            totalNeedBox.append(String.format("%.2f", morePay));
            userCashBox.selectAll();
            userCashBox.replaceSelection("");
        }
    }

    public void getReceipt(){
        Thread newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Receipt newReceipt = new Receipt();
                newReceipt.setVisible(true);
                newReceipt.receiptBox.append("\n" + java.time.LocalDate.now() + "\nThe Kiosk Company");
                newReceipt.receiptBox.append("\n");

                for (Product p: Controller.ProductData.scannedProducts
                ) {
                    newReceipt.receiptBox.append("\n Barcode:  " + p.getProductID() +    "     Product: " + p.getProductName() + "          Price: £" + String.format("%.2f", p.getPrice()));
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
                newReceipt.receiptBox.append("\n Payment Method:     Cash");

                newReceipt.receiptBox.append("\n");
                String change = changeBox.getText();
                newReceipt.receiptBox.append("\n Change: £" + change);
            }
        });
        newThread.start();

        run();
    }

    public void run(){
        System.out.println(Thread.currentThread().getName() + "is running");
    }
}
