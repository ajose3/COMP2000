package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Login extends JFrame{
    private JPanel loginPage;
    private JTextField usernameEntry;
    private JTextField passwordEntry;
    private JButton login;
    public String split = " ";
    public boolean isAuthenticated = false;

    public Login(){
        setContentPane(loginPage);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        pack();
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifyAdmin();
                if (isAuthenticated){
                    dispose();
                    AdminPage adminPage = new AdminPage();
                    adminPage.setVisible(true);
                }
            }
        });
    }

    public void readAdminFile() throws FileNotFoundException {
        String path = "credentials.txt";
        File adminFile = new File(path);

        Scanner adminScanner = new Scanner(adminFile);
        while (adminScanner.hasNextLine()){
            String data = adminScanner.nextLine();

            String[] adminData = data.split(split);

            Admin newAdmin = new Admin(adminData[0], adminData[1]);

            newAdmin.setUsername(adminData[0]);
            newAdmin.setPassword(adminData[1]);

            MyArrayLists.admins.add(newAdmin);

            System.out.println(newAdmin.getUsername() + newAdmin.getPassword());

        }
        adminScanner.close();
    }

    public void verifyAdmin(){
        String username = usernameEntry.getText();
        String password = passwordEntry.getText();

        for (Admin a: MyArrayLists.admins
             ) {
            if (username.equals(a.getUsername()) && password.equals(a.getPassword())) {
                System.out.println("Login Success");
                isAuthenticated = true;
            }
            else if (username.equals(a.getUsername()) != password.equals(a.getPassword())){
                JOptionPane.showMessageDialog(null, "Not matching credentials");
            }
        }
    }

    public static void main(String[] args){

    }

}
