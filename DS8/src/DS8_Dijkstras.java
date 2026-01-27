public class DS8_Dijkstras
{
    public static int dijkras_weighted (char[] edges, String vertices, char start, char end)
    {
        // take code from DS8_BSF class breadthFirstSearch_Unweighted method
        // and then implement a way to add numbers
        boolean[] bt = new boolean[vertices.length()];
        DS8_Queue<char[]> queue = new DS8_Queue<>();
        queue.add(new char[] {start});
        bt[vertices.indexOf(start)] = true;

        while (!queue.isEmpty())
        {
            char[] path = queue.poll();
            char lastLetter = path[path.length-1];
            bt[vertices.indexOf(lastLetter)] = true;

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
        return -1;
    }
}
    