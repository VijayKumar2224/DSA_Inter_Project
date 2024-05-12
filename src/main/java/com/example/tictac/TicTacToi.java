package com.example.tictac;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


import java.awt.*;
import java.io.IOException;


public class TicTacToi extends Application {
    private final Button buttons[][] = new Button[3][3];
    private Label playerXScoreLable, playerOScoreLable;

    private boolean playerXTurn = true;

    private int playerXScore = 0, playerOScore = 0;

    private BorderPane createContent(){
         BorderPane root = new BorderPane();
         root.setPadding(new Insets(20));

         //Title
        Label titleLabel = new Label("Tic-Tac Toi");
        root.setTop(titleLabel);
        titleLabel.setStyle("-fx-font-size: 48pt; -fx-font-weight: bold;");
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

        //Game Board
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setPrefSize(100,100);
                button.setStyle("-fx-font-size: 24pt; -fx-font-weight: bold;");
                button.setOnAction(event-> buttonClicked(button));
                buttons[i][j] = button;
                gridPane.add(button, j, i);
            }

        }
        root.setCenter(gridPane);
        BorderPane.setAlignment(gridPane, Pos.CENTER);

        //Score
        HBox scoreBoard = new HBox(20);
        scoreBoard.setAlignment(Pos.CENTER);
        playerXScoreLable = new Label("Player X : 0");
        playerXScoreLable.setStyle("-fx-font-size: 24pt; -fx-font-weight: bold;");
        playerOScoreLable = new Label("Player O : 0");
        playerOScoreLable.setStyle("-fx-font-size: 24pt; -fx-font-weight: bold;");
        scoreBoard.getChildren().addAll(playerXScoreLable, playerOScoreLable);
        root.setBottom(scoreBoard);


         return root;

    }

    private void buttonClicked(Button button){
        if(button.getText().equals("")) {
            if (playerXTurn) {
                button.setText("X");
            } else {
                button.setText("O");
            }

            playerXTurn = !playerXTurn;
            checkWinner();
        }
        return;
    }

    private void checkWinner(){
        //row
        for(int raw = 0; raw < 3; raw++){
            if(buttons[raw][0].getText().equals(buttons[raw][1].getText())
                    && buttons[raw][1].getText().equals(buttons[raw][2].getText())
                    && !buttons[raw][0].getText().isEmpty()){
                //System.out.println("Winner");
                String winner = buttons[raw][0].getText();
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();

            }
        }

        //col
        for(int col = 0; col < 3; col++){
            if(buttons[0][col].getText().equals(buttons[1][col].getText())
                    && buttons[1][col].getText().equals(buttons[2][col].getText())
                    && !buttons[0][col].getText().isEmpty()){
                //System.out.println("Winner");
                String winner = buttons[0][col].getText();
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;

            }
        }

        //Diagonal
        //1
        if(buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())
                && !buttons[0][0].getText().isEmpty()){
            //System.out.println("Winner");
            String winner = buttons[0][0].getText();
            showWinnerDialog(winner);
            updateScore(winner);
            resetBoard();
            return;

        }

        //2

        if(buttons[2][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[0][2].getText())
                && !buttons[2][0].getText().isEmpty()){
            //System.out.println("Winner");
            String winner = buttons[2][0].getText();
            showWinnerDialog(winner);
            updateScore(winner);
            resetBoard();
            return;

        }

        //tie

        boolean tie = true;
        for(Button row[]: buttons){
            for(Button button : row){
                if(button.getText().isEmpty()){
                    tie = false;
                    break;
                }
            }
        }
        if(tie){
            showTieDialog();
            resetBoard();
        }


    }
    private void showWinnerDialog(String winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Congratulation " + winner + " !You won the game!");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private void showTieDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("Game Over ! It's a tie!");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    public void updateScore(String winner){
        if(winner.equals("X")){
            playerXScore++;
            playerXScoreLable.setText("Player X : " + playerXScore);
        }else{
            playerOScore++;
            playerOScoreLable.setText("Player O : " + playerOScore);

        }
    }

    private void resetBoard(){
        for(Button raw[] : buttons){
            for(Button button : raw){
                button.setText("");
            }
        }
    }
    @Override

    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toi");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}