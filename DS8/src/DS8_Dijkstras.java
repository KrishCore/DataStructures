import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

public class DS8_Dijkstras
{
    public static int dijkstras_Weighted(String[] edges, String vertices, char start, char end)
    {
        ArrayList<DS8_Weighted_Node> sorted = new ArrayList<>();
        sorted.add(new DS8_Weighted_Node(start, 0));
        for (char a : vertices.toCharArray())
            if (a != sorted.getFirst().getLocation())
                sorted.add(new DS8_Weighted_Node(a, Integer.MAX_VALUE));
        Collections.sort(sorted);

        while (!sorted.isEmpty())
        {
            DS8_Weighted_Node n = sorted.removeFirst();
            if (n.getDistance() == Integer.MAX_VALUE)
                return -1;
            if (n.getLocation() == end)
                return n.getDistance();
            ArrayList<DS8_Weighted_Node> adj = new ArrayList<>();
            for (String edge : edges)
                if (edge.charAt(0) == n.getLocation())
                    adj.add(new DS8_Weighted_Node(edge.charAt(1), Integer.parseInt(edge.substring(2))));
            for (DS8_Weighted_Node temp: adj)
                for (DS8_Weighted_Node node : sorted)
                    if (node.getLocation() == temp.getLocation() && n.getDistance() + temp.getDistance() < node.getDistance())
                        node.setDistance(n.getDistance() + temp.getDistance());
            Collections.sort(sorted);
        }
        return -1;
    }
    public static int dijkstras_Topographical(char[][] grid, ArrayList<DS8_TerrainCost> TerrainCosts)
    {
        ArrayList<DS8_Terrian_Node> sorted = new ArrayList<>();
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == 'S')
                    sorted.addFirst(new DS8_Terrian_Node(new Point(i,j), 0));
                else sorted.add(new DS8_Terrian_Node(new Point(i,j), Integer.MAX_VALUE));
        Collections.sort(sorted);
        while (!sorted.isEmpty())
        {
            DS8_Terrian_Node node = sorted.removeFirst();
            int x = node.getLocation().x;
            int y = node.getLocation().y;
            if (grid[x][y] == 'E')
                return node.getDistance();
            if (node.getDistance() == Integer.MAX_VALUE)
                break;
            //up
            if (x-1 >= 0)
            {
                int cost = 0;
                for (DS8_TerrainCost tc : TerrainCosts)
                    if (tc.getType() == grid[x-1][y])
                        cost = tc.getCost();
                if (cost != -1)
                    for (DS8_Terrian_Node n : sorted)
                        if (n.getLocation().x == x-1 && n.getLocation().y == y)
                            if (node.getDistance() + cost < n.getDistance())
                                n.setDistance(node.getDistance() + cost);
            }
            //down
            if (x+1 < grid.length)
            {
                int cost = 0;
                for (DS8_TerrainCost tc : TerrainCosts)
                    if (tc.getType() == grid[x+1][y])
                        cost = tc.getCost();
                if (cost != -1)
                    for (DS8_Terrian_Node n : sorted)
                        if (n.getLocation().x == x+1 && n.getLocation().y == y)
                            if (node.getDistance() + cost < n.getDistance())
                                n.setDistance(node.getDistance() + cost);
            }
            //left
            if (y-1 >= 0)
            {
                int cost = 0;
                for (DS8_TerrainCost tc : TerrainCosts)
                    if (tc.getType() == grid[x][y-1])
                        cost = tc.getCost();
                if (cost != -1)
                    for (DS8_Terrian_Node n : sorted)
                        if (n.getLocation().x == x && n.getLocation().y == y-1)
                            if (node.getDistance() + cost < n.getDistance())
                                n.setDistance(node.getDistance() + cost);
            }
            //right
            if (y+1 < grid[0].length)
            {
                int cost = 0;
                for (DS8_TerrainCost tc : TerrainCosts)
                    if (tc.getType() == grid[x][y+1])
                        cost = tc.getCost();
                if (cost != -1)
                    for (DS8_Terrian_Node n : sorted)
                        if (n.getLocation().x == x && n.getLocation().y == y+1)
                            if (node.getDistance() + cost < n.getDistance())
                                n.setDistance(node.getDistance() + cost);
            }
            Collections.sort(sorted);
        }
        return -1;
    }
}
