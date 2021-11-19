package pl.pacinho.db;

import pl.pacinho.model.CellType;
import pl.pacinho.view.Cell;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbManager {

    private static DbManager self;
    private Connection conn = null;

    private DbManager() {
        connect();
        createBoardTable();
    }

    public static DbManager getInstance() {
        if (self == null) {
            self = new DbManager();
        }
        return self;
    }

    private Connection connect() {
        if (conn != null) {
            return conn;
        }
        try {
            String url = "jdbc:sqlite:2048.db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private void createBoardTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS BOARD (\n"
                    + "	cell integer\n"
                    + ");";

            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Cell> getLastGame() {
        List<Cell> out = new ArrayList<>();
        try {
            try (ResultSet rs = conn.prepareStatement(Queries.selectLastGame).executeQuery()) {
                while (rs.next()) {
                    out.add(new Cell(CellType.findByNumber(rs.getInt(1))));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public void saveCell(Cell cell) {
        try {
            try (PreparedStatement pst = conn.prepareStatement(Queries.insertIntoBoard)) {
                pst.setInt(1, cell.getCellType().getNumber());
                int i = pst.executeUpdate();
                //if(i>0) System.out.println("Cell added");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearTable() {
        try {
            try (PreparedStatement pst = conn.prepareStatement(Queries.deleteFromBoard)) {
                int i = pst.executeUpdate();
                //if(i>0) System.out.println("Cells removed : " + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}