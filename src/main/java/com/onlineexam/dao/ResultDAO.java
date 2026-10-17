package com.onlineexam.dao;

import com.onlineexam.util.DBConnection;

import java.sql.*;

public class ResultDAO {
    public boolean saveResult(int studentId, int examId, int score, int total) {
        double percentage = total == 0 ? 0 : (score * 100.0 / total);
        String sql = "INSERT INTO results(student_id,exam_id,score,total_questions,percentage) VALUES(?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, examId);
            ps.setInt(3, score);
            ps.setInt(4, total);
            ps.setDouble(5, percentage);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
