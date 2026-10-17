package com.onlineexam.gui;

import java.awt.Color;
import java.awt.Component;
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

public class AdminRegisterFrame extends JFrame {

    private final JTextField username = Style.field(22);
    private final JPasswordField password = Style.passwordField(22);
    private final JPasswordField confirmPassword = Style.passwordField(22);

    public AdminRegisterFrame() {

        Style.setupFrame(this, "Admin Registration", 520, 430);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        );

        GridBagConstraints g = new GridBagConstraints();

        g.insets = new Insets(8, 8, 8, 8);
        g.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel(
                "ADMIN REGISTRATION",
                SwingConstants.CENTER
        );

        title.setFont(Style.TITLE);
        title.setForeground(Style.NAVY);

        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;

        panel.add(title, g);

        addRow(panel, g, 1, "Username", username);
        addRow(panel, g, 2, "Password", password);
        addRow(panel, g, 3, "Confirm Password", confirmPassword);

        JButton register = Style.button("Register Admin");
        JButton back = Style.button("Back to Login");

        g.gridy = 4;
        g.gridx = 0;
        g.gridwidth = 1;

        panel.add(register, g);

        g.gridx = 1;

        panel.add(back, g);

        add(panel);

        register.addActionListener(e -> registerAdmin());

        back.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private void addRow(
            JPanel panel,
            GridBagConstraints g,
            int row,
            String label,
            Component field
    ) {

        g.gridx = 0;
        g.gridy = row;
        g.gridwidth = 1;

        panel.add(new JLabel(label), g);

        g.gridx = 1;

        panel.add(field, g);
    }

    private void registerAdmin() {

        String user = username.getText().trim();
        String pass = new String(password.getPassword());
        String confirm = new String(confirmPassword.getPassword());

        if (user.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields."
            );

            return;
        }

        if (!pass.equals(confirm)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Passwords do not match."
            );

            return;
        }

        boolean success =
                new AdminDAO().register(user, pass);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Admin registration successful!"
            );

            dispose();

            new LoginFrame().setVisible(true);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Registration failed. Username may already exist."
            );
        }
    }
}