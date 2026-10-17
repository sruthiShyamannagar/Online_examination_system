package com.onlineexam.gui;

import com.onlineexam.dao.StudentDAO;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private final JTextField name = Style.field(22);
    private final JTextField email = Style.field(22);
    private final JPasswordField password = Style.passwordField(22);

    public RegisterFrame() {
        Style.setupFrame(this, "Student Registration", 500, 400);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(8, 8, 8, 8);
        g.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Student Registration");
        title.setFont(Style.TITLE);
        title.setForeground(Style.NAVY);
        g.gridx=0; g.gridy=0; g.gridwidth=2; panel.add(title,g);

        addRow(panel,g,1,"Full Name",name);
        addRow(panel,g,2,"Email",email);
        addRow(panel,g,3,"Password",password);

        JButton register = Style.button("Create Account");
        JButton back = Style.button("Back to Login");
        g.gridx=0; g.gridy=4; g.gridwidth=1; panel.add(register,g);
        g.gridx=1; panel.add(back,g);

        add(panel);
        register.addActionListener(e -> register());
        back.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private void addRow(JPanel p, GridBagConstraints g, int row, String label, Component field) {
        g.gridx=0; g.gridy=row; g.gridwidth=1;
        p.add(new JLabel(label),g);
        g.gridx=1; p.add(field,g);
    }

    private void register() {
        if (name.getText().trim().isEmpty() || email.getText().trim().isEmpty()
                || new String(password.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(this,"Please fill all fields.");
            return;
        }
        boolean ok = new StudentDAO().register(name.getText().trim(), email.getText().trim(),
                new String(password.getPassword()));
        if (ok) {
            JOptionPane.showMessageDialog(this,"Registration successful. Please login.");
            dispose();
            new LoginFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this,"Registration failed. Email may already exist.");
        }
    }
}
