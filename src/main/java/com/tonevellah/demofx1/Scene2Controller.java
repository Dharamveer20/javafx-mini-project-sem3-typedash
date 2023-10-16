//login logic
package com.tonevellah.demofx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

import static com.tonevellah.demofx1.Scene1Controller.log;

public class Scene2Controller {
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
//        System.out.println(username +" "+ password);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PreparedStatement psInsert = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/typerush", "root", "anappleaday.?20");
            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                warnin.setText("Username not found!");
                warnin.setVisible(true);
                System.out.println("User not found");
                uname.setText("");
                pass.setText("");
            } else {
                while(resultSet.next()){
                    String retrievedPassword = resultSet.getString("password");

                    if(retrievedPassword.equals(password)){
                        try {
                            log = 1;
                            root = FXMLLoader.load(getClass().getResource("Scene4.fxml"));
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        } catch (Exception e){
//                            System.out.println("Error in Line 97 scene2Controller.");
                            System.out.println(e);
                        }
                    }
                    else{
                        warnin.setText("Password did not match!");
                        warnin.setVisible(true);
                        System.out.println("password did not match");
                        pass.setText("");
                    }
                }
            }
        } catch (SQLException se) {
            System.out.println("Error while logging in");
            se.printStackTrace();
        } finally { // Closing Connections and all resources
            try {
                CloseResources closingResources = new CloseResources();
                closingResources.closeResources();
                System.out.println("All resources closed.");
            } catch (Exception e){
                System.out.println(e);
                System.out.println("Error while closing connection in Scene 2 controller.");
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
