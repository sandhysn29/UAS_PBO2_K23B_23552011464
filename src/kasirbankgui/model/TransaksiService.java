package kasirbankgui.model;

import kasirbankgui.db.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaksiService {

    public static boolean setor(int rekeningId, double jumlah) {
        return updateSaldoDanSimpanTransaksi(rekeningId, jumlah, "setor");
    }

    public static boolean tarik(int rekeningId, double jumlah) {
        double saldo = LoginService.getSaldo(rekeningId);
        if (jumlah > saldo) return false;
        return updateSaldoDanSimpanTransaksi(rekeningId, -jumlah, "tarik");
    }

    private static boolean updateSaldoDanSimpanTransaksi(int rekeningId, double jumlah, String tipe) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            // 1. Update saldo rekening
            String updateSaldo = "UPDATE rekening SET saldo = saldo + ? WHERE id = ?";
            PreparedStatement stmt1 = conn.prepareStatement(updateSaldo);
            stmt1.setDouble(1, jumlah);
            stmt1.setInt(2, rekeningId);
            stmt1.executeUpdate();

            // 2. Catat transaksi
            String insertTransaksi = "INSERT INTO transaksi (rekening_id, tipe, jumlah) VALUES (?, ?, ?)";
            PreparedStatement stmt2 = conn.prepareStatement(insertTransaksi);
            stmt2.setInt(1, rekeningId);
            stmt2.setString(2, tipe);
            stmt2.setDouble(3, Math.abs(jumlah));
            stmt2.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("Error transaksi: " + e.getMessage());
            return false;
        }
    }
    
    public static List<Transaksi> getTransaksiList(int rekeningId) {
    List<Transaksi> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM transaksi WHERE rekening_id = ? ORDER BY tanggal DESC LIMIT 10";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, rekeningId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Timestamp tgl = rs.getTimestamp("tanggal");
                String tipe = rs.getString("tipe");
                double jumlah = rs.getDouble("jumlah");
                list.add(new Transaksi(tgl, tipe, jumlah));
            }
        } catch (SQLException e) {
            System.out.println("Error get transaksi: " + e.getMessage());
        }
        return list;
    }
}