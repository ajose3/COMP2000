package View;

import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Kiosk extends JFrame{
    private JPanel kiosk;
    private JComboBox<String> productBox;
    private JTextArea priceDisplay;
    private JButton loginPageBtn;
    private JButton addBasket;
    private JButton viewBasket;

    public Kiosk(){
        setContentPane(kiosk);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        Basket basket = new Basket();
        basket.setVisible(false);
        pack();

        productBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                priceDisplay.selectAll();
                priceDisplay.replaceSelection("");
                Controller.ProductData.productIndex = productBox.getSelectedIndex();

                Product selectedProduct = Controller.ProductData.products.get(Controller.ProductData.productIndex);
                priceDisplay.append(String.valueOf(String.format("%.2f", selectedProduct.getPrice())));
            }
        });
        loginPageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login loginPage = new Login();
                loginPage.setVisible(true);
                try {
                    loginPage.readAdminFile();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                loginPage.verifyAdmin();
            }
        });
        viewBasket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                basket.setVisible(true);
            }
        });
        addBasket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.ProductData.productIndex = productBox.getSelectedIndex();
                Product selectedProduct = Controller.ProductData.products.get(Controller.ProductData.productIndex);

                if (selectedProduct.getStock() < 1){
                    JOptionPane.showMessageDialog(null, "Sorry, item out of stock");
                }
                else{
                    Product scannedItem = new Product(selectedProduct.getProductID(), selectedProduct.getProductName(), selectedProduct.getStock(), selectedProduct.getPrice());

                    Controller.ProductData.scannedProducts.add(scannedItem);

                    int indexProduct = Controller.ProductData.products.indexOf(selectedProduct);

                    Product newStockProduct = new Product(selectedProduct.getProductID(), selectedProduct.getProductName(), selectedProduct.getStock() - 1, selectedProduct.getPrice());
                    Controller.ProductData.products.set(indexProduct, newStockProduct);
                    basket.basketItems.append(" \n Barcode:  " + selectedProduct.getProductID() +  "    Product: " + selectedProduct.getProductName() + "          Price: £" + String.format("%.2f", selectedProduct.getPrice()));

                    try {
                        Controller.ProductData.writeFile();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

                //Testing to see if products are added to the scannedProducts Arraylist
                System.out.println(Controller.ProductData.scannedProducts);

                double total = 0.00;
                if (Controller.ProductData.scannedProducts.isEmpty()){
                    total = 0.00;
                } else {
                    for (Product p: Controller.ProductData.scannedProducts) {
                        total = total + (p.getPrice());
                    }

                    basket.totalBox.selectAll();
                    basket.totalBox.replaceSelection("");
                    basket.totalBox.append(String.format("%.2f", total));
                }
            }
        });
    }

    // fill combobox
    public void fillProductBox(){
        for (Product p : Controller.ProductData.products
        ) {
        productBox.addItem(p.getProductName());
        }
    }

    public static void main(String[] args) throws IOException {
        Kiosk kiosk = new Kiosk();
        kiosk.setVisible(true);
        //Testing to see if it opens the Kiosk JFrame
        System.out.println("Working");
        Controller.ProductData.readFile();
        kiosk.fillProductBox();
    }
}
