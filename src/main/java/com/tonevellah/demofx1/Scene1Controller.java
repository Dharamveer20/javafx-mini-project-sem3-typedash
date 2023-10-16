//homepage
package com.tonevellah.demofx1;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class Scene1Controller {
    //@FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    static public int clr=0; // clr = 0 bright mode, clr = 1 night mode
    static public int lvl=1; // level 1,2,3
    static public int car=1; // car 1,2,3
    static public int log=0; // log = 0 not logged in log in 1 logged in

    // Opening the login GUI
    public void loginpage(ActionEvent event) throws IOException {
        System.out.println(clr);
        root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Opening the Signup GUI
    public void signuppage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scene3.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void exit(ActionEvent e) {
        System.exit(0);
    }
}
