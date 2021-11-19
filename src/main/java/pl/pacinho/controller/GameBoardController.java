package pl.pacinho.controller;

import javafx.util.Pair;
import pl.pacinho.db.DbManager;
import pl.pacinho.model.CellType;
import pl.pacinho.model.IterateParameters;
import pl.pacinho.model.MoveType;
import pl.pacinho.model.WallType;
import pl.pacinho.utils.GameLogic;
import pl.pacinho.view.Cell;
import pl.pacinho.view.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static pl.pacinho.config.Properties.SIZE;


public class GameBoardController {

    private final GameBoard gameBoard;
    private List<Integer> rightWallIdx;
    private List<Integer> leftWallIdx;

    private DbManager dbManager;

    private boolean win = false;

    public GameBoardController(GameBoard gameBoard) {
        dbManager = DbManager.getInstance();
        this.gameBoard = gameBoard;
        rightWallIdx = GameLogic.getWallIdx(WallType.RIGHT);
        leftWallIdx = GameLogic.getWallIdx(WallType.LEFT);
    }

    public void back() {
        Component[] lastMove = GameLogic.getLastMove();
        if (lastMove == null) {
            return;
        }

        gameBoard.getBoard().removeAll();
        for (Component component : lastMove) {
            gameBoard.getBoard().add(component);
        }
        calculateScore();
        refresh();
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

        if (start) {
            GameLogic.addMove(gameBoard.getBoard().getComponents());
        }
        refresh();
    }

    public void move(KeyEvent e) {

        if (!gameBoard.getContentPane().isEnabled()) {
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            back();
            return;
        }

        MoveType byVK = MoveType.findByVK(e.getKeyCode());
        if (byVK == null) {
            return;
        }

        if (singleMove(byVK)) {
            addCell(false);
        }

        GameLogic.addMove(gameBoard.getBoard().getComponents());

        if (checkEnd()) {
            JOptionPane.showMessageDialog(gameBoard, "Koniec gry !");
        }

        if (checkWin() && !win) {
            win = true;
            JOptionPane.showMessageDialog(gameBoard, "Wygrana !");
        }

        calculateScore();
    }

    private boolean checkWin() {
        Integer max = GameLogic.parseArray(gameBoard.getBoard().getComponents())
                .stream()
                .map(c -> c.getCellType().getNumber())
                .max(Integer::compareTo).get();
        return max == 2048;
    }

    private void calculateScore() {
        Integer sum = GameLogic.parseArray(gameBoard.getBoard().getComponents())
                .stream()
                .map(c -> c.getCellType().getNumber())
                .reduce(0, (a, b) -> a + b);

        gameBoard.getScoreValueL().setText(String.valueOf(sum) + "   ");

    }

    private boolean singleMove(MoveType moveType) {
        List<Integer> mergedIdx = new ArrayList<>();

        boolean addNewCell = false;
        int nonAvailableMoveCount = 0;

        IterateParameters iterateParameters = GameLogic.getIterateParameters(gameBoard.getBoard().getComponentCount(), moveType);

        while (nonAvailableMoveCount < SIZE * SIZE) {
            nonAvailableMoveCount = 0;
            List<Cell> out = GameLogic.parseArray(gameBoard.getBoard().getComponents());
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
                    break;
                } else if (cell1.getCellType() == cell.getCellType() && !mergedIdx.contains(i)) {
                    mergedIdx.add(idxNext);
                    replaceCell(i, new Cell(cell.getCellType().getNext()), idxNext);
                    addNewCell = true;
                    break;
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
        refresh();
    }

    private void refresh() {
        gameBoard.repaint();
        gameBoard.revalidate();
        gameBoard.validate();
    }

    private boolean checkEnd() {
        List<Cell> cells = GameLogic.parseArray(gameBoard.getBoard().getComponents());

        if (cells.stream().filter(c -> c.getCellType() == CellType._EMPTY).findAny().isPresent()) {
            return false;
        }

        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            //go Left
            if (!leftWallIdx.contains(i)) {
                boolean endLeft = false;
                while (!endLeft) {
                    int idxNext = i - 1;
                    if (checkMerge(cells, cell, idxNext)) return false;
                    endLeft = true;
                }
            }
            //go Right
            if (!rightWallIdx.contains(i)) {
                boolean endRight = false;
                while (!endRight) {
                    int idxNext = i + 1;
                    if (checkMerge(cells, cell, idxNext)) return false;
                    endRight = true;
                }
            }
            //go Up
            if (i - SIZE > 0) {
                boolean enUp = false;
                while (!enUp) {
                    int idxNext = i - SIZE;
                    if (checkMerge(cells, cell, idxNext)) return false;
                    enUp = true;
                }
            }
            //do Down
            if (i + SIZE < SIZE * SIZE) {
                boolean endDown = false;
                while (!endDown) {
                    int idxNext = i + SIZE;
                    if (checkMerge(cells, cell, idxNext)) return false;
                    endDown = true;
                }
            }
        }

        return true;
    }

    private boolean checkMerge(List<Cell> cells, Cell cell, int idxNext) {
        Cell cel2 = cells.get(idxNext);
        if (cel2.getCellType() == cell.getCellType()) {
            return true;
        }
        return false;
    }

    public void loadPrevGame() {
        List<Cell> lastGame = dbManager.getLastGame();
        if (lastGame.isEmpty()) {
            return;
        }

        int result = JOptionPane.showConfirmDialog(
                gameBoard,
                "Load previous game?",
                "Load Game",
                JOptionPane.YES_NO_OPTION);

        if (result == 0) {
            gameBoard.getBoard().removeAll();
            lastGame.forEach(c -> gameBoard.getBoard().add(c));
            GameLogic.addMove(gameBoard.getBoard().getComponents());
            refresh();
        }
    }

    public void saveGame() {
        dbManager.clearTable();
        GameLogic.parseArray(gameBoard.getBoard().getComponents())
                .forEach(dbManager::saveCell);
    }
}