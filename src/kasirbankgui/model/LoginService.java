package kasirbankgui.model;

import kasirbankgui.db.DatabaseConnection;
import java.sql.*;

public class LoginService {
    public static boolean login(int rekeningId, String pin) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM nasabah WHERE rekening_id = ? AND pin = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, rekeningId);
            stmt.setString(2, pin);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
            return false;
        }
    }
    
    public static String getNamaNasabah(int rekeningId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT nama FROM nasabah WHERE rekening_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, rekeningId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) return rs.getString("nama");
        } catch (SQLException e) {
            System.out.println("Error get nama: " + e.getMessage());
        }
        return null;
    }

    public static String getJenisRekening(int rekeningId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT jenis FROM rekening WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, rekeningId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) return rs.getString("jenis");
        } catch (SQLException e) {
            System.out.println("Error get jenis rekening: " + e.getMessage());
        }
        return null;
    }

    public static double getSaldo(int rekeningId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT saldo FROM rekening WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, rekeningId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) return rs.getDouble("saldo");
        } catch (SQLException e) {
            System.out.println("Error get saldo: " + e.getMessage());
        }
        return 0;
    }
}