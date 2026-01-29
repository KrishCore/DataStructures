import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

public class DS8_Dijkstras
{
    public static int dijkstras_Weighted(String[] edges, String vertices, char start, char end)
    {
        ArrayList<DS8_Weighted_Node> sorted = new ArrayList<>();
        sorted.add(new DS8_Weighted_Node(start, 0));
        for (int i = 0; i < vertices.length(); i++) {
            char a = vertices.charAt(i);
            if (a != sorted.getFirst().getLocation())
                sorted.add(new DS8_Weighted_Node(a, Integer.MAX_VALUE));
        }
        Collections.sort(sorted);


        while (!sorted.isEmpty())
        {
            DS8_Weighted_Node n = sorted.removeFirst();
            if (n.getDistance() == Integer.MAX_VALUE)
                return -1;
            if (n.getLocation() == end)
                return n.getDistance();

            //arraylist to find adjacent nodes from string[] edges
            ArrayList<DS8_Weighted_Node> adj = new ArrayList<>();
            for (String edge : edges) {
                if (edge.charAt(0) == n.getLocation())
                    adj.add(new DS8_Weighted_Node(edge.charAt(1), Integer.parseInt(edge.substring(2))));
            }
            for (DS8_Weighted_Node temp: adj) {
                int newDistance = n.getDistance() + temp.getDistance();
                for (DS8_Weighted_Node node : sorted)
                    if (node.getLocation() == temp.getLocation() && newDistance < node.getDistance())
                        node.setDistance(newDistance);
            }
            Collections.sort(sorted);
        }
        return -1;
    }

    public static int dijkstras_Topographical (char[][] grid, ArrayList<DS8_TerrainCost> travelCosts)
    {
        ArrayList<DS8_Terrian_Node> sorted = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'S') {
                    sorted.addFirst(new DS8_Terrian_Node(new Point(i, j), 0));
                }
                else sorted.add(new DS8_Terrian_Node(new Point(i,j), Integer.MAX_VALUE));
            }
        }

        while (!sorted.isEmpty())
        {
            DS8_Terrian_Node n = sorted.removeFirst();
            int x = n.getLocation().x;
            int y = n.getLocation().y;
            if (n.getDistance() == Integer.MAX_VALUE)
                return -1;
            if (grid[x][y] == 'E')
                return n.getDistance();

            //up
            if (x-1 >= 0)
            {
                char c = grid[x-1][y];
                int cost = 0;
                for (DS8_TerrainCost tc : travelCosts)
                    if (tc.getType() == c)
                        cost = tc.getCost();
                if (cost != -1)
                    for (DS8_Terrian_Node node : sorted)
                        if (node.getLocation().x == x-1 && node.getLocation().y == y && node.getDistance() > n.getDistance() + cost)
                            node.setDistance(n.getDistance() + cost);
            }
            //down
            if (x+1 < grid.length)
            {
                char c = grid[x+1][y];
                int cost = 0;
                for (DS8_TerrainCost tc : travelCosts)
                    if (tc.getType() == c)
                        cost = tc.getCost();
                if (cost != -1)
                    for (DS8_Terrian_Node node : sorted)
                        if (node.getLocation().x == x+1 && node.getLocation().y == y && node.getDistance() > n.getDistance() + cost)
                            node.setDistance(n.getDistance() + cost);
            }
            //left
            if (y-1 >= 0)
            {
                char c = grid[x][y-1];
                int cost = 0;
                for (DS8_TerrainCost tc : travelCosts)
                    if (tc.getType() == c)
                        cost = tc.getCost();
                if (cost != -1)
                    for (DS8_Terrian_Node node : sorted)
                        if (node.getLocation().x == x && node.getLocation().y == y - 1 && node.getDistance() > n.getDistance() + cost)
                            node.setDistance(n.getDistance() + cost);
            }
            //right
            if (y+1 <grid[0].length)
            {
                char c = grid[x][y+1];
                int cost = 0;
                for (DS8_TerrainCost tc : travelCosts)
                    if (tc.getType() == c)
                        cost = tc.getCost();
                if (cost != -1)
                    for (DS8_Terrian_Node node : sorted)
                        if (node.getLocation().x == x && node.getLocation().y == y+1 && node.getDistance() > n.getDistance() + cost)
                            node.setDistance(n.getDistance() + cost);
                }
            Collections.sort(sorted);
        }
        return -1;
    }
    public static void quickSortt(DS8_Terrian_Node[] data, int from, int to)
    {
        if (from >= to)
            return;

        int p = (from+to)/2;
        int i=from;
        int j=to;

        while (i<=j) {
            if (data[i].compareTo(data[p]) <= 0)
                i++;
            else if (data[j].compareTo(data[p]) >= 0)
                j--;
            else {
                DS8_Terrian_Node temp = data[j];
                data[j] = data[i];
                data[i] = temp;
                i++;
                j--;
            }
        }
        if (p < j) {
            DS8_Terrian_Node temp = data[p];
            data[p] = data[j];
            data[j] = temp;
            p = j;
        }
        else if (p > i) {
            DS8_Terrian_Node temp = data[p];
            data[p] = data[i];
            data[i] = temp;
            p = i;
        }
        quickSortt(data, from, p-1);
        quickSortt(data, p+1, to);
    }
    public static void quickSort(DS8_Weighted_Node[] data, int from, int to)
    {
        if (from >= to)
            return;

        int p = (from+to)/2;
        int i=from;
        int j=to;

        while (i<=j) {
            if (data[i].compareTo(data[p]) <= 0)
                i++;
            else if (data[j].compareTo(data[p]) >= 0)
                j--;
            else {
                DS8_Weighted_Node temp = data[j];
                data[j] = data[i];
                data[i] = temp;
                i++;
                j--;
            }
        }
        if (p < j) {
            DS8_Weighted_Node temp = data[p];
            data[p] = data[j];
            data[j] = temp;
            p = j;
        }
        else if (p > i) {
            DS8_Weighted_Node temp = data[p];
            data[p] = data[i];
            data[i] = temp;
            p = i;
        }
        quickSort(data, from, p-1);
        quickSort(data, p+1, to);
    }
}
