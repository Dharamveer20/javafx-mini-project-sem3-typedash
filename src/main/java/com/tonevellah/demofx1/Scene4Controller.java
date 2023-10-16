//after signing or logging in
package com.tonevellah.demofx1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import static com.tonevellah.demofx1.Scene1Controller.*;
public class Scene4Controller {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Text advc;
    @FXML
    Text uname;
    public void tips(String str){
        advc.setText(str);
         }
    public void playGame(ActionEvent e) throws IOException { // On Action of WPM rush

        root = FXMLLoader.load(getClass().getResource("Scene5.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void exit(ActionEvent e)throws IOException {
        log=0;
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
