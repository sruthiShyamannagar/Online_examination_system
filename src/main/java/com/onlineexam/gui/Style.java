package com.onlineexam.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public final class Style {

    public static final Color NAVY = new Color(25, 42, 86);
    public static final Color BLUE = new Color(37, 99, 235);
    public static final Color BLUE_DARK = new Color(29, 78, 216);
    public static final Color LIGHT = new Color(245, 247, 251);
    public static final Color DARK = new Color(30, 41, 59);
    public static final Color GREEN = new Color(22, 163, 74);
    public static final Color WHITE = Color.WHITE;

    public static final Font TITLE =
            new Font("Segoe UI", Font.BOLD, 28);

    public static final Font HEAD =
            new Font("Segoe UI", Font.BOLD, 20);

    public static final Font NORMAL =
            new Font("Segoe UI", Font.PLAIN, 15);

    private Style() {}

    public static void setupFrame(JFrame f, String title, int w, int h) {
        f.setTitle(title);
        f.setSize(w, h);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setBackground(LIGHT);
    }

    public static JButton button(String text) {

        JButton b = new JButton(text);

        b.setFont(new Font("Segoe UI", Font.BOLD, 15));

        // Clearly visible white text
        b.setForeground(Color.WHITE);

        // Blue background
        b.setBackground(BLUE);

        // Remove default focus border
        b.setFocusPainted(false);

        // Remove default border
        b.setBorderPainted(false);

        // Better spacing
        b.setBorder(new EmptyBorder(11, 22, 11, 22));

        // Make button opaque
        b.setOpaque(true);

        // Better cursor
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return b;
    }

    public static JTextField field(int columns) {

        JTextField t = new JTextField(columns);

        t.setFont(NORMAL);

        t.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(203, 213, 225)
                        ),
                        new EmptyBorder(8, 10, 8, 10)
                )
        );

        return t;
    }

    public static JPasswordField passwordField(int columns) {

        JPasswordField t = new JPasswordField(columns);

        t.setFont(NORMAL);

        t.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(203, 213, 225)
                        ),
                        new EmptyBorder(8, 10, 8, 10)
                )
        );

        return t;
    }
}