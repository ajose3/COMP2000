package View;

import Controller.CRUD;
import Controller.ProductData;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddDelProducts extends JFrame implements CRUD {
    private JPanel addDelProducts;
    private JTextArea newIdBox;
    private JTextArea pNameBox;
    private JTextArea stockBox;
    private JTextArea priceBox;
    private JButton addProductBtn;
    private JComboBox<String> productBox;
    private JTextArea pIDbox;
    private JTextArea nameBox;
    private JTextArea pStockBox;
    private JTextArea pPriceBox;
    private JButton deleteBtn;
    private JComboBox<String> selectProductBox;
    private JTextArea editID;
    private JTextArea editName;
    private JTextArea editStock;
    private JTextArea editPrice;
    private JButton updateSave;

    public AddDelProducts() {
        setContentPane(addDelProducts);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(700, 500));
        fillProductBox();
        pack();

        addProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    add();
                    JOptionPane.showMessageDialog(null, "Product has been added");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        productBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pIDbox.selectAll();
                pIDbox.replaceSelection("");
                nameBox.selectAll();
                nameBox.replaceSelection("");
                pStockBox.selectAll();
                pStockBox.replaceSelection("");
                pPriceBox.selectAll();
                pPriceBox.replaceSelection("");

                Controller.ProductData.productIndex = productBox.getSelectedIndex();
                Product selectedProduct = Controller.ProductData.products.get(Controller.ProductData.productIndex);

                pIDbox.append(selectedProduct.getProductID());
                nameBox.append(selectedProduct.getProductName());
                pStockBox.append(Integer.toString(selectedProduct.getStock()));
                pPriceBox.append((String.format("%.2f", selectedProduct.getPrice())));
            }
        });
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure that you want to delete the product?", "Delete Product", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION){
                    try {
                        delete();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                else  if (response == JOptionPane.NO_OPTION){
                    JOptionPane.showMessageDialog(null, "Product has not been deleted");
                }

            }
        });
        selectProductBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editID.selectAll();
                editID.replaceSelection("");
                editName.selectAll();
                editName.replaceSelection("");
                editStock.selectAll();
                editStock.replaceSelection("");
                editPrice.selectAll();
                editPrice.replaceSelection("");

                Controller.ProductData.productIndex = selectProductBox.getSelectedIndex();
                Product selectedProduct = Controller.ProductData.products.get(Controller.ProductData.productIndex);

                editID.append(selectedProduct.getProductID());
                editName.append(selectedProduct.getProductName());
                editStock.append(Integer.toString(selectedProduct.getStock()));
                editPrice.append((String.format("%.2f", selectedProduct.getPrice())));
            }
        });
        updateSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    edit();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public void add() throws IOException {
        String pID = newIdBox.getText();
        String pName = pNameBox.getText();
        String amount = stockBox.getText();
        String cost = priceBox.getText();

        int stock = Integer.parseInt(amount);
        double price = Double.parseDouble(cost);

        Product newProduct = new Product(pID, pName, stock, price);

        ProductData.products.add(newProduct);

        Controller.ProductData.writeFile();
    }

    public void delete() throws IOException {
        Controller.ProductData.productIndex = productBox.getSelectedIndex();
        Product selectedProduct = Controller.ProductData.products.get(Controller.ProductData.productIndex);

        ProductData.products.remove(selectedProduct);

        Controller.ProductData.writeFile();
    }

    public void edit() throws IOException {
        String newPId = editID.getText();
        String newPName = editName.getText();
        String newStock = editStock.getText();
        String newPrice = editPrice.getText();

        int stock = Integer.parseInt(newStock);
        double price = Double.parseDouble(newPrice);

        Controller.ProductData.productIndex = selectProductBox.getSelectedIndex();
        Product selectedProduct = Controller.ProductData.products.get(Controller.ProductData.productIndex);

        int indexSelectedProduct = Controller.ProductData.products.indexOf(selectedProduct);

        Product changeProduct = new Product(newPId, newPName, stock, price);

        Controller.ProductData.products.set(indexSelectedProduct, changeProduct);

        //Test to see if product is changed
        for (Product p: Controller.ProductData.products
        ) {
            System.out.println("ID: " + p.getProductID() + " Name: " + p.getProductName() + " Stock: " + p.getStock() + " Price: " + p.getPrice());
        }

        Controller.ProductData.writeFile();
    }

    private void fillProductBox(){
        for (Product p : Controller.ProductData.products
        ) {
            productBox.addItem(p.getProductName());
            selectProductBox.addItem(p.getProductName());
        }
    }
}
