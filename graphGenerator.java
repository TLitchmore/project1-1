import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class graphGenerator {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Please input the number of vertices");

        //Saving number of vertices
        int vertices = in.nextInt();

        System.out.println("Please input the number of edges");

        //Saving number of edges
        int edges = in.nextInt();

        // The base cases: graph is not possible in these cases
        if (vertices <= 1 || edges < 1 || edges < vertices - 1) {
            System.out.println("Graph cannot be generated");
        } else {
            int[][] graph = generate(vertices, edges);
            //printing the matrix
            System.out.println(Arrays.deepToString(graph));
        }
    }

    /**
     * This method generates a graph with the asked number of vertices and edges.
     *
     * @param vert  number of vertices
     * @param edges number of edges
     * @return the generated graph
     */

    public static int[][] generate(int vert, int edges) {
        // Boolean matrix which will hold 'true' at [a][b] and [b][a] if there is an edge between a and b.
        boolean[][] connections = new boolean[vert + 1][vert + 1];
        // the matrix that will hold the connected vertices in the format the we use everywhere
        int[][] graph = new int[edges][2];

        // Loop reads through the rightly formatted matrix where the edges are put and so at the same time keeps track of the number of edges we generated.
        for (int i = 0; i < edges; i++) {
            // Two randomly generated numbers which will be the indexes in the 'connections' matrix where a connections is put to 'true'.
            // 0 is not included, because there are no vertices named 0 so we keep this row and column empty.
            Random rand = new Random();
            int a = rand.nextInt(vert) + 1;
            int b = rand.nextInt(vert) + 1;
            // System.out.println(a + " " + b);
            // For loop to go through the rows in the boolean matrix. We start with j=1 because our 0 row is empty and should stay empty and then of course we only stop when we have reached the number of vertices itself.
            for (int j = 1; j <= vert; j++) {
                // if no connection exists in this row
                if (!connectionExist(vert, edges, connections)) {
                    // Make a connection between the vertex and a random number and put it in both places in the matrix.
                    connections[j][a] = true;
                    connections[a][j] = true;
                    // Put this connection also in the rightly formatted graph.
                    graph[i][0] = j;
                    graph[i][1] = a;
                }
            }
            // if a is not the same as b (vertices can't be connected to themselves) and if there is not already a connection there (because we don't want to count this edge double)
            if (a != b && !connections[a][b]) {
                connections[a][b] = true;
                connections[b][a] = true;
                // edge is put in the rightly formatted matrix
                graph[i][0] = a;
                graph[i][1] = b;
            } else {
                // else we want to go through the loop again with differently generated random numbers
                i--;
            }
        }
        return graph;
    }

    /**
     * This method tests to see if there is a connection to a certain vertex.
     *
     * @param vert number of vertices
     * @param edges number of edges
     * @param connections boolean matrix which holds the connections between vertices
     * @return returns whether there was a connection (true) or not (false)
     */
    public static boolean connectionExist(int vert, int edges, boolean[][] connections) {
        boolean conExist = false;
        // for loop that goes through all vertices: the rows
        for (int i = 0; i < vert; i++) {
            // for loop that goes through all cells in this row
            for (int j = 0; j < vert; j++) {
                // if there is a connection the boolean should be returned true
                if (connections[i][j]) {
                    conExist = true;
                }
            }
        }
        return conExist;
    }

}

