import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Minesweeper extends JFrame {
    private static final String[] DIFFICULTIES = {"Easy", "Medium", "Hard"};

    private int rows;
    private int cols;
    private int mines;

    private Cell[][] cells;
    private boolean gameOver = false;
    private int cellsRevealed = 0;

    private JPanel boardPanel;

    public Minesweeper() {
        setTitle("Google Minesweeper");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        showDifficultySelection();
    }

    private void showDifficultySelection() {
        getContentPane().removeAll();

        JPanel selectPanel = new JPanel();
        selectPanel.setLayout(new GridLayout(0, 1, 10, 10));
        selectPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel label = new JLabel("Select Difficulty", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        selectPanel.add(label);

        for (String diff : DIFFICULTIES) {
            JButton btn = new JButton(diff);
            btn.setFont(new Font("Arial", Font.PLAIN, 18));
            btn.addActionListener(e -> {
                switch (diff) {
                    case "Easy" -> setDifficulty(9, 9, 10);
                    case "Medium" -> setDifficulty(16, 16, 40);
                    case "Hard" -> setDifficulty(16, 30, 99);
                }
                initGame();
            });
            selectPanel.add(btn);
        }

        getContentPane().add(selectPanel);
        pack();
        setLocationRelativeTo(null);
        revalidate();
        repaint();
        setVisible(true);
    }

    private void setDifficulty(int r, int c, int m) {
        rows = r;
        cols = c;
        mines = m;
    }

    private void initGame() {
        getContentPane().removeAll();
        cells = new Cell[rows][cols];
        cellsRevealed = 0;
        gameOver = false;

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(rows, cols));
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Cell cell = new Cell(r, c);
                cells[r][c] = cell;
                boardPanel.add(cell.button);
            }
        }

        placeMines();
        calculateNumbers();

        // Frame size adjustments
        int width = Math.min(cols * 40, 1200);
        int height = Math.min(rows * 40 + 50, 800);
        setSize(width, height);
        setMinimumSize(new Dimension(width, height));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(boardPanel, BorderLayout.CENTER);

        // Add a button panel with Restart and Difficulty buttons
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        JButton restartBtn = new JButton("Restart");
        restartBtn.addActionListener(e -> initGame());
        JButton difficultyBtn = new JButton("Difficulty");
        difficultyBtn.addActionListener(e -> showDifficultySelection());

        controlPanel.add(restartBtn);
        controlPanel.add(difficultyBtn);

        getContentPane().add(controlPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        revalidate();
        repaint();
        setVisible(true);
    }

    private void placeMines() {
        Random rand = new Random();
        int minesPlaced = 0;
        while (minesPlaced < mines) {
            int r = rand.nextInt(rows);
            int c = rand.nextInt(cols);
            if (!cells[r][c].isMine) {
                cells[r][c].isMine = true;
                minesPlaced++;
            }
        }
    }

    private void calculateNumbers() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!cells[r][c].isMine) {
                    int count = 0;
                    for (int dr = -1; dr <= 1; dr++) {
                        for (int dc = -1; dc <= 1; dc++) {
                            int nr = r + dr;
                            int nc = c + dc;
                            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && cells[nr][nc].isMine) {
                                count++;
                            }
                        }
                    }
                    cells[r][c].neighborMines = count;
                }
            }
        }
    }

    private void revealCell(int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols) return;
        Cell cell = cells[r][c];
        if (cell.isRevealed || cell.isFlagged) return;

        cell.reveal();

        if (cell.isMine) {
            gameOver = true;
            revealAllMines();
            JOptionPane.showMessageDialog(this, "💥 Boom! Game Over!");
            promptRestartOrDifficulty();
            return;
        }

        cellsRevealed++;

        // Reveal neighbors if no mines nearby
        if (cell.neighborMines == 0) {
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (dr != 0 || dc != 0) {
                        revealCell(r + dr, c + dc);
                    }
                }
            }
        }

        if (cellsRevealed == rows * cols - mines) {
            gameOver = true;
            revealAllMines();
            JOptionPane.showMessageDialog(this, "🎉 You cleared the minefield! Congratulations!");
            promptRestartOrDifficulty();
        }
    }

    private void revealAllMines() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (cells[r][c].isMine && !cells[r][c].isFlagged) {
                    cells[r][c].button.setText("💣");
                    cells[r][c].button.setBackground(new Color(255, 102, 102));
                }
            }
        }
    }

    private void promptRestartOrDifficulty() {
        String[] options = {"Restart", "Change Difficulty", "Exit"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "What would you like to do?",
                "Game Over",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == 0) {
            initGame();
        } else if (choice == 1) {
            showDifficultySelection();
        } else {
            System.exit(0);
        }
    }

    private class Cell {
        int row, col;
        boolean isMine = false;
        boolean isRevealed = false;
        boolean isFlagged = false;
        int neighborMines = 0;
        JButton button;

        private final Color unrevealedLight = new Color(184, 184, 184);
        private final Color unrevealedDark = new Color(204, 204, 204);
        private final Color flagBgColor = new Color(255, 150, 150);
        private final Color mineColor = new Color(255, 102, 102);
        private final Color revealedEmptyColor = new Color(225, 235, 245);

        public Cell(int r, int c) {
            row = r;
            col = c;
            button = new JButton();
            button.setPreferredSize(new Dimension(35, 35));
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.setMargin(new Insets(0, 0, 0, 0));
            button.setFocusPainted(false);
            button.setOpaque(true);
            button.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            setUnrevealedColor();

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (gameOver) return;
                    if (SwingUtilities.isRightMouseButton(e)) {
                        toggleFlag();
                    } else if (SwingUtilities.isLeftMouseButton(e)) {
                        if (!isFlagged)
                            revealCell(row, col);
                    }
                }
            });
        }

        private void setUnrevealedColor() {
            if ((row + col) % 2 == 0) {
                button.setBackground(unrevealedLight);
            } else {
                button.setBackground(unrevealedDark);
            }
            button.setForeground(Color.BLACK);
            button.setText("");
        }

        public void reveal() {
            if (isRevealed) return;
            isRevealed = true;
            button.setEnabled(false);

            if (isMine) {
                button.setText("💣");
                button.setBackground(mineColor);
                button.setForeground(Color.BLACK);
            } else {
                if (neighborMines == 0) {
                    button.setBackground(revealedEmptyColor);
                    button.setText("");
                } else {
                    button.setText(String.valueOf(neighborMines));
                    button.setForeground(getColorForNumber(neighborMines));
                    button.setBackground(getColorForNumberBg(neighborMines));
                }
            }
        }

        public void toggleFlag() {
            if (isRevealed) return;
            isFlagged = !isFlagged;
            if (isFlagged) {
                button.setText("🚩");
                button.setForeground(Color.RED.darker());
                button.setBackground(flagBgColor);
            } else {
                setUnrevealedColor();
            }
        }

        private Color getColorForNumber(int number) {
            return switch (number) {
                case 1 -> new Color(0, 0, 255);
                case 2 -> new Color(0, 128, 0);
                case 3 -> new Color(255, 0, 0);
                case 4 -> new Color(0, 0, 128);
                case 5 -> new Color(128, 0, 0);
                case 6 -> new Color(64, 224, 208);
                case 7 -> Color.BLACK;
                case 8 -> Color.DARK_GRAY;
                default -> Color.BLACK;
            };
        }

        private Color getColorForNumberBg(int number) {
            return switch (number) {
                case 1 -> new Color(198, 226, 255);
                case 2 -> new Color(198, 255, 198);
                case 3, 5 -> new Color(255, 198, 198);
                case 4 -> new Color(198, 198, 255);
                case 6 -> new Color(198, 255, 255);
                case 7 -> new Color(224, 224, 224);
                case 8 -> new Color(192, 192, 192);
                default -> new Color(225, 235, 245);
            };
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Minesweeper::new);
    }
}
