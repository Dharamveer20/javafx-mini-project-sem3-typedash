// Typing Game Scene Controller
package com.tonevellah.demofx1;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.tonevellah.demofx1.Scene1Controller.*;
import static com.tonevellah.demofx1.Scene1Controller.car; // Importing car (value = 1 or 2 or 3). Chosen by the user.
public class Gamecontroller {
    private int wordCounter = 0;
    private int first = 0;
    int fir = 0;
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public Text seconds; // Displaying Seconds
    @FXML
    private Text wordsPerMin; // displaying WPM per word
    @FXML
    private Text accuracy; // Display accuracy
    @FXML
    private Text programWord; // current word that is expected by user to type (hidden behind textflow (Text to be shown to type in the GUI))
    @FXML
    private Text secondProgramWord; // hidden behind textflow (Text to be shown to type in the GUI)
    @FXML
    private Text thirdProgramWord; // hidden behind textflow (Text to be shown to type in the GUI)
    @FXML
    private Text secpreviousProgramWord; // previous word ka bhi previous word (hidden behind textflow (Text to be shown to type in the GUI))
    @FXML
    private Text previousProgramWord; // hidden behind textflow (Text to be shown to type in the GUI)
    @FXML
    private TextFlow textflow; // Text to be shown to type in the GUI
    @FXML
    private TextField userWord; // Actual word entered by user.
    @FXML
    private ImageView correct; // correct image whose visibility will be set to false in setFirstWord method.
    @FXML
    private ImageView wrong; // wrong image whose visibility will be set to false in setFirstWord method.
    @FXML
    private Button playAgain; // play again button whose visibility will be set to false in setFirstWord method.
    @FXML
    private Text greyText; // setting previous to previous word to gray.
    @FXML
    private Text blueText; // setting current word to type to color blue.
    @FXML
    private Text greenText; // Setting previous correct typed to green
    @FXML
    private Text lastText; // This will select last word typed by the user(aage we will set it's color to green if correct. if wrong -> pink)
    @FXML
    private ImageView imgview; // will set imgview to car image in setFurstWord method.
    private double x1;
    private double y1;
    @FXML
    private Label won;
    private long pretime = 0;
    Instant start,end;

// Alloting sentence that will be printed in the GUI
    public String givenstring =takeGivenLine();
    public String takeGivenLine(){
        int max=50,min=40;
        max=lvl;
        min = max - 1;
        max *= 15;
        min *= 15;
        String st = "";
        int i = 0;
        int ran = (int) Math.floor(Math.random() * (max - min + 1) + min);

        try {
            File file = new File("C:\\Users\\Ganesh\\OneDrive\\Documents\\Dharam\\miniproject\\resources\\Lines.txt");
            Scanner fileinput = new Scanner(file);
            while (fileinput.hasNext()) {
                String s = fileinput.nextLine();
                if (i >= ran) {
                    st += s;
                }
                i++;
            }
            fileinput.close();
        }
        catch (Exception e) {
            System.out.println("Error in GameController while Writing into the the");
            System.out.println(e);
        }
        return st;
    }
    String[] givenwords = givenstring.split(" "); // Creating array which will store all words from the text that will be displayed to type

    // In this function we are setting programWord to given word to type
    public void setfirstword() {
        secpreviousProgramWord.setText("start");
        previousProgramWord.setText("here:- ");
        programWord.setText(givenwords[0]);
        secondProgramWord.setText(givenwords[1]);
        thirdProgramWord.setText(givenwords[2]);

        greyText=new Text("");
        greyText.setFill(Color.GREY);
        blueText = new Text(givenwords[0]); // Assigning current word to type to blueText
        blueText.setFill(Color.BLUE);

        String st=" "; // This will store all the sentences after the first word.
        for(int i=1;i<35;i++){
            st+=givenwords[i] + " ";
        }
        greenText = new Text(st); // assigning greenText all the sentences after the first word i.e pogramWord
        greenText.setFill(Color.BLACK);

        textflow.getChildren().addAll(greyText,blueText, greenText);
        textflow.setStyle("-fx-font: 28 arial;");
        textflow.setPrefWidth(700);

        textflow.setPadding(new Insets(15, 15, 15, 15));

        start = Instant.now();

        playAgain.setVisible(false);
        wrong.setVisible(false);
        correct.setVisible(false);
//        System.out.println("car "+ car);
        if(car==1) imgview.setImage(new Image ("C:\\Users\\Ganesh\\OneDrive\\Documents\\Dharam\\miniproject\\resources\\car_yellow.png"));
        else if(car==2) imgview.setImage(new Image ("C:\\Users\\Ganesh\\OneDrive\\Documents\\Dharam\\miniproject\\resources\\car_red.png"));
        else if(car==3)imgview.setImage(new Image ("C:\\Users\\Ganesh\\OneDrive\\Documents\\Dharam\\miniproject\\resources\\car_pink.png"));

        won.setVisible(false);
    }

    // on key pressed (interface where user types the word) startGame method executes. Note: fx id is set to userWord
    public void startGame(KeyEvent ke) throws IOException{
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();
        }

