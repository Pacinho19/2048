package pl.pacinho.view;

import lombok.Getter;
import pl.pacinho.controller.GameBoardController;
import pl.pacinho.model.CellType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static pl.pacinho.config.Properties.SIZE;

public class GameBoard extends JFrame {

    private final GameBoard self = this;
    private GameBoardController gameBoardController;
    @Getter
    private Container board;

    public GameBoard() {
        this.setTitle("2048");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);

        gameBoardController = new GameBoardController(self);

        init();
        initActions();
    }


    private void init() {
        board = self.getContentPane();
        board.setLayout(new GridLayout(SIZE, SIZE));

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board.add(new Cell(CellType._EMPTY));
            }
        }

        gameBoardController.addCell(true);
    }

    private void initActions() {
        self.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gameBoardController.move(e);
            }
        });
    }

}