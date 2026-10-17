package com.onlineexam.gui;

import com.onlineexam.dao.ExamDAO;
import com.onlineexam.model.Exam;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {
    private final DefaultListModel<Exam> model = new DefaultListModel<>();
    private final JList<Exam> exams = new JList<>(model);

    public AdminDashboard() {
        Style.setupFrame(this,"Admin Dashboard",800,560);

        JPanel root = new JPanel(new BorderLayout(15,15));
        root.setBackground(Style.LIGHT);
        root.setBorder(BorderFactory.createEmptyBorder(20,25,20,25));

        JLabel title = new JLabel("ADMIN DASHBOARD");
        title.setFont(Style.TITLE);
        title.setForeground(Style.NAVY);

        JLabel info = new JLabel("Create exams and add MCQ questions.");
        info.setFont(Style.NORMAL);

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(title,BorderLayout.NORTH);
        top.add(info,BorderLayout.SOUTH);

        exams.setFont(Style.NORMAL);
        JScrollPane scroll = new JScrollPane(exams);

        JButton addExam = Style.button("Create Exam");
        JButton addQuestion = Style.button("Add Question");
        JButton refresh = Style.button("Refresh");
        JButton logout = Style.button("Logout");

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setOpaque(false);
        bottom.add(addExam);
        bottom.add(addQuestion);
        bottom.add(refresh);
        bottom.add(logout);

        root.add(top,BorderLayout.NORTH);
        root.add(scroll,BorderLayout.CENTER);
        root.add(bottom,BorderLayout.SOUTH);
        add(root);

        addExam.addActionListener(e -> createExam());
        addQuestion.addActionListener(e -> addQuestion());
        refresh.addActionListener(e -> load());
        logout.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        load();
    }

    private void load() {
        model.clear();
        for (Exam e : new ExamDAO().getAllExams()) model.addElement(e);
    }

    private void createExam() {
        JTextField name = Style.field(20);
        JTextField subject = Style.field(20);
        JTextField duration = Style.field(20);

        JPanel p = new JPanel(new GridLayout(3,2,8,8));
        p.add(new JLabel("Exam Name")); p.add(name);
        p.add(new JLabel("Subject")); p.add(subject);
        p.add(new JLabel("Duration (minutes)")); p.add(duration);

        int result = JOptionPane.showConfirmDialog(this,p,"Create Exam",
                JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int minutes = Integer.parseInt(duration.getText().trim());
                int id = new ExamDAO().addExam(name.getText().trim(),subject.getText().trim(),minutes);
                if (id > 0) {
                    JOptionPane.showMessageDialog(this,"Exam created successfully.");
                    load();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,"Duration must be a number.");
            }
        }
    }

    private void addQuestion() {
        Exam selected = exams.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this,"Select an exam first.");
            return;
        }
        new AddQuestionFrame(selected, this).setVisible(true);
    }
}
