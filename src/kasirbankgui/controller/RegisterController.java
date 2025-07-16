package kasirbankgui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import kasirbankgui.model.RegisterService;

public class RegisterController {

    @FXML
    private TextField namaField;

    @FXML
    private PasswordField pinField;

    @FXML
    private ComboBox<String> jenisRekeningComboBox;

    @FXML
    private Label statusLabel;

    @FXML
    private void handleRegister() {
        String nama = namaField.getText();
        String pin = pinField.getText();
        String jenis = jenisRekeningComboBox.getValue();

        if (nama.isEmpty() || pin.isEmpty() || jenis == null) {
            statusLabel.setText("Semua field harus diisi!");
            return;
        }

        if (!pin.matches("\\d{4}")) {
            statusLabel.setText("PIN harus 4 digit angka!");
            return;
        }

        boolean success = RegisterService.register(nama, pin, jenis);
        if (success) {
            statusLabel.setText("Registrasi sukses! Silakan login.");
        } else {
            statusLabel.setText("Registrasi gagal. Coba lagi.");
        }
    }
}