package pl.pacinho.controller;

import javafx.util.Pair;
import pl.pacinho.model.CellType;
import pl.pacinho.model.IterateParameters;
import pl.pacinho.model.MoveType;
import pl.pacinho.model.WallType;
import pl.pacinho.utils.GameLogic;
import pl.pacinho.view.Cell;
import pl.pacinho.view.GameBoard;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static pl.pacinho.config.Properties.SIZE;


public class GameBoardController {

    private final GameBoard gameBoard;
    private List<Integer> rightWallIdx;
    private List<Integer> leftWallIdx;

    public GameBoardController(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        rightWallIdx = GameLogic.getWallIdx(WallType.RIGHT);
        leftWallIdx = GameLogic.getWallIdx(WallType.LEFT);
    }

    public void addCell(boolean start) {
        boolean endFind = false;
        int indexByColAndRow = -1;

        while (!endFind) {
            Pair<Integer, Integer> randomCell = GameLogic.getRandomCellIndexes();
            indexByColAndRow = GameLogic.getIndexByColAndRow(randomCell.getKey(), randomCell.getValue());
            if (((Cell) gameBoard.getBoard().getComponent(indexByColAndRow)).getCellType() == CellType._EMPTY) {
                endFind = true;
            }
        }

        Cell cell;
        if (start) {
            cell = new Cell(CellType._2);
        } else {
            cell = GameLogic.getRandomCell();
        }
        gameBoard.getBoard().remove(indexByColAndRow);
        gameBoard.getBoard().add(cell, indexByColAndRow);

        refresh();
    }

    public void move(KeyEvent e) {
//        boolean addNewCell = false;
//        switch (e.getKeyCode()) {
//            case KeyEvent.VK_UP:
//                addNewCell = moveUp();
//                break;
//            case KeyEvent.VK_DOWN:
//                addNewCell = moveDown();
//                break;
//            case KeyEvent.VK_LEFT:
//                addNewCell = moveLeft();
//                break;
//            case KeyEvent.VK_RIGHT:
//                addNewCell = moveRight();
//                break;
//            default:
//                return;
//        }
//        if (addNewCell)
//            addCell(false);

        if (singleMove(MoveType.findByVK(e.getKeyCode())))
            addCell(false);
    }

    private boolean singleMove(MoveType moveType) {
        List<Integer> mergedIdx = new ArrayList<>();

        boolean addNewCell = false;
        int nonAvailableMoveCount = 0;

        IterateParameters iterateParameters = GameLogic.getIterateParameters(gameBoard.getBoard().getComponentCount(), moveType);

        while (nonAvailableMoveCount < SIZE * SIZE) {
            nonAvailableMoveCount = 0;
            List<Cell> out = Arrays.stream(gameBoard.getBoard().getComponents()).map(c -> (Cell) c).collect(Collectors.toList());
            for (int i = iterateParameters.getIStart();
                 moveType == MoveType.UP || moveType == MoveType.LEFT ? (i < iterateParameters.getISize()) : i >= iterateParameters.getISize();
                 i++) {
                if (i >= SIZE * SIZE) {
                   break;
                }
                Cell cell = out.get(i);
                int idxNext = GameLogic.getNextIdx(i, moveType);
                if ((moveType == MoveType.UP || moveType == MoveType.LEFT ? idxNext < 0 : idxNext > (SIZE * SIZE) - 1)
                        || (moveType == MoveType.RIGHT && rightWallIdx.contains(i))
                        || (moveType == MoveType.LEFT && leftWallIdx.contains(i))
                        || cell.getCellType() == CellType._EMPTY) {
                    nonAvailableMoveCount++;

                    if (moveType == MoveType.RIGHT || moveType == MoveType.DOWN) {
                        i -= 2;
                    }

                    continue;
                }
                Cell cell1 = out.get(idxNext);
                if (cell1.getCellType() == CellType._EMPTY) {
                    replaceCell(i, cell, idxNext);
                    addNewCell = true;
                } else if (cell1.getCellType() == cell.getCellType() && !mergedIdx.contains(i)) {
                    mergedIdx.add(idxNext);
                    replaceCell(i, new Cell(cell.getCellType().getNext()), idxNext);
                    addNewCell = true;
                } else {
                    nonAvailableMoveCount++;
                }

                if (moveType == MoveType.RIGHT || moveType == MoveType.DOWN) {
                    i -= 2;
                }
            }
        }
        refresh();
        return addNewCell;
    }

    private void replaceCell(int idx1, Cell cel1, int idx2) {
        gameBoard.getBoard().remove(idx2);
        gameBoard.getBoard().add(new Cell(cel1.getCellType()), idx2);
        gameBoard.getBoard().remove(idx1);
        gameBoard.getBoard().add(new Cell(CellType._EMPTY), idx1);
    }

    private void refresh() {
        gameBoard.repaint();
        gameBoard.revalidate();
        gameBoard.validate();
    }
}