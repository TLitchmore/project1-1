// Call on this method every time a color has been chosen for a vertex.
public class warning {
    /**
     * Determines whether a warning is needed, i.e. the colored vertex is connected to a vertex with the same color.
     *
     * @param vert the vertex that has been given a color
     * @param e    the matrix with the connections
     * @return true if a warning is needed, false if not
     */
    public static boolean warningNeeded(int vert, ColEdge[] e, int[] colors) {
        int[] connections = new int[e.length];
        // read through the matrix
        for (int i = 0; i < e.length; i++) {
            // loop goes through array that stores all the nodes this vertex is connected to
            for (int p = 0; p < connections.length; p++) {
                // if we find this vertex in the graph matrix and it's in the first column we save the value that's in the second column
                if (e[i].u == vert) {
                    connections[p] = e[i].v;
                } // if we find this vertex in the graph matrix and it's in the second column we save the value that's in the first column
                if (e[i].v == vert) {
                    connections[p] = e[i].u;
                }
            }
        }
        // Go through the array with the connected vertices after it is filled
        for (int p = 0; p < connections.length; p++) {
            // If the color of the vertex is the same as the color of the connected vertex, give a warning.
            if (colors[connections[p]] == colors[vert]) {
                return true;
            } else {
                return false;
            }
        }
    }

}
