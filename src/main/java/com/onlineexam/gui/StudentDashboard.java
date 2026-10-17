package com.onlineexam.gui;

import com.onlineexam.model.Exam;
import com.onlineexam.model.Student;
import com.onlineexam.dao.ExamDAO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentDashboard extends JFrame {
    private final Student student;
    private final DefaultListModel<Exam> model = new DefaultListModel<>();
    private final JList<Exam> examList = new JList<>(model);

    public StudentDashboard(Student student) {
        this.student = student;
        Style.setupFrame(this, "Student Dashboard", 760, 540);

        JPanel root = new JPanel(new BorderLayout(15,15));
        root.setBackground(Style.LIGHT);
        root.setBorder(BorderFactory.createEmptyBorder(20,25,20,25));

        JLabel welcome = new JLabel("Welcome, " + student.getName());
        welcome.setFont(Style.TITLE);
        welcome.setForeground(Style.NAVY);

        JLabel info = new JLabel("Select an examination and start your test.");
        info.setFont(Style.NORMAL);

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(welcome, BorderLayout.NORTH);
        top.add(info, BorderLayout.SOUTH);

        examList.setFont(Style.NORMAL);
        examList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(examList);

        JButton refresh = Style.button("Refresh Exams");
        JButton start = Style.button("Start Selected Exam");
        JButton logout = Style.button("Logout");

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setOpaque(false);
        bottom.add(refresh);
        bottom.add(start);
        bottom.add(logout);

        root.add(top,BorderLayout.NORTH);
        root.add(scroll,BorderLayout.CENTER);
        root.add(bottom,BorderLayout.SOUTH);
        add(root);

        refresh.addActionListener(e -> loadExams());
        start.addActionListener(e -> startExam());
        logout.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        loadExams();
    }

    private void loadExams() {
        model.clear();
        List<Exam> exams = new ExamDAO().getAllExams();
        for (Exam exam : exams) model.addElement(exam);
    }

    private void startExam() {
        Exam selected = examList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this,"Please select an exam.");
            return;
        }
        dispose();
        new ExamFrame(student, selected).setVisible(true);
    }
}
