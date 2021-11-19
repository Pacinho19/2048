package pl.pacinho.db;

public class Queries {
    public static String selectLastGame = "select * from BOARD";
    public static String insertIntoBoard = "insert into BOARD(cell) values (?)";
    public static String deleteFromBoard = "delete from BOARD";
}