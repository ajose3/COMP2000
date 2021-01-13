package Controller;

import Model.Admin;
import Model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminData {

    //Admin Arraylist
    public static ArrayList<Admin> admins = new ArrayList<>();

    public static int adminIndex;

    //reading admin file
    public static void readAdminFile() throws FileNotFoundException {
        String path = "credentials.txt";
        File adminFile = new File(path);
        String split = " ";

        Scanner adminScanner = new Scanner(adminFile);
        while (adminScanner.hasNextLine()){
            String data = adminScanner.nextLine();

            String[] adminData = data.split(split);

            Admin newAdmin = new Admin(adminData[0], adminData[1]);

            newAdmin.setUsername(adminData[0]);
            newAdmin.setPassword(adminData[1]);

            Controller.AdminData.admins.add(newAdmin);

            //Testing to see if it reads the admin file
            System.out.println(newAdmin.getUsername() + newAdmin.getPassword());

        }
        adminScanner.close();
    }

    public static ArrayList<Admin> writeAdmin() throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter("credentials.txt"));

        for (Admin a: admins
        ) {
            writer.write(a.getUsername());
            writer.write(" ");
            writer.write(a.getPassword());
            writer.newLine();

        }
        writer.close();
        return admins;
    }

}
