package WumpusWorld;


public class WumpusPlayer
{
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    private int direction;
    private boolean gold;
    private boolean arrow;
    private int colPosition;
    private int rowPosition;

    public WumpusPlayer()
    {
        direction = NORTH;
        gold = false;
        arrow = true;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean hasGold() {
        return gold;
    }

    public void setGold(boolean gold) {
        this.gold = gold;
    }

    public boolean hasArrow() {
        return arrow;
    }

    public void setArrow(boolean arrow) {
        this.arrow = arrow;
    }

    public int getColPosition() {
        return colPosition;
    }

    public void setColPosition(int colPosition) {
        if (colPosition >= 0 && colPosition < WumpusMap.NUM_COLUMNS)
            this.colPosition = colPosition;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public void setRowPosition(int rowPosition) {
        if (rowPosition >= 0 && rowPosition < WumpusMap.NUM_ROWS)
            this.rowPosition = rowPosition;
    }
}
