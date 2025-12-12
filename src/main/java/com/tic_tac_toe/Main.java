package com.tic_tac_toe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    private Button[][] cells = new Button[3][3];
    private boolean xTurn = true;
    private int moves = 0;
    private Label statusLabel;

    @Override
    public void start(Stage stage) {
        statusLabel = new Label("X's turn");
        statusLabel.setFont(Font.font(16));

        GridPane grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(8);
        grid.setPadding(new Insets(16));
        grid.setAlignment(Pos.CENTER);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                Button btn = new Button("");
                btn.setPrefSize(100, 100);
                btn.setFont(Font.font(24));
                final int rr = r, cc = c;
                btn.setOnAction(e -> handleMove(rr, cc));
                cells[r][c] = btn;
                grid.add(btn, c, r);
            }
        }

        Button restart = new Button("Restart");
        restart.setOnAction(e -> resetGame());

        HBox bottom = new HBox(10, statusLabel, restart);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setCenter(grid);
        root.setBottom(bottom);

        Scene scene = new Scene(root, 360, 420);
        stage.setScene(scene);
        stage.setTitle("Tic-Tac-Toe");
        stage.show();
    }

    private void handleMove(int r, int c) {
        Button btn = cells[r][c];
        if (!btn.getText().isEmpty() || isGameOver()) return;

        btn.setText(xTurn ? "X" : "O");
        moves++;

        if (checkWin(xTurn ? "X" : "O")) {
            statusLabel.setText((xTurn ? "X" : "O") + " wins!");
            disableAll();
        } else if (moves == 9) {
            statusLabel.setText("Draw!");
        } else {
            xTurn = !xTurn;
            statusLabel.setText((xTurn ? "X" : "O") + "'s turn");
        }
    }

    private boolean checkWin(String player) {
        // rows
        for (int r = 0; r < 3; r++) {
            if (cells[r][0].getText().equals(player) &&
                cells[r][1].getText().equals(player) &&
                cells[r][2].getText().equals(player)) return true;
        }
        // cols
        for (int c = 0; c < 3; c++) {
            if (cells[0][c].getText().equals(player) &&
                cells[1][c].getText().equals(player) &&
                cells[2][c].getText().equals(player)) return true;
        }
        // diagonals
        if (cells[0][0].getText().equals(player) &&
            cells[1][1].getText().equals(player) &&
            cells[2][2].getText().equals(player)) return true;

        if (cells[0][2].getText().equals(player) &&
            cells[1][1].getText().equals(player) &&
            cells[2][0].getText().equals(player)) return true;

        return false;
    }

    private boolean isGameOver() {
        String s = statusLabel.getText();
        return s.contains("wins") || s.equals("Draw!");
    }

    private void disableAll() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                cells[r][c].setDisable(true);
    }

    private void resetGame() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++) {
                cells[r][c].setText("");
                cells[r][c].setDisable(false);
            }
        xTurn = true;
        moves = 0;
        statusLabel.setText("X's turn");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
