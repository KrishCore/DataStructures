import java.util.ArrayList;
import java.util.Collections;
public class DS8_Graphs
{
    public static ArrayList<String> bridges (String[] edges, String vertices)
    {
        ArrayList<String> bridges = new ArrayList<>();
        ArrayList<String> ed = new ArrayList<>();
        Collections.addAll(ed, edges);
        for (int i = 0; i < edges.length; i++) {
            String se = ed.remove(i);
            String[] arr = new String[ed.size()];
            for (int j = 0; j < arr.length; j++)
                arr[j] = ed.get(j);
<<<<<<< Updated upstream
            if (breadthFirstSearch_Unweighted(arr,vertices, se.charAt(0),
                    se.charAt(1)) == null)
=======
            if (breadthFirstSearch_Unweighted(arr,vertices, se.charAt(0), se.charAt(1)) == null)
>>>>>>> Stashed changes
                bridges.add(se);
            ed.add(i, se);
        }
        return bridges.isEmpty() ? null : bridges;
    }
<<<<<<< Updated upstream
    public static String[] stronglyConnectedRegions (String[] edges, String
            vertices)
=======
    public static String[] stronglyConnectedRegions (String[] edges, String vertices)
>>>>>>> Stashed changes
    {
        ArrayList<String> regions = new ArrayList<>();
        for (int i = 0; i < edges.length; i++)
            edges[i] += "1";
        ArrayList<Character> used = new ArrayList<>();
        for (char f : vertices.toCharArray()) {
            if (!used.contains(f)) {
                String temp = f+"";
                for (char e : vertices.toCharArray()) {
<<<<<<< Updated upstream
                    if (f != e && dijkstras_Weighted(edges, vertices, f, e) != -1
                            && dijkstras_Weighted(edges, vertices, e, f) != -1) {
=======
                    if (f != e && dijkstras_Weighted(edges, vertices, f, e) != -1 && dijkstras_Weighted(edges, vertices, e, f) != -1) {
>>>>>>> Stashed changes
                        temp += e;
                        used.add(e);
                    }
                }
                if (temp.length() > 1)
                {
                    used.add(f);
                    regions.add(temp);
                }
            }
        }
        String[] array = new String[regions.size()];
        for (int i = 0; i < array.length; i++)
            array[i] = regions.get(i);
        return regions.isEmpty() ? null : array;
    }
<<<<<<< Updated upstream
    public static String breadthFirstSearch_Unweighted (String[] edges, String
            vertices, char start, char end)
=======

    public static String breadthFirstSearch_Unweighted (String[] edges, String vertices, char start, char end)
>>>>>>> Stashed changes
    {
        {
            boolean[] bt = new boolean[vertices.length()];
            DS8_Queue<char[]> queue = new DS8_Queue<>();
            queue.add(new char[] {start});
            bt[vertices.indexOf(start)] = true;
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
            while (!queue.isEmpty())
            {
                char[] path = queue.poll();
                char lastLetter = path[path.length-1];
                bt[vertices.indexOf(lastLetter)] = true;
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
                if (lastLetter == end)
                    return new String(path);
                DS8_Queue<Character> add = new DS8_Queue<>();
                for (int i = 0; i < edges.length; i++) {
                    if (edges[i].contains(lastLetter +""))
                    {
                        char next;
                        if (edges[i].startsWith(lastLetter +""))
                            next = edges[i].charAt(1);
                        else next = edges[i].charAt(0);
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
                        if (!bt[vertices.indexOf(next)])
                            add.add(next);
                        if (!bt[vertices.indexOf(next)]){
                            bt[vertices.indexOf(next)] = true;
                            char[] newPath = new char [path.length+1];
                            for (int k = 0; k < path.length; k++) {
                                newPath[k] = path[k];
                            }
                            newPath[newPath.length-1] = add.poll();
                            queue.add(newPath);
                        }
                    }
                }
            }
            return null;
        }
    }
<<<<<<< Updated upstream
    public static int dijkstras_Weighted(String[] edges, String vertices, char
            start, char end)
=======

    public static int dijkstras_Weighted(String[] edges, String vertices, char start, char end)
>>>>>>> Stashed changes
    {
        ArrayList<DS8_Weighted_Node> sorted = new ArrayList<>();
        sorted.add(new DS8_Weighted_Node(start, 0));
        for (char a : vertices.toCharArray())
            if (a != sorted.getFirst().getLocation())
                sorted.add(new DS8_Weighted_Node(a, Integer.MAX_VALUE));
        Collections.sort(sorted);
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                    adj.add(new DS8_Weighted_Node(edge.charAt(1),
                            Integer.parseInt(edge.substring(2))));
            for (DS8_Weighted_Node temp: adj)
                for (DS8_Weighted_Node node : sorted)
                    if (node.getLocation() == temp.getLocation() && n.getDistance()
                            + temp.getDistance() < node.getDistance())
=======
                    adj.add(new DS8_Weighted_Node(edge.charAt(1), Integer.parseInt(edge.substring(2))));
            for (DS8_Weighted_Node temp: adj)
                for (DS8_Weighted_Node node : sorted)
                    if (node.getLocation() == temp.getLocation() && n.getDistance() + temp.getDistance() < node.getDistance())
>>>>>>> Stashed changes
                        node.setDistance(n.getDistance() + temp.getDistance());
            Collections.sort(sorted);
        }
        return -1;
    }
}
