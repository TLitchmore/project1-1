

public class LowerBound {
    public static void main(String[] args) {
        //creation of example array to test code
        int[][] example = {{1, 2}, {1, 3}, {1, 4}, {2, 4}, {2, 5}};
        //not necessary, just for the test, a-value is the vertex from which we look at direct connections
        int a = 1;
        int[][] sub = subsetMaker(example, a);
        // int[][] added = addMatrices(example, sub);
        printArray(sub);

        // System.out.println(findNewA(example, sub, a));
    }

    //creation of a method that splits the array into subsets
    public static int[][] subsetMaker(int[][] example, int a) {
        //comes from the method that finds the max number of connections
        int sizeOfSub = example.length;
        // counter how many direct connections we place in our sub matrix
        int cntr = 0;
        //creation of a new MD array to store the subset
        int[][] sub = new int[sizeOfSub][2];
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
                        cntr++;
                    } else {
                        // if the a value is in the second column, then we want to know the value in the first column, so j = 0 and we place these values in the sub matrix
                        sub[z][1] = a;
                        sub[z][0] = example[i][0];
                        cntr++;
                    }
                    // Once we put the first values in the first row, z will refer to the second row after this and so we re-enter the loop
                    z++;
                }

            }
        }
        // 
        for (int firstCompared = 0; firstCompared < cntr - 1; firstCompared++) {
            for(int secondCompared = 1; secondCompared < cntr; secondCompared++) {
                compare(firstCompared, secondCompared, example, sub);
            }
        }

        return sub;
    }
    public static int[][] compare(int rowOfFirstValue, int rowOfSecondValue, int[][] example, int[][] sub) {

        // go through rows in example
        for (int q = 0; q < example.length; q++) {
            // go through columns in example
            for (int r = 0; r < example[0].length; r++) {
                if (example[q][r] == sub[rowOfFirstValue][1]) {
                    // if we find this in the first column AND the value in the second column is the paired value we add this pair to our subset matrix
                    if (r == 0 && example[q][1] == sub[rowOfSecondValue][1]) {
                        for (int s = 0; s < sub.length; s++) {
                            if (sub[s][0] == 0) {
                                sub[s][0] = example[q][0];
                                sub[s][1] = example[q][1];
                            }
                        }
                    }
                    if (r == 1 && example[q][0] == sub[rowOfSecondValue][1]) {
                        for (int s = 0; s < sub.length; s++) {
                            if (sub[s][0] == 0) {
                                sub[s][0] = example[q][0];
                                sub[s][1] = example[q][1];
                            }
                        }
                    }
                }
            }

        }
        return sub;
    }

   /* public static int keepPair(ColEdge[] example){
        ColEdge[]
    }*/

   /* public static int [][] addMatrices(int[][] example, int[][] sub){
        int[][] combined = new int[example.length + sub.length][2];
        // Go through rows in example matrix
        for(int i = 0; i<example.length; i++) {
            // Go through columns in example matrix
            for(int j = 0; j<example[0].length; j++) {
                combined[i][j] = example[i][j];

                //Go through rows in sub matrix
                for (int k = 0; k < sub.length; k++) {
                    // Go through columns in sub matrix
                    for (int l = 0; l < sub[0].length; l++) {
                        combined[example.length + k][l] = sub[k][l];
                    }
                }
            }
        }
        return combined;
    }
    public static int[][] removeDuplicates(int [][] combined) {
        for(int i = 0; i< combined.length; i++){
            for(int j = 0; j<combined[0].length; j++){

            }
    }*/

   /*public static int findNewA(int[][] example, int[][] sub, int a){
        // Go through rows in example matrix
        for(int i = 0; i<example.length; i++){
            // Go through columns in example matrix
            for(int j = 0; j<example[0].length; j++){
                int x = example[i][j];
                //Go through rows in sub matrix
                for(int k = 0; k<sub.length; k++){
                    // Go through columns in sub matrix
                    for(int l = 0; l<sub[0].length; l++){
                        if(x == sub[k][l]){
                           // do nothing and go to next value in example matrix
                        }
                    }
                }

            }
        }
        return a;
    }*/

  public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
