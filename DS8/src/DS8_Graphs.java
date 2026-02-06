import java.util.ArrayList;
import java.util.Arrays;
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
            if (DS8_BFS.breadthFirstSearch_Unweighted(arr,vertices, se.charAt(0), se.charAt(1)) == null)
                bridges.add(se);
            ed.add(i, se);
        }
        return bridges.isEmpty() ? null : bridges;
    }
    public static String[] stronglyConnectedRegions (String[] edges, String vertices)
    {
        ArrayList<String> regions = new ArrayList<>();
        for (int i = 0; i < edges.length; i++)
            edges[i] += "1";
        ArrayList<Character> used = new ArrayList<>();
        for (char f : vertices.toCharArray()) {
            if (!used.contains(f)) {
                String temp = f+"";
                for (char e : vertices.toCharArray()) {
                    if (f != e && DS8_Dijkstras.dijkstras_Weighted(edges, vertices, f, e) != -1 && DS8_Dijkstras.dijkstras_Weighted(edges, vertices, e, f) != -1) {
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
}
