package pl.pacinho.view;

import pl.pacinho.model.CellType;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Cell extends JPanel {

    private CellType cellType;

    public Cell(CellType cellType) {
        this.cellType = cellType;
        init();
    }

    private void init() {
        this.setBorder(new LineBorder(Color.BLACK));
        this.setBackground(cellType.getColor());
        this.setLayout(new BorderLayout());
        JLabel jLabel = new JLabel(cellType.getCellText());
        jLabel.setFont(new Font("Serif", Font.BOLD, 70));
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setVerticalAlignment(JLabel.CENTER);
        this.add(jLabel, BorderLayout.CENTER);
    }
}
