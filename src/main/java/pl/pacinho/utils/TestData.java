package pl.pacinho.utils;

import pl.pacinho.db.DbManager;
import pl.pacinho.model.CellType;
import pl.pacinho.view.Cell;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    private static List<Integer> testBoard = new ArrayList<Integer>() {
        {
            add(4096);
            add(256);
            add(0);
            add(0);

            add(8);
            add(32);
            add(16);
            add(2);

            add(8);
            add(2);
            add(4);
            add(0);

            add(4);
            add(2);
            add(0);
            add(4);
        }
    };

    public static void insertTstData() {
        DbManager dbManager = DbManager.getInstance();
        dbManager.clearTable();
        testBoard.forEach(i -> {
            dbManager.saveCell(new Cell(CellType.findByNumber(i)));
        });

    }
}