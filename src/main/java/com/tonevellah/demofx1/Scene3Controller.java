//signup GUI controller
package com.tonevellah.demofx1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.*;
import java.io.*;

import static com.tonevellah.demofx1.Scene1Controller.clr;
import static com.tonevellah.demofx1.Scene1Controller.log;
public class Scene3Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField uname;
    @FXML
    private PasswordField pass;
    @FXML
    private Label warnin;
    public String username;
    public String password;

    public void menu(ActionEvent event) throws IOException {
        username=uname.getText();
        password=pass.getText();

        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/typerush", "root","anappleaday.?20");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");

            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                warnin.setText("Name already taken!");
                warnin.setVisible(true);
                System.out.println("user exists");

                uname.setText("");
                pass.setText("");
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users(username,password) VALUES(?,?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.executeUpdate();

//                Statement stm = connection.createStatement();
                log=1;
                root = FXMLLoader.load(getClass().getResource("Scene4.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally { // Closing All Resources (Connections and all)
            try {
                CloseResources closingResources = new CloseResources();
                closingResources.closeResources();
                System.out.println("All resources closed in Scene3Controller.");
            } catch (Exception se){
                System.out.println(se);
                System.out.println("Error while closing connection in SCene 3 controller.");
            }
        }
    }
    public void goback(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}