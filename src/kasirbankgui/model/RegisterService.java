package kasirbankgui.model;

import kasirbankgui.db.DatabaseConnection;

import java.sql.*;

public class RegisterService {

    public static boolean register(String nama, String pin, String jenisRekening) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Insert ke rekening dulu
            String insertRekening = "INSERT INTO rekening (jenis, saldo) VALUES (?, 0)";
            PreparedStatement stmt1 = conn.prepareStatement(insertRekening, Statement.RETURN_GENERATED_KEYS);
            stmt1.setString(1, jenisRekening);
            stmt1.executeUpdate();

            // Ambil ID rekening yang baru dibuat
            ResultSet rs = stmt1.getGeneratedKeys();
            if (rs.next()) {
                int rekeningId = rs.getInt(1);

                // Insert ke nasabah
                String insertNasabah = "INSERT INTO nasabah (nama, rekening_id, pin) VALUES (?, ?, ?)";
                PreparedStatement stmt2 = conn.prepareStatement(insertNasabah);
                stmt2.setString(1, nama);
                stmt2.setInt(2, rekeningId);
                stmt2.setString(3, pin);
                stmt2.executeUpdate();

                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error register: " + e.getMessage());
        }
        return false;
    }
}