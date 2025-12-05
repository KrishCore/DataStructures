import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChessGame extends JFrame {

    public ChessGame() {
        setTitle("Java Swing Chess – Fully Fixed");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 640);
        add(new Board());
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChessGame::new);
    }
}

/* ==================== BOARD ==================== */

class Board extends JPanel {
    Square[][] squares = new Square[8][8];

    Square selected = null;
    ArrayList<Square> legalMoves = new ArrayList<>();
    boolean whiteTurn = true;

    public Board() {
        setLayout(new GridLayout(8, 8));
        initBoard();
        initPieces();
    }

    private void initBoard() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Square sq = new Square(r, c);
                sq.setBackground((r + c) % 2 == 0
                        ? new Color(240, 217, 181)
                        : new Color(181, 136, 99));

                sq.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        handleClick(sq);
                    }
                });

                squares[r][c] = sq;
                add(sq);
            }
        }
    }

    private void initPieces() {

        // --- BLACK ---
        for (int c = 0; c < 8; c++)
            squares[1][c].setPiece(new Pawn(false));

        squares[0][0].setPiece(new Rook(false)); squares[0][7].setPiece(new Rook(false));
        squares[0][1].setPiece(new Knight(false)); squares[0][6].setPiece(new Knight(false));
        squares[0][2].setPiece(new Bishop(false)); squares[0][5].setPiece(new Bishop(false));
        squares[0][3].setPiece(new Queen(false));
        squares[0][4].setPiece(new King(false));

        // --- WHITE ---
        for (int c = 0; c < 8; c++)
            squares[6][c].setPiece(new Pawn(true));

        squares[7][0].setPiece(new Rook(true)); squares[7][7].setPiece(new Rook(true));
        squares[7][1].setPiece(new Knight(true)); squares[7][6].setPiece(new Knight(true));
        squares[7][2].setPiece(new Bishop(true)); squares[7][5].setPiece(new Bishop(true));
        squares[7][3].setPiece(new Queen(true));
        squares[7][4].setPiece(new King(true));
    }

    private void clearHighlight() {
        for (Square s : legalMoves) s.setHighlight(false);
        legalMoves.clear();
    }

    private void handleClick(Square sq) {
        Piece p = sq.getPiece();

        if (selected == null) {
            if (p != null && p.isWhite() == whiteTurn) {
                selected = sq;
                showMoves(sq);
            }
        } else {
            if (legalMoves.contains(sq)) {
                movePiece(selected, sq);
            }
            selected = null;
            clearHighlight();
        }
    }

    private void showMoves(Square from) {
        clearHighlight();
        Piece p = from.getPiece();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Square to = squares[r][c];
                if (p.canMove(from, to, squares)) {
                    legalMoves.add(to);
                    to.setHighlight(true);
                }
            }
        }
    }

    private void movePiece(Square from, Square to) {
        Piece p = from.getPiece();

        /* CASTLING CHECK */
        if (p instanceof King && Math.abs(to.col - from.col) == 2) {
            int row = from.row;

            if (to.col == 6) { // king-side
                Square rookFrom = squares[row][7];
                Square rookTo = squares[row][5];
                rookTo.setPiece(rookFrom.getPiece());
                rookFrom.setPiece(null);
                rookTo.getPiece().hasMoved = true;
            }
            if (to.col == 2) { // queen-side
                Square rookFrom = squares[row][0];
                Square rookTo = squares[row][3];
                rookTo.setPiece(rookFrom.getPiece());
                rookFrom.setPiece(null);
                rookTo.getPiece().hasMoved = true;
            }
        }

        to.setPiece(p);
        p.hasMoved = true;
        from.setPiece(null);

        whiteTurn = !whiteTurn;
    }
}

/* ==================== SQUARE ==================== */

class Square extends JPanel {
    int row, col;
    Piece piece;
    boolean highlight = false;

    public Square(int r, int c) { row = r; col = c; }

    public void setPiece(Piece p) { piece = p; repaint(); }
    public Piece getPiece() { return piece; }

    public void setHighlight(boolean h) { highlight = h; repaint(); }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (highlight) {
            g.setColor(new Color(0, 255, 0, 120));
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        if (piece != null) {
            g.setColor(piece.isWhite() ? Color.WHITE : Color.BLACK);
            g.setFont(new Font("Serif", Font.BOLD, 40));
            g.drawString(piece.getSymbol(), getWidth() / 2 - 14, getHeight() / 2 + 14);
        }
    }
}

/* ==================== PIECE BASE ==================== */

abstract class Piece {
    private boolean white;
    public boolean hasMoved = false;

