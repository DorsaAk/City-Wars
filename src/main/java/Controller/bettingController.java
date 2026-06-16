package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import view.charcater;
import view.debutantMenu;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class bettingController implements Initializable{
    final String DB_URL = "jdbc:mysql://127.0.0.1:3306/oop_proj";
    final String USERNAME = "root";
    final String PASSWORD = "Dorsa_Akbari@4518";
    String query;   int rowsAffected = 0;
    @FXML
    private Label coinLabel1;
    @FXML
    private Label coinLabel2;
    @FXML
    private TextField Text1;
    @FXML
    private TextField Text2;
    @FXML
    private Button go;
    public static int currentBet;
    public static int guestBet;
    @FXML
    private void letsGo(MouseEvent mouseEvent){
        if(Text1.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, "ERROR", "Player 1 has not entered the number of coins yet.");
            return;
        }
        if(Text2.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, "ERROR", "Player 2 has not entered the number of coins yet.");
            return;
        }
        if(!Text1.getText().matches("\\d+")){
            showAlert(Alert.AlertType.ERROR, "ERROR", "Wrong format of entry for player1.");
            return;
        }
        if(!Text2.getText().matches("\\d+")) {
            showAlert(Alert.AlertType.ERROR, "ERROR", "Wrong format of entry for player 2.");
            return;
        }
        if(Controller.currentuser.getCoins() < Integer.parseInt(Text1.getText())){
            showAlert(Alert.AlertType.ERROR, "ERROR", "Player 1 does not have enough coins.");
            return;
        }
        if(modeController.guest.getCoins() < Integer.parseInt(Text2.getText())){
            showAlert(Alert.AlertType.ERROR, "ERROR", "Player 2 does not have enough coins.");
            return;
        }
        currentBet = Integer.parseInt(Text1.getText());
        guestBet = Integer.parseInt(Text2.getText());
        Controller.currentuser.setCoins(Controller.currentuser.getCoins()-currentBet);
        modeController.guest.setCoins(modeController.guest.getCoins()-guestBet);

        showAlert(Alert.AlertType.INFORMATION, "SUCCESS", "Coins have been taken from the users. Now choose your characters and start!");
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            query = "update users set coins = " + Controller.currentuser.getCoins() + " where username = \"" + Controller.currentuser.getUsername() + "\";";
            rowsAffected += statement.executeUpdate(query);
            query = "update users set coins = " + modeController.guest.getCoins() + " where username = \"" + modeController.guest.getUsername() + "\";";
            rowsAffected += statement.executeUpdate(query);
            System.out.println("Coins were taken successfully. " + rowsAffected + " row(s) affected.");
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        try {
            charcater charcater = new charcater();
            charcater.start(debutantMenu.stage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coinLabel1.setText("How many coins will you bet on " + Controller.currentuser.getUsername() + "?");
        coinLabel2.setText("How many coins will you bet on " + modeController.guest.getUsername() + "?");
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
