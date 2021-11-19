package pl.pacinho.run;

import pl.pacinho.db.DbManager;
import pl.pacinho.utils.TestData;
import pl.pacinho.view.GameBoard;

public class Main {


    public static void main(String[] args) {
        //TestData.insertTstData();
        GameBoard gb = new GameBoard();
        gb.setVisible(true);
    }
}