    public Piece(boolean w) { white = w; }
    public boolean isWhite() { return white; }

    public abstract boolean canMove(Square f, Square t, Square[][] b);
    public abstract String getSymbol();

    protected boolean enemyOrEmpty(Square t) {
        return t.getPiece() == null || t.getPiece().isWhite() != isWhite();
    }
}

/* ==================== PAWN ==================== */

class Pawn extends Piece {
    public Pawn(boolean w) { super(w); }
    public String getSymbol() { return "♙"; }

    public boolean canMove(Square f, Square t, Square[][] b) {
        int dir = isWhite() ? 1 : -1;
        int fr = f.row, fc = f.col, tr = t.row, tc = t.col;

        if (fc == tc && t.getPiece() == null) {
            if (tr == fr + dir) return true;
            if (!hasMoved && tr == fr + 2 * dir && b[fr + dir][fc].getPiece() == null)
                return true;
        }

        if (Math.abs(fc - tc) == 1 && tr == fr + dir &&
                t.getPiece() != null && t.getPiece().isWhite() != isWhite())
            return true;

        return false;
    }
}

/* ==================== ROOK ==================== */

class Rook extends Piece {
    public Rook(boolean w) { super(w); }
    public String getSymbol() { return "♖"; }

    public boolean canMove(Square f, Square t, Square[][] b) {
        if (f == t) return false;

        int fr = f.row, fc = f.col, tr = t.row, tc = t.col;

        if (fr != tr && fc != tc) return false;

        if (fr == tr) {
            int step = (tc > fc) ? 1 : -1;
            for (int c = fc + step; c != tc; c += step)
                if (b[fr][c].getPiece() != null) return false;
        } else {
            int step = (tr > fr) ? 1 : -1;
            for (int r = fr + step; r != tr; r += step)
                if (b[r][fc].getPiece() != null) return false;
        }

        return enemyOrEmpty(t);
    }
}

/* ==================== KNIGHT ==================== */

class Knight extends Piece {
    public Knight(boolean w) { super(w); }
    public String getSymbol() { return "♘"; }

    public boolean canMove(Square f, Square t, Square[][] b) {
        int dr = Math.abs(f.row - t.row);
        int dc = Math.abs(f.col - t.col);
        if ((dr == 2 && dc == 1) || (dr == 1 && dc == 2))
            return enemyOrEmpty(t);
        return false;
    }
}

/* ==================== BISHOP ==================== */

class Bishop extends Piece {
    public Bishop(boolean w) { super(w); }
    public String getSymbol() { return "♗"; }

    public boolean canMove(Square f, Square t, Square[][] b) {
        int fr = f.row, fc = f.col, tr = t.row, tc = t.col;

        if (Math.abs(fr - tr) != Math.abs(fc - tc)) return false;

        int rs = (tr > fr) ? 1 : -1;
        int cs = (tc > fc) ? 1 : -1;

        int r = fr + rs, c = fc + cs;
        while (r != tr) {
            if (b[r][c].getPiece() != null) return false;
            r += rs; c += cs;
        }

        return enemyOrEmpty(t);
    }
}

/* ==================== QUEEN ==================== */

class Queen extends Piece {
    public Queen(boolean w) { super(w); }
    public String getSymbol() { return "♕"; }

    public boolean canMove(Square f, Square t, Square[][] b) {
        return new Rook(isWhite()).canMove(f, t, b)
                || new Bishop(isWhite()).canMove(f, t, b);
    }
}

/* ==================== KING ==================== */

class King extends Piece {
    public King(boolean w) { super(w); }
    public String getSymbol() { return "♔"; }

    public boolean canMove(Square f, Square t, Square[][] b) {
        int dr = Math.abs(f.row - t.row);
        int dc = Math.abs(f.col - t.col);

        if (dr <= 1 && dc <= 1)
            return enemyOrEmpty(t);

        if (!hasMoved && dr == 0 && Math.abs(dc) == 2) {
            int row = f.row;

            if (dc == 2) {
                if (t.col == 6) {
                    if (b[row][5].getPiece() == null && b[row][6].getPiece() == null &&
                            b[row][7].getPiece() instanceof Rook &&
                            !b[row][7].getPiece().hasMoved)
                        return true;
                }
                if (t.col == 2) {
                    if (b[row][3].getPiece() == null &&
                            b[row][2].getPiece() == null &&
                            b[row][1].getPiece() == null &&
                            b[row][0].getPiece() instanceof Rook &&
                            !b[row][0].getPiece().hasMoved)
                        return true;
                }
            }
        }

        return false;
    }
}
