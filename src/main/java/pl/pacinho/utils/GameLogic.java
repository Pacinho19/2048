package pl.pacinho.utils;

import javafx.util.Pair;
import pl.pacinho.model.CellType;
import pl.pacinho.model.IterateParameters;
import pl.pacinho.model.MoveType;
import pl.pacinho.model.WallType;
import pl.pacinho.view.Cell;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static pl.pacinho.config.Properties.SIZE;

public class GameLogic {

    private static List<Component[]> moves = new ArrayList<>();

    public static Pair<Integer, Integer> getRandomCellIndexes() {
        return new Pair<>(RandomUtils.getInt(0, SIZE - 1), RandomUtils.getInt(0, SIZE - 1));
    }

    public static Integer getIndexByColAndRow(int col, int row) {
        return col * SIZE + row;
    }

    public static Cell getRandomCell() {
        Integer i = RandomUtils.getInt(0, 1);
        return i == 0 ? new Cell(CellType._2) : new Cell(CellType._4);
    }

    public static List<Integer> getWallIdx(WallType wallType) {
        List<Integer> out = new ArrayList<>();
        for (int i = wallType == WallType.LEFT ? 0 : SIZE - 1; i < SIZE * SIZE; i += SIZE) {
            out.add(i);
        }
        return out;
    }

    public static int getNextIdx(int i, MoveType moveType) {
        switch (moveType) {
            case UP:
                return i - SIZE;
            case DOWN:
                return i + SIZE;
            case LEFT:
                return i - 1;
            case RIGHT:
                return i + 1;
        }
        return 0;
    }

    public static IterateParameters getIterateParameters(int outSize, MoveType moveType) {
        switch (moveType) {
            case DOWN:
            case RIGHT:
                return new IterateParameters(outSize - 1, 0);
            default:
                return new IterateParameters(0, outSize);
        }
    }

    public static void addMove(Component[] components) {
        moves.add(components);
    }

    public static Component[] getLastMove() {
        if (moves.size() <= 1) {
            return null;
        }
        moves.remove(moves.size()-1);
        return moves.get(moves.size() - 1);
    }

    public static List<Cell> parseArray(Component[] components){
        return Arrays.asList(components)
                .stream()
                .map(c -> (Cell) c)
                .collect(Collectors.toList());
    }
}