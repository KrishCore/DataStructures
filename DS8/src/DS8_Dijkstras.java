import java.util.ArrayList;
import java.util.Collections;

public class DS8_Dijkstras
{
    public static int dijkstras_Weighted(String[] edges, String vertices, char start, char end) {
        ArrayList<DS8_Weighted_Node> list = new ArrayList<>();
        list.add(new DS8_Weighted_Node(start, 0));
        for (int i = 0; i < vertices.length(); i++) {
            char a = vertices.charAt(i);
            if (a != list.getFirst().getLocation())
                list.add(new DS8_Weighted_Node(a, Integer.MAX_VALUE));
        }

        DS8_Weighted_Node[] nodes = new DS8_Weighted_Node[list.size()];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = list.get(i);
        }
        ArrayList<DS8_Weighted_Node> sorted = new ArrayList<>(list);
        quickSort(nodes, 0, list.size()-1);
        Collections.addAll(sorted, nodes);

        while (!sorted.isEmpty())
        {
            DS8_Weighted_Node n = sorted.removeFirst();
            if (n.getLocation() == end)
                return n.getDistance();
            if (n.getDistance() == Integer.MAX_VALUE)
                break;

            ArrayList<DS8_Weighted_Node> adj = new ArrayList<>();
            for (String edge : edges) {
                if (edge.charAt(0) == n.getLocation())
                    adj.add(new DS8_Weighted_Node(edge.charAt(1), Integer.parseInt(edge.charAt(2) + "")));
                int d = n.getDistance();// + adj.getFirst().getDistance();
            }
            
            ArrayList<DS8_Weighted_Node> tsorted = new ArrayList<>(list);
            quickSort(nodes, 0, list.size()-1);
            Collections.addAll(tsorted, nodes);
            System.out.println(adj);
        }
        return -1;
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
    