package pl.pacinho.view;

import lombok.Getter;
import pl.pacinho.controller.GameBoardController;
import pl.pacinho.model.CellType;
import springutilities.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static pl.pacinho.config.Properties.SIZE;

public class GameBoard extends JFrame {

    private final GameBoard self = this;
    private GameBoardController gameBoardController;
    @Getter
    private Container board;
    private JLabel scoreL;
    @Getter
    private JLabel scoreValueL;
    @Getter
    private JButton backJB;

    public GameBoard() {
        this.setTitle("2048");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);

        gameBoardController = new GameBoardController(self);
        initComponents();
        initView();
        initActions();
    }

    private void initComponents() {
        board = new JPanel();
        scoreL = new JLabel("Score : ");
        scoreL.setFont(new Font("Serif", Font.BOLD, 20));
        scoreValueL = new JLabel("2");
        scoreValueL.setFont(new Font("Serif", Font.BOLD, 20));

        backJB = new JButton("Back");
    }

    private void initView() {
        self.setLayout(new BorderLayout());
        board.setLayout(new GridLayout(SIZE, SIZE));

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board.add(new Cell(CellType._EMPTY));
            }
        }

        gameBoardController.addCell(true);
        self.add(board, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel scorePanel = new JPanel(new SpringLayout());
        scorePanel.add(scoreL);
        scorePanel.add(scoreValueL);
        SpringUtilities.makeCompactGrid(scorePanel, 1, 2, 5, 5, 5, 5);

        topPanel.add(scorePanel, BorderLayout.WEST);
        topPanel.add(backJB, BorderLayout.EAST);

        self.add(topPanel, BorderLayout.NORTH);
    }

    private void initActions() {
        self.setFocusable(true);
        self.requestFocusInWindow();
        self.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gameBoardController.move(e);
            }
        });
        backJB.addActionListener((e) -> {
            gameBoardController.back();
            self.setFocusable(true);
            self.requestFocusInWindow();
        });
    }
}