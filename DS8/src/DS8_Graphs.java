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
            if (DS8_BFS.breadthFirstSearch_Unweighted(arr,vertices, se.charAt(0), se.charAt(1)) == null)
                bridges.add(se);
            ed.add(i, se);
        }
        return bridges.isEmpty() ? null : bridges;
    }
}
