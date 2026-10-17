package com.onlineexam.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.onlineexam.dao.AdminDAO;
import com.onlineexam.dao.StudentDAO;
import com.onlineexam.model.Student;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {

        setTitle("Online Examination System");
        setSize(550, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Style.LIGHT);

        // ================= HEADER =================

        JPanel header = new JPanel();
        header.setBackground(Style.NAVY);
        header.setBorder(
                BorderFactory.createEmptyBorder(25, 10, 25, 10)
        );

        JLabel title = new JLabel("ONLINE EXAMINATION SYSTEM");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(Color.WHITE);

        header.add(title);

        mainPanel.add(header, BorderLayout.NORTH);

        // ================= LOGIN PANEL =================

        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBorder(
                BorderFactory.createEmptyBorder(30, 50, 30, 50)
        );

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel welcome = new JLabel(
                "Login to continue",
                SwingConstants.CENTER
        );

        welcome.setFont(
                new Font("Segoe UI", Font.BOLD, 20)
        );

        welcome.setForeground(Style.NAVY);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        loginPanel.add(welcome, gbc);

        // Username

        JLabel usernameLabel = new JLabel("Username / Email");

        usernameLabel.setFont(Style.NORMAL);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;

        loginPanel.add(usernameLabel, gbc);

        usernameField = Style.field(20);

        gbc.gridx = 1;

        loginPanel.add(usernameField, gbc);

        // Password

        JLabel passwordLabel = new JLabel("Password");

        passwordLabel.setFont(Style.NORMAL);

        gbc.gridy = 2;
        gbc.gridx = 0;

        loginPanel.add(passwordLabel, gbc);

        passwordField = Style.passwordField(20);

        gbc.gridx = 1;

        loginPanel.add(passwordField, gbc);

        // ================= BUTTONS =================

        JButton studentLogin =
                Style.button("Student Login");

        JButton adminLogin =
                Style.button("Admin Login");

        JButton studentRegister =
                Style.button("Student Registration");

        JButton adminRegister =
                Style.button("Admin Registration");

        // Student Login

        gbc.gridy = 3;
        gbc.gridx = 0;

        loginPanel.add(studentLogin, gbc);

        // Admin Login

        gbc.gridx = 1;

        loginPanel.add(adminLogin, gbc);

        // Student Registration

        gbc.gridy = 4;
        gbc.gridx = 0;

        loginPanel.add(studentRegister, gbc);

        // Admin Registration

        gbc.gridx = 1;

        loginPanel.add(adminRegister, gbc);

        mainPanel.add(loginPanel, BorderLayout.CENTER);

        add(mainPanel);

        // ================= ACTIONS =================

        studentLogin.addActionListener(e -> studentLogin());

        adminLogin.addActionListener(e -> adminLogin());

        studentRegister.addActionListener(e -> {

            dispose();

            new RegisterFrame().setVisible(true);

        });

        adminRegister.addActionListener(e -> {

            dispose();

            new AdminRegisterFrame().setVisible(true);

        });
    }

    // ================= STUDENT LOGIN =================

    private void studentLogin() {

        String email = usernameField.getText().trim();

        String password =
                new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username/email and password."
            );

            return;
        }

        Student student =
                new StudentDAO().login(email, password);

        if (student != null) {

            dispose();

            new StudentDashboard(student).setVisible(true);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid student login details."
            );
        }
    }

    // ================= ADMIN LOGIN =================

    private void adminLogin() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password."
            );

            return;
        }

        boolean success =
                new AdminDAO().login(username, password);

        if (success) {

            dispose();

            new AdminDashboard().setVisible(true);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid admin login details."
            );
        }
    }
}