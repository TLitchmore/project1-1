import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class graphGenerator {

    public static void main(String[] args) {
        //Initialize a scanner to read the command line
        Scanner in = new Scanner(System.in);

        //Asking user to input number of vertices
        System.out.println("Please input the number of vertices");

        //Saving number of vertices
        int vertices = in.nextInt();

        //Asking user to input number of edges
        System.out.println("Please input the number of edges");

        //Saving number of edges
        int edges = in.nextInt();
        
        if(vertices <= 1 || edges < 1 || edges < vertices-1){
        	System.out.println("Graph cannot be generated");
        }	
        int[][] graph = generate(vertices, edges);
        //printing random array
        System.out.println(Arrays.deepToString(graph));
    }



    //method that generates random graph
    public static int[][] generate(int vert, int edges) {
        // boolean matrix which will hold 'true' at [a][b] and [b][a] if there is an edge between a and b
        boolean[][] connections = new boolean[vert+1][vert+1];
        // the matrix that will hold the connected vertices
        int[][] graph = new int[edges][2];

        // loop reads through the rightly formatted matrix where the edges are put and so at the same time keeps track of the number of edges we generated
        for(int i = 0; i < edges; i++) {
            // two randomly generated numbers which will be the indexes in the 'connections' matrix where a connections is put to 'true'
            Random rand = new Random();
            int a = rand.nextInt(vert) + 1;
            int b = rand.nextInt(vert) + 1;
            System.out.println(a + " " + b);
            // if a is not the same as b (vertices can't be connected to themselves) and if there is not already a connection there (because we don't want to count this as an extra edge)
            if (a != b && !connections[a][b]) {
                connections[a][b] = true;
                connections[b][a] = true;
                // edge is put in the rightly formatted matrix
                graph[i][0] = a;
                graph[i][1] = b;
            } else {
            	i--;
            }
        }

        return graph;
    }

}

