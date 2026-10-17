package com.onlineexam.gui;

import com.onlineexam.model.Exam;
import com.onlineexam.model.Student;

import javax.swing.*;
import java.awt.*;

public class ResultFrame extends JFrame {
    public ResultFrame(Student student, Exam exam, int score, int total) {
        Style.setupFrame(this,"Examination Result",550,430);

        double percentage = total == 0 ? 0 : score * 100.0 / total;

        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createEmptyBorder(30,40,30,40));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(9,9,9,9);

        JLabel title = new JLabel("EXAMINATION RESULT");
        title.setFont(Style.TITLE);
        title.setForeground(Style.NAVY);
        g.gridx=0; g.gridy=0; g.gridwidth=2; p.add(title,g);

        addLine(p,g,1,"Student",student.getName());
        addLine(p,g,2,"Exam",exam.getExamName());
        addLine(p,g,3,"Correct Answers",score + " / " + total);
        addLine(p,g,4,"Percentage",String.format("%.2f%%",percentage));

        JLabel status = new JLabel(percentage >= 40 ? "PASS" : "FAIL");
        status.setFont(new Font("Segoe UI",Font.BOLD,24));
        status.setForeground(percentage >= 40 ? Style.GREEN : Color.RED);
        g.gridx=0; g.gridy=5; g.gridwidth=2; p.add(status,g);

        JButton dashboard = Style.button("Back to Dashboard");
        g.gridy=6; p.add(dashboard,g);

        add(p);
        dashboard.addActionListener(e -> {
            dispose();
            new StudentDashboard(student).setVisible(true);
        });
    }

    private void addLine(JPanel p, GridBagConstraints g, int row, String a, String b) {
        g.gridwidth=1; g.gridx=0; g.gridy=row;
        JLabel l = new JLabel(a + ":");
        l.setFont(Style.HEAD);
        p.add(l,g);
        g.gridx=1;
        JLabel v = new JLabel(b);
        v.setFont(Style.NORMAL);
        p.add(v,g);
    }
}
