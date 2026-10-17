package com.onlineexam.gui;

import com.onlineexam.dao.ExamDAO;
import com.onlineexam.dao.ResultDAO;
import com.onlineexam.model.Exam;
import com.onlineexam.model.Question;
import com.onlineexam.model.Student;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ExamFrame extends JFrame {
    private final Student student;
    private final Exam exam;
    private final List<Question> questions;
    private final String[] answers;
    private int current = 0;
    private int secondsLeft;
    private final JLabel timer = new JLabel();
    private final JLabel number = new JLabel();
    private final JTextArea question = new JTextArea();
    private final JRadioButton[] options = {
            new JRadioButton(), new JRadioButton(), new JRadioButton(), new JRadioButton()
    };
    private final ButtonGroup group = new ButtonGroup();
    private final Timer countdown;

    public ExamFrame(Student student, Exam exam) {
        this.student = student;
        this.exam = exam;
        this.questions = new ExamDAO().getQuestions(exam.getExamId());
        this.answers = new String[questions.size()];
        this.secondsLeft = exam.getDurationMinutes() * 60;

        Style.setupFrame(this, exam.getExamName(), 850, 620);

        JPanel root = new JPanel(new BorderLayout(15,15));
        root.setBackground(Style.LIGHT);
        root.setBorder(BorderFactory.createEmptyBorder(20,25,20,25));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel title = new JLabel(exam.getExamName());
        title.setFont(Style.TITLE);
        title.setForeground(Style.NAVY);

        timer.setFont(new Font("Segoe UI",Font.BOLD,18));
        timer.setForeground(Color.RED);
        header.add(title,BorderLayout.WEST);
        header.add(timer,BorderLayout.EAST);

        JPanel card = new JPanel(new BorderLayout(10,10));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        number.setFont(Style.HEAD);
        question.setFont(new Font("Segoe UI",Font.BOLD,18));
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setEditable(false);
        question.setOpaque(false);
        question.setRows(4);

        JPanel qtop = new JPanel(new BorderLayout());
        qtop.setOpaque(false);
        qtop.add(number,BorderLayout.NORTH);
        qtop.add(question,BorderLayout.CENTER);

        JPanel opts = new JPanel();
        opts.setOpaque(false);
        opts.setLayout(new BoxLayout(opts,BoxLayout.Y_AXIS));
        for (JRadioButton r : options) {
            r.setFont(Style.NORMAL);
            r.setOpaque(false);
            group.add(r);
            opts.add(r);
        }

        card.add(qtop,BorderLayout.NORTH);
        card.add(opts,BorderLayout.CENTER);

        JButton previous = Style.button("Previous");
        JButton next = Style.button("Next");
        JButton submit = Style.button("Submit Exam");

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setOpaque(false);
        bottom.add(previous);
        bottom.add(next);
        bottom.add(submit);

        root.add(header,BorderLayout.NORTH);
        root.add(card,BorderLayout.CENTER);
        root.add(bottom,BorderLayout.SOUTH);
        add(root);

        previous.addActionListener(e -> {
            saveAnswer();
            if (current > 0) { current--; showQuestion(); }
        });
        next.addActionListener(e -> {
            saveAnswer();
            if (current < questions.size()-1) { current++; showQuestion(); }
        });
        submit.addActionListener(e -> submitExam());

        countdown = new Timer(1000, e -> tick());
        if (questions.isEmpty()) {
            JOptionPane.showMessageDialog(this,"This exam has no questions.");
            dispose();
            new StudentDashboard(student).setVisible(true);
        } else {
            showQuestion();
            countdown.start();
        }
    }

    private void showQuestion() {
        Question q = questions.get(current);
        number.setText("Question " + (current+1) + " of " + questions.size());
        question.setText(q.getQuestionText());
        options[0].setText("A. " + q.getOptionA());
        options[1].setText("B. " + q.getOptionB());
        options[2].setText("C. " + q.getOptionC());
        options[3].setText("D. " + q.getOptionD());
        group.clearSelection();

        if (answers[current] != null) {
            int index = "ABCD".indexOf(answers[current]);
            if (index >= 0) options[index].setSelected(true);
        }
    }

    private void saveAnswer() {
        for (int i=0;i<4;i++) {
            if (options[i].isSelected()) {
                answers[current] = "ABCD".substring(i,i+1);
                return;
            }
        }
        answers[current] = null;
    }

    private void tick() {
        secondsLeft--;
        int min = secondsLeft / 60;
        int sec = secondsLeft % 60;
        timer.setText(String.format("Time Left: %02d:%02d",min,sec));
        if (secondsLeft <= 0) {
            countdown.stop();
            submitExam();
        }
    }

    private void submitExam() {
        saveAnswer();
        countdown.stop();

        int score = 0;
        for (int i=0;i<questions.size();i++) {
            if (answers[i] != null && answers[i].equalsIgnoreCase(questions.get(i).getCorrectAnswer())) {
                score++;
            }
        }

        new ResultDAO().saveResult(student.getStudentId(), exam.getExamId(), score, questions.size());

        dispose();
        new ResultFrame(student, exam, score, questions.size()).setVisible(true);
    }
}
