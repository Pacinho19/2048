package pl.pacinho.controller;

import javafx.util.Pair;
import lombok.RequiredArgsConstructor;
import pl.pacinho.model.CellType;
import pl.pacinho.utils.GameLogic;
import pl.pacinho.view.Cell;
import pl.pacinho.view.GameBoard;

import java.awt.*;
import java.awt.event.KeyEvent;

@RequiredArgsConstructor
public class GameBoardController {

    private final GameBoard gameBoard;

    public void addStartCell() {
        Pair<Integer, Integer> randomCell = GameLogic.getRandomCell();

        int indexByColAndRow = GameLogic.getIndexByColAndRow(randomCell.getKey(), randomCell.getValue());
        gameBoard.getBoard().remove(indexByColAndRow);
        gameBoard.getBoard().add(new Cell(CellType._2), indexByColAndRow);

        gameBoard.repaint();
        gameBoard.revalidate();
        gameBoard.validate();
    }

    public void move(KeyEvent e) {
        int moveRange = -1;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                moveRange=0;
                break;
            case KeyEvent.VK_DOWN:
                moveRange=0;
                break;
            case KeyEvent.VK_LEFT:
                moveRange=0;
                break;
            case KeyEvent.VK_RIGHT:
                moveRange=0;
                break;
            default:
                return;
        }


    }
}
