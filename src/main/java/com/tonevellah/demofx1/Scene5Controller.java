//Choose Level Car color and start game controller
package com.tonevellah.demofx1;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import static com.tonevellah.demofx1.Scene1Controller.lvl;
import static com.tonevellah.demofx1.Scene1Controller.car;
import static com.tonevellah.demofx1.Scene1Controller.log;

public class Scene5Controller implements Initializable {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public RadioButton rButton1;
    @FXML
    public RadioButton rButton2;
    @FXML
    public RadioButton rButton3;
    @FXML
    private ChoiceBox<String> myChoiceBox=new ChoiceBox<String>();
    private String[] cars = {"Yellow","Red","Pink"};
    static public int virkey=0;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        myChoiceBox.getItems().addAll(cars);
        myChoiceBox.setOnAction(this::getCar);
        myChoiceBox.setStyle("-fx-font: 20 arial;");
    }
    public void getCar(ActionEvent event) {

        String mytime = myChoiceBox.getValue();
        if(mytime=="Yellow") car=1;
        else if(mytime=="Red") car=2;
        else if(mytime=="Pink") car=3;
    }
    // Deciding the level of the Game
    public void getLevel(ActionEvent event) {
        if(rButton1.isSelected()) lvl=1;
        else if(rButton2.isSelected()) lvl=2;
        else if(rButton3.isSelected()) lvl=3;
    }
    public void gotogame(ActionEvent e) throws IOException{ // On Action of Start Game Button
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Gamecontroller gamecontroller = loader.getController();
            gamecontroller.takeGivenLine();
            gamecontroller.setfirstword();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ke){
            System.out.println(ke);
            System.out.println("Error in line 95 of Scenecontroller 5");
        }
    }

    public void goback(ActionEvent event) throws IOException {
        System.out.println(log);
        root = FXMLLoader.load(getClass().getResource("Scene4.fxml")); // Going to 'after signing or logging in' GUI
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
