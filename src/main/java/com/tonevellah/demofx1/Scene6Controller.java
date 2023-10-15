//view result
package com.tonevellah.demofx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static com.tonevellah.demofx1.Scene1Controller.clr;
import static com.tonevellah.demofx1.Scene1Controller.log;
import static com.tonevellah.demofx1.Scene5Controller.virkey;
public class Scene6Controller{
    @FXML
    Label wpmLabel;
    @FXML
    Label accuracyLabel;
    @FXML
    Label typedwordsLabel;
    @FXML
    Label wrongWordTypedLabel;
    @FXML
    Button button;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void displayResult(int wpmScore,int accuracyScore,int typedWords,int wrongWords){
        typedwordsLabel.setText(String.valueOf(typedWords) + " Words");
        wrongWordTypedLabel.setText(String.valueOf(wrongWords) + " Words");
        accuracyLabel.setText(String.valueOf(accuracyScore) + "%");
        wpmLabel.setText(String.valueOf((int)wpmScore));
    }
    public void tryagain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scene4.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