        if (ke.getCode().equals(KeyCode.SPACE)) {
            int colf=5;
            System.out.println(first);
            String s = userWord.getText();
            if(fir>=1)s=s.substring(1,s.length());
            fir++;
            String real = programWord.getText();

            countChar+=s.length();

            countAll++;
            if (s.equals(real)) {
                counter++;
                double tm=60;
                double wpm= (counter/(tm-timer))*tm;
                wordsPerMin.setText(String.valueOf((int)wpm));

                wrong.setVisible(false);
                correct.setVisible(true);

                if(lvl==1)speed=(int)wpm/5;
                else if(lvl==2)speed=(int)wpm/5 +3;
                else if(lvl==3)speed=(int)wpm/5 +6;
                else if(lvl==4)speed=(int)wpm/5 +9;

                colf=1;
            }
            else{
                double tm=60;
                double wpm= (counter/(tm-timer))*tm;
                wordsPerMin.setText(String.valueOf((int)wpm));

                wrong.setVisible(true);
                correct.setVisible(false);

                speed=0;
                colf=0;
            }

            userWord.setText("");
            accuracy.setText(String.valueOf(Math.round((counter*1.0/countAll)*100)) +"%");

            programWord.setText(givenwords[fir]);
            secondProgramWord.setText(givenwords[fir+1]);
            previousProgramWord.setText(givenwords[fir-1]);

            if(fir>=3)secpreviousProgramWord.setText(givenwords[fir-2]);
            else secpreviousProgramWord.setText("here:- ");

            int lim=0;
            if(fir<35)lim=35;
            else if(fir<35)lim=35;
            else if(fir<70)lim=70;
            else if(fir<105)lim=105;
            else if(fir<140)lim=140;
            else if(fir<175)lim=175;

            textflow.getChildren().clear();
            String st="";
            for(int i=lim-35;i<fir-1;i++){
                st+=givenwords[i] + " ";
            }
            greyText = new Text(st);
            greyText.setFill(Color.GREY);

            lastText = new Text(givenwords[fir-1] +" ");
            if(colf==0)lastText.setFill(Color.LIGHTPINK);
            else lastText.setFill(Color.LIGHTGREEN);

            blueText = new Text(givenwords[fir]);
            blueText.setFill(Color.BLUE);
            blueText.setUnderline(true);

            st=" ";
            for(int i=fir+1;i<lim;i++){
                st+=givenwords[i] + " ";
            }
            greenText = new Text(st);
            greenText.setFill(Color.BLACK);

            textflow.getChildren().addAll(greyText,lastText,blueText,greenText);
            textflow.setStyle("-fx-font: 28 arial;");
            textflow.setPrefWidth(700);
        }
    }
    public void resultview(MouseEvent e) throws IOException {
        String username = "";
        try {
            File file = new File("C:\\Users\\Ganesh\\OneDrive\\Documents\\Dharam\\miniproject\\resources\\usname.txt");
            Scanner fileinput = new Scanner(file);

            while (fileinput.hasNext()) {
                String s = fileinput.nextLine();
                username=s;
            }
            fileinput.close();
        }
        catch(Exception fe){
            System.out.println("Error in game controller line 199");
            System.out.println(fe);
        }

        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/typerush", "root", "anappleaday.?20");

            psInsert = connection.prepareStatement("INSERT INTO races(username,wpm) VALUES(?,?)");
            psInsert.setString(1, username);
            psInsert.setInt(2, counter);
            psInsert.executeUpdate();
        } catch (SQLException se) {
            System.out.println("error in line 314 game controller");
            System.out.println(se);
        } finally {
            try {
                CloseResources closingResources = new CloseResources();
                closingResources.closeResources();
//                System.out.println("All resources closed in game controller.");
            } catch (Exception se){
                System.out.println(se);
                System.out.println("Error while closing resources in game controller.");
            }
        }
        //extra
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/typerush", "root", "anappleaday.?20");

            psInsert = connection.prepareStatement("INSERT INTO extra(username,wpm,accuracy,totword,totchar,pretime) VALUES(?,?,?,?,?,?)");
            psInsert.setString(1, username);
            psInsert.setInt(2, counter);
            int acc = (int) Math.round((counter * 1.0 / countAll) * 100);
            psInsert.setInt(3, acc);
            psInsert.setInt(4, countAll);
            psInsert.setInt(5, countChar);
            psInsert.setInt(6, (int)pretime);
            psInsert.executeUpdate();


        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                CloseResources closingResources = new CloseResources();
                closingResources.closeResources();
                System.out.println("All resources closed in Game controller 2nd .");
            } catch (Exception se){
                System.out.println(se);
                System.out.println("Error while closing connection in Game controller 2nd.");
            }
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene6.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene6Controller scene6controller = loader.getController();
        int acc = (int) Math.round((counter * 1.0 / countAll) * 100);
        scene6controller.displayResult(counter, acc, countAll, countAll - counter);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // if time over logic below
    private int countAll = 0;
    private int counter = 0;
    private int timer = 60;
    private int speed = 0;
    private int countChar=0;
    Runnable r = new Runnable() {
        @Override
        public void run() {
            if (timer > -1) {
                seconds.setText(String.valueOf(timer));
                timer -= 1;
                wrong.setVisible(false);
                correct.setVisible(false);
                imgview.setY(y1-=speed);
                if(y1<=-480)won.setVisible(true);

                double tm=60;
                double wpm= Math.ceil((counter/(tm-timer))*tm);
                wordsPerMin.setText(String.valueOf((int)wpm));
            }
            else {
                if (timer == -1) {
                    userWord.setDisable(true);
                    userWord.setText("Game over");
                    playAgain.setVisible(true);
                }
                if (timer == -4) {
                    playAgain.setVisible(true);
                    playAgain.setDisable(false);
                    executor.shutdown();
                }
                timer -= 1;
            }
        }
    };
}

