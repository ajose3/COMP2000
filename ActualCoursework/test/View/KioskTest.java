package View;

import Model.Product;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class KioskTest {
    ArrayList<Product> products = new ArrayList<>();

    @Test
    public void addProduct(){
        String pID = "1";
        String pName = "Apples";
        int stock = 4;
        double price = 1.50;
        products.add(new Product(pID, pName, stock, price));

        assertEquals("Check size of array", 1, products.size());
    }

    @Test
    public void verifyAdmin(){
        ArrayList<String> correctDetails = new ArrayList<>();
        ArrayList<String> inputDetails = new ArrayList<>();

        String username = "user";
        String password = "password";
        correctDetails.add(username);
        correctDetails.add(password);

        String inputUsername = "user";
        String inputPassword = "password";
        inputDetails.add(inputUsername);

        inputDetails.add(inputPassword);

        assertEquals("Check if credentials are matching", correctDetails, inputDetails);
    }

    @Test
    public void totalPrice(){
        double total = 0.00;
        double price = 1.50;
        String numStock = "5";
        double stockNum = Double.parseDouble(numStock);

        total = (stockNum * price) + 0.50;
        assertNotEquals("Check if total calculation is correct", 7, total);
    }
}