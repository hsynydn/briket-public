package com.example.hmessenger.logic;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;

import android.widget.LinearLayout;

public class DisplayUnitController
        implements
//        Initializable,
        Variables
{

    public ArrayList<LinearLayout> gridPane;

//    @FXML
//    public GridPane gridPane;
//    @FXML
//    public Button startButton;
//    @FXML
//    private Button pauseButton;
//    @FXML
//    public Label scoreBoard;
//    @FXML
//    public Label gameOver;

    public Stack<Integer> actionEventStack;
//    public EventHandler<javafx.scene.input.KeyEvent> keyEventHandler;

    private Player player;

    public void importObject(Player player){
        this.player = player;
    }

//    public void activateDisplayAction(Stage primaryStage){
//        actionEventStack = new Stack<>();
//
//
//        keyEventHandler = new EventHandler<javafx.scene.input.KeyEvent>() {
//            @Override
//            public void handle(javafx.scene.input.KeyEvent keyEvent) {
//
//                if (keyEvent.getCode() == KeyCode.RIGHT) {
//                    addToActionStack(RIGHT_KEY);
//                }else if(keyEvent.getCode() == KeyCode.LEFT){
//                    addToActionStack(LEFT_KEY);
//                }else if(keyEvent.getCode() == KeyCode.P){
//                    //addActionStack(PAUSE);
//                }else if(keyEvent.getCode() == KeyCode.R){
//                    addToActionStack(ROTATE_KEY);
//                }
//                else if(keyEvent.getCode() == KeyCode.DOWN){
//                    addToActionStack(DOWN_KEY);
//                }
//
//                keyEvent.consume();
//            }
//        };
//
//        primaryStage.addEventHandler(javafx.scene.input.KeyEvent.KEY_PRESSED, keyEventHandler);
//    }


//    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }

    public void refreshMonitor(ArrayList<Cell> gridValues)throws NullPointerException{

        for(Cell cell : gridValues){

            int linear_index = gridValues.indexOf(cell);
            int vertical_index = linear_index / Variables.GRID_Y;
            int horizontal_index = linear_index % Variables.GRID_Y;

            if(cell.isSet()){
                gridPane.get(vertical_index).getChildAt(horizontal_index).setBackgroundColor(cell.getColor());

            }else{
                gridPane.get(vertical_index).getChildAt(horizontal_index).setBackgroundColor(0x000000);
            }
        }
    }

    public void addToActionStack(Integer action){
        actionEventStack.add(action);
    }

    public void emptyActionStack(){
        actionEventStack.removeAllElements();
    }

    public Stack<Integer> getStack(){
        return actionEventStack;
    }

    public void actionOnStart(){
        player.startGame();
//        startButton.setVisible(false);
//        gameOver.setVisible(false);
    }

    public void actionOnExit(){
        player.exitGame();
    }

    public void setScore(int sequence){
        player.setScore(sequence*12);

//        javafx.application.Platform.runLater(new Runnable() {
//
//            @Override
//            public void run() {
//                scoreBoard.setText(player.getScore());
//
//            }
//        });
    }
}

