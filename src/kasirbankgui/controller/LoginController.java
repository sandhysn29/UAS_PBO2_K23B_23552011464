package kasirbankgui.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kasirbankgui.model.LoginService;
import kasirbankgui.model.LoginService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kasirbankgui.controller.HomeController;


public class LoginController {

    @FXML
    private TextField idField;

    @FXML
    private PasswordField pinField;

    @FXML
    private Label errorLabel;

    @FXML
    private void handleLogin(ActionEvent event) {
        
        System.out.println("ID INPUT: " + idField.getText());
        System.out.println("PIN INPUT: " + pinField.getText());

        String idText = idField.getText();
        String pin = pinField.getText();

        try {
            int id = Integer.parseInt(idText);
            boolean success = LoginService.login(id, pin);
            System.out.println("Login Result: " + success);

            if (success) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/kasirbankgui/view/Home.fxml"));
                Parent root = loader.load();

                String nama = LoginService.getNamaNasabah(id);
                String jenis = LoginService.getJenisRekening(id);
                double saldo = LoginService.getSaldo(id);

                HomeController controller = loader.getController();
                controller.setData(nama, jenis, id, saldo);

                Stage stage = (Stage) idField.getScene().getWindow();
                stage.setScene(new Scene(root));

            } else {
                errorLabel.setText("ID atau PIN salah!");
            }
        } catch (NumberFormatException e) {
            errorLabel.setText("ID harus berupa angka!");
        } catch (IOException e) {
            e.printStackTrace();
            errorLabel.setText("Terjadi kesalahan saat memuat tampilan.");
        }
    }

    
    @FXML
    private void goToRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/kasirbankgui/view/Register.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) idField.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}