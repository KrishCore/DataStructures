import java.util.ArrayList;
import java.awt.Point;
import java.util.Collections;

public class DS8_AStar {
    public static DS8_Path_Solution aStar_Simple (char[][] maze)
    {
        ArrayList<DS8_AStar_Node<Point>> open = new ArrayList<>();
        ArrayList<DS8_AStar_Node<Point>> closed  = new ArrayList<>();
        Point start = new Point();
        Point end = new Point();

        for (int i = 0; i < maze.length; i++)
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 'S') start = new Point(j,i);
                if (maze[i][j] == 'E') end = new Point(j,i);
            }
        open.add(new DS8_AStar_Node<>(start, null, 0, (Math.abs(end.x-start.x) + Math.abs(end.y- start.y))));

        while (!open.isEmpty())
        {
            Collections.sort(open);
            DS8_AStar_Node<Point> cur = open.removeFirst();
            closed.add(cur);

            if (cur.getLocation().equals(end))
            {
                ArrayList<Point> path= new ArrayList<>();
                while (cur != null) {
                    path.addFirst(cur.getLocation());
                    cur = cur.getParent();
                }
                return new DS8_Path_Solution<>(path, path.size()-1);
            }

            int r = cur.getLocation().y;
            int c = cur.getLocation().x;

            //up
            if (r -1 >= 0 && maze[r -1][c] != 'W')
            {
                Point p = new Point(c, r-1);
                int g = cur.getG()+1;
                int h = Math.abs(end.x - p.x) + Math.abs(end.y - p.y);
                DS8_AStar_Node<Point> newPoint = new DS8_AStar_Node<>(p, cur, g, h);
                boolean skip = false;
                for(DS8_AStar_Node<Point> t : closed)
                    if (t.getLocation().equals(p)) skip = true;
                for (DS8_AStar_Node<Point> t : open)
                    if (t.getLocation().equals(p) && cur.getG() >= t.getG()) skip =true;
                if (!skip) open.add(newPoint);
            }
            //down
            if (r +1 < maze.length && maze[r +1][c] != 'W')
            {
                Point p = new Point(c, r+1);
                int g = cur.getG()+1;
                int h = Math.abs(end.x - p.x) + Math.abs(end.y - p.y);
                DS8_AStar_Node<Point> newPoint = new DS8_AStar_Node<>(p, cur, g, h);
                boolean skip = false;
                for(DS8_AStar_Node<Point> t : closed)
                    if (t.getLocation().equals(p)) skip = true;
                for (DS8_AStar_Node<Point> t : open)
                    if (t.getLocation().equals(p) && cur.getG() >= t.getG()) skip = true;
                if (!skip) open.add(newPoint);
            }
            //left
            if (c -1 >= 0 && maze[r][c -1] != 'W')
            {
                Point p = new Point(c-1, r);
                int g = cur.getG()+1;
                int h = Math.abs(end.x - p.x) + Math.abs(end.y - p.y);
                DS8_AStar_Node<Point> newPoint = new DS8_AStar_Node<>(p, cur, g, h);
                boolean skip = false;
                for(DS8_AStar_Node<Point> t : closed)
                    if (t.getLocation().equals(p)) skip = true;
                for (DS8_AStar_Node<Point> t : open)
                    if (t.getLocation().equals(p) && cur.getG() >= t.getG()) skip = true;
                if (!skip) open.add(newPoint);
            }
            //right
            if (c +1 < maze[0].length && maze[r][c +1] != 'W')
            {
                Point p = new Point(c+1, r);
                int g = cur.getG()+1;
                int h = Math.abs(end.x - p.x) + Math.abs(end.y - p.y);
                DS8_AStar_Node<Point> newPoint = new DS8_AStar_Node<>(p, cur, g, h);
                boolean skip = false;
                for(DS8_AStar_Node<Point> t : closed)
                    if (t.getLocation().equals(p)) skip = true;
                for (DS8_AStar_Node<Point> t : open)
                    if (t.getLocation().equals(p) && cur.getG() >= t.getG()) skip = true;
                if (!skip) open.add(newPoint);
            }
        }
        return null;
    }

    public static int aStar_JetPack(char[][] maze) {
        int rows = maze.length;
        int cols = maze[0].length;

        DS8_AStar_Node<Point> start = null;
        DS8_AStar_Node<Point> end = null;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (maze[r][c] == 'S') start = new DS8_AStar_Node<>(new Point(c, r), null, 0, 0);
                if (maze[r][c] == 'E') end = new DS8_AStar_Node<>(new Point(c, r), null, 0, 0);
            }
        }

        start.setH(Math.max(Math.abs(start.getLocation().y - end.getLocation().y),
                Math.abs(start.getLocation().x - end.getLocation().x)));
        start.fixF();

        ArrayList<DS8_AStar_Node<Point>> open = new ArrayList<>();
        ArrayList<DS8_AStar_Node<Point>> closed = new ArrayList<>();
        open.add(start);

        while (!open.isEmpty()) {
            DS8_AStar_Node<Point> current = open.get(0);
            for (DS8_AStar_Node<Point> n : open)
                if (n.getF() < current.getF() || (n.getF() == current.getF() && n.getH() < current.getH()))
                    current = n;

            if (current.getLocation().equals(end.getLocation()))
                return current.getG();

            open.remove(current);
            closed.add(current);

            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (dr == 0 && dc == 0) continue;

                    int nr = current.getLocation().y + dr;
                    int nc = current.getLocation().x + dc;

                    if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;

                    Point neighborPoint = new Point(nc, nr);
                    boolean inClosed = false;
                    for (DS8_AStar_Node<Point> n : closed)
                        if (n.getLocation().equals(neighborPoint))
                            inClosed = true;
                    if (inClosed) continue;

                    int moveCost = (maze[nr][nc] == 'O') ? 1 : 0;
                    DS8_AStar_Node<Point> neighbor = null;
                    for (DS8_AStar_Node<Point> n : open)
                        if (n.getLocation().equals(neighborPoint))
                            neighbor = n;

                    if (neighbor != null) {
                        int newG = current.getG() + moveCost;
                        if (newG < neighbor.getG()) {
                            open.remove(neighbor);
                            int h = neighbor.getH();
                            open.add(new DS8_AStar_Node<>(neighborPoint, current, newG, h));
                        }
                    } else {
                        int h = Math.max(Math.abs(nr - end.getLocation().y), Math.abs(nc - end.getLocation().x));
                        open.add(new DS8_AStar_Node<>(neighborPoint, current, current.getG() + moveCost, h));
                    }
                }
            }
        }
        return -1;
    }
}
