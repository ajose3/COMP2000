package View;

import Controller.AdminData;

import Controller.CRUD;
import Model.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddDelAdmin extends JFrame implements CRUD {
    private JPanel addDelAdmin;
    private JTextArea newNameBox;
    private JTextArea newPassBox;
    private JButton addNewUserBtn;
    private JTextArea usernameBox;
    private JTextArea passwordBox;
    private JButton deleteBtn;
    private JComboBox<String> adminBox;
    private JComboBox<String> selectAdminBox;
    private JTextArea editUsername;
    private JTextArea editPassword;
    private JButton saveUser;

    public AddDelAdmin() {
        setContentPane(addDelAdmin);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(700, 500));
        fillAdminBox();
        pack();

        addNewUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    add();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Admin has been created");
            }
        });
        adminBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameBox.selectAll();
                usernameBox.replaceSelection("");
                passwordBox.selectAll();
                passwordBox.replaceSelection("");

                AdminData.adminIndex = adminBox.getSelectedIndex();
                Admin selectedAdmin = AdminData.admins.get(AdminData.adminIndex);

                usernameBox.append(selectedAdmin.getUsername());
                passwordBox.append(selectedAdmin.getPassword());
            }
        });
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure that you want to delete the Admin?", "Delete Product", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION){
                    try {
                        delete();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                else if (response == JOptionPane.NO_OPTION){
                    JOptionPane.showMessageDialog(null, "Admin has not been deleted");
                }
            }
        });
        selectAdminBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editUsername.selectAll();
                editUsername.replaceSelection("");
                editPassword.selectAll();
                editPassword.replaceSelection("");

                AdminData.adminIndex = selectAdminBox.getSelectedIndex();
                Admin selectedAdmin = AdminData.admins.get(AdminData.adminIndex);

                editUsername.append(selectedAdmin.getUsername());
                editPassword.append(selectedAdmin.getPassword());
            }
        });
        saveUser.addActionListener(new ActionListener() {
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
        String username = newNameBox.getText();
        String password = newPassBox.getText();

        Admin newAdmin = new Admin(username, password);

        AdminData.admins.add(newAdmin);

        Controller.AdminData.writeAdmin();
    }

    public void delete() throws IOException {
        Controller.AdminData.adminIndex = adminBox.getSelectedIndex();
        Admin selectedAdmin = AdminData.admins.get(Controller.AdminData.adminIndex);

        AdminData.admins.remove(selectedAdmin);

        AdminData.writeAdmin();
    }

    private void fillAdminBox(){
        for (Admin a : AdminData.admins
        ) {
            adminBox.addItem(a.getUsername());
            selectAdminBox.addItem(a.getUsername());
        }
    }

    public void edit() throws IOException {
        String newUName = editUsername.getText();
        String newPassword = editPassword.getText();

        Controller.AdminData.adminIndex = selectAdminBox.getSelectedIndex();
        Admin selectedAdmin = AdminData.admins.get(AdminData.adminIndex);

        int indexSelectedAdmin = AdminData.admins.indexOf(selectedAdmin);

        Admin changeAdmin = new Admin(newUName, newPassword);
        AdminData.admins.set(indexSelectedAdmin, changeAdmin);

        for (Admin a: AdminData.admins
        ) {
            System.out.println("Username: " + a.getUsername() + " Password: " + a.getPassword());
        }

        Controller.AdminData.writeAdmin();
    }
}
