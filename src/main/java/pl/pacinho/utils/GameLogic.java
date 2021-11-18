package pl.pacinho.utils;

import javafx.util.Pair;

import static pl.pacinho.config.Properties.SIZE;

public class GameLogic {

    public static Pair<Integer, Integer> getRandomCell() {
        return new Pair<>(RandomUtils.getInt(0, SIZE-1), RandomUtils.getInt(0, SIZE-1));
    }

    public static Integer getIndexByColAndRow(int col, int row){
        return col*SIZE+row;
    }
}
