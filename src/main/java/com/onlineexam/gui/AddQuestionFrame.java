package com.onlineexam.gui;

import com.onlineexam.dao.ExamDAO;
import com.onlineexam.model.Exam;

import javax.swing.*;
import java.awt.*;

public class AddQuestionFrame extends JFrame {
    private final Exam exam;
    private final AdminDashboard dashboard;
    private final JTextArea question = new JTextArea(4,30);
    private final JTextField a = Style.field(25);
    private final JTextField b = Style.field(25);
    private final JTextField c = Style.field(25);
    private final JTextField d = Style.field(25);
    private final JComboBox<String> correct = new JComboBox<>(new String[]{"A","B","C","D"});

    public AddQuestionFrame(Exam exam, AdminDashboard dashboard) {
        this.exam = exam;
        this.dashboard = dashboard;

        Style.setupFrame(this,"Add Question",620,520);

        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createEmptyBorder(20,25,20,25));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(7,7,7,7);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.WEST;

        JLabel title = new JLabel("Add MCQ to: " + exam.getExamName());
        title.setFont(Style.HEAD);
        title.setForeground(Style.NAVY);
        g.gridx=0; g.gridy=0; g.gridwidth=2; p.add(title,g);

        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(Style.NORMAL);

        addRow(p,g,1,"Question",new JScrollPane(question));
        addRow(p,g,2,"Option A",a);
        addRow(p,g,3,"Option B",b);
        addRow(p,g,4,"Option C",c);
        addRow(p,g,5,"Option D",d);
        addRow(p,g,6,"Correct Answer",correct);

        JButton save = Style.button("Save Question");
        g.gridx=0; g.gridy=7; g.gridwidth=2; p.add(save,g);

        add(p);

        save.addActionListener(e -> saveQuestion());
    }

    private void addRow(JPanel p, GridBagConstraints g, int row, String label, Component component) {
        g.gridx=0; g.gridy=row; g.gridwidth=1;
        p.add(new JLabel(label),g);
        g.gridx=1; p.add(component,g);
    }

    private void saveQuestion() {
        if (question.getText().trim().isEmpty() || a.getText().trim().isEmpty()
                || b.getText().trim().isEmpty() || c.getText().trim().isEmpty()
                || d.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Fill all question fields.");
            return;
        }

        boolean ok = new ExamDAO().addQuestion(exam.getExamId(),
                question.getText().trim(),a.getText().trim(),b.getText().trim(),
                c.getText().trim(),d.getText().trim(),
                correct.getSelectedItem().toString());

        if (ok) {
            JOptionPane.showMessageDialog(this,"Question added successfully.");
            dispose();
            dashboard.toFront();
        } else {
            JOptionPane.showMessageDialog(this,"Could not add question.");
        }
    }
}
