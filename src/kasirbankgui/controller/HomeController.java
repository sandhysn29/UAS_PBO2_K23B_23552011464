package kasirbankgui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import kasirbankgui.model.TransaksiService;
import kasirbankgui.model.LoginService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import kasirbankgui.model.Transaksi;
import java.util.List;

public class HomeController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label saldoLabel;

    @FXML
    private TextField inputField;

    @FXML
    private Label resultLabel;
    
    @FXML
    private TableView<Transaksi> transaksiTable;

    @FXML
    private TableColumn<Transaksi, String> tipeCol;

    @FXML
    private TableColumn<Transaksi, Double> jumlahCol;

    @FXML
    private TableColumn<Transaksi, String> tanggalCol;


    private String nama;
    private String jenis;
    private int rekeningId;
    private double saldo;

    public void setData(String nama, String jenis, int rekeningId, double saldo) {
        this.nama = nama;
        this.jenis = jenis;
        this.rekeningId = rekeningId;
        this.saldo = saldo;

        welcomeLabel.setText("Selamat datang, " + nama + " (" + jenis + ")");
        updateSaldo();
        loadTransaksiTable();
    }

    private void updateSaldo() {
        saldo = LoginService.getSaldo(rekeningId); // ambil terbaru
        saldoLabel.setText("Saldo: Rp " + saldo);
    }

    @FXML
    private void handleSetor() {
        try {
            double jumlah = Double.parseDouble(inputField.getText());
            boolean sukses = TransaksiService.setor(rekeningId, jumlah);

            if (sukses) {
                saldo += jumlah;
                updateSaldo();
                resultLabel.setText("Setor Rp" + jumlah + " berhasil.");
                loadTransaksiTable();
            } else {
                resultLabel.setText("Gagal setor.");
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Masukkan angka yang valid.");
        }
    }

    @FXML
    private void handleTarik() {
        try {
            double jumlah = Double.parseDouble(inputField.getText());
            if (jumlah > saldo) {
                resultLabel.setText("Saldo tidak cukup.");
                return;
            }

            boolean sukses = TransaksiService.tarik(rekeningId, jumlah);
            if (sukses) {
                saldo -= jumlah;
                updateSaldo();
                resultLabel.setText("Tarik Rp" + jumlah + " berhasil.");
                loadTransaksiTable();
            } else {
                resultLabel.setText("Gagal tarik.");
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Masukkan angka yang valid.");
        }
    }

    @FXML
    private void handleBunga() {
        double bunga = jenis.equalsIgnoreCase("Tabungan") ? saldo * 0.02 : saldo * 0.01;
        resultLabel.setText("Bunga bulan ini: Rp " + bunga);
    }
    
    private void loadTransaksiTable() {
        tipeCol.setCellValueFactory(new PropertyValueFactory<>("tipe"));
        jumlahCol.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        tanggalCol.setCellValueFactory(new PropertyValueFactory<>("tanggal"));

        List<Transaksi> transaksiList = TransaksiService.getTransaksiList(rekeningId);
        ObservableList<Transaksi> observableList = FXCollections.observableArrayList(transaksiList);
        transaksiTable.setItems(observableList);
    }

}