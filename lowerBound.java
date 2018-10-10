public class LowerBound {
    public static void main(String[] args) {
        //creation of example array to test code
        int[][] example = {{1, 2}, {1, 3}, {1, 4}, {2, 4}, {2, 5}};
        printArray(subsetMaker(example));
    }

    //creation of a method that splits the array into subsets
    public static int[][] subsetMaker(int[][] example) {
        //comes from the method that finds the max number of connections
        int maxConnections = 3;
        //creation of a new MD array to store the subset
        int[][] sub = new int[maxConnections][2];
        //not necessary, just for the test
        int a = 1;
        // z is row in 'sub' matrix (where we place our found values)
        int z = 0;
        //reading through rows
        for (int i = 0; i < example.length; i++) {
            //reading through columns
            for (int j = 0; j < example[i].length; j++) {
                // If we find our a value in the matrix
                if (example[i][j] == a) {
                    if (j == 0) {
                        //if the a value is in the first column, then we want to know the value in the second column, so j = 1 and we place these values in the sub matrix
                        sub[z][0] = a;
                        sub[z][1] = example[i][1];
                    } else {
                        // if the a value is in the second column, then we want to know the value in the first column, so j = 0 and we place these values in the sub matrix
                        sub[z][1] = a;
                        sub[z][0] = example[i][0];
                    }
                    // Once we put the first values in the first row, z will refer to the second row after this and so we re-enter the loop
                    z++;
                }

            }
        }
        return sub;
    }

    public static void printArray(int[][] sub) {
        for (int i = 0; i < sub.length; i++) {
            for (int j = 0; j < sub[0].length; j++) {
                System.out.print(sub[i][j] + " ");
            }
            System.out.println();
        }
    }
}
