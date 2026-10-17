package com.onlineexam.dao;

import com.onlineexam.model.Exam;
import com.onlineexam.model.Question;
import com.onlineexam.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamDAO {
    public List<Exam> getAllExams() {
        List<Exam> exams = new ArrayList<>();
        String sql = "SELECT * FROM exams ORDER BY exam_id DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                exams.add(new Exam(rs.getInt("exam_id"),
                        rs.getString("exam_name"),
                        rs.getString("subject"),
                        rs.getInt("duration_minutes")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exams;
    }

    public int addExam(String name, String subject, int duration) {
        String sql = "INSERT INTO exams(exam_name,subject,duration_minutes) VALUES(?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setString(2, subject);
            ps.setInt(3, duration);
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean addQuestion(int examId, String question, String a, String b,
                               String c, String d, String correct) {
        String sql = "INSERT INTO questions(exam_id,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES(?,?,?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, examId);
            ps.setString(2, question);
            ps.setString(3, a);
            ps.setString(4, b);
            ps.setString(5, c);
            ps.setString(6, d);
            ps.setString(7, correct);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Question> getQuestions(int examId) {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT * FROM questions WHERE exam_id=? ORDER BY question_id";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, examId);
            ResultSet rs = execute(ps);
            while (rs.next()) {
                list.add(new Question(rs.getInt("question_id"), rs.getInt("exam_id"),
                        rs.getString("question_text"), rs.getString("option_a"),
                        rs.getString("option_b"), rs.getString("option_c"),
                        rs.getString("option_d"), rs.getString("correct_answer")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
