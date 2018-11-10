public class HintSystem_01{
/*Provides hints for the player as they go thorough the game. There are two options:
a small hint and a big hint. */ 
	public static void main (String[] args){
	//Testing structure. 
	int choice;
	int color;
	int hintArray[]=new int array[2];//use for result of bigHint
	if (choice==0)
		color= smallHint(choice, palette, counterOfColors,history)
		//error message if user has not colored a vertex
		if (color==0){
			System.out.println("Please color a vertex before using the hint."):
		}
		System.out.println("Use " + color);
	else if (choice==1)
		hintArray=bigHint( );
		System.out.println("Use " + hintArray[0]+ " on " + hintArray[1]);
		

public int smallHint(int[] palette, int[] counterOfColors, int[]history, int adjacentcolor){
/*Provides a slight hint to nudge the player along. 
history is an arraylist of vertices the user has chosen
palette is the number of colors available for the user
counterOfColors records which color is assigned to each vertex
choice indicates which hit button has been clicked*/
	int color;
			for(int i=0; i<palette.length; i++)
			{
				if (i && i!=adjacentcolor)
				{
					color=i;
					break;
				}
			}
	return color;
}
	
	public void check(int vertex, coledge[] e)
	{
	/*Checks to make sure that the color offered as a hint is not the same color as 
	already colored vertices.*/
		for(int i=0;i<e.length;i++){
    		//creation of a new MD array to store the subset
    		ColEdge[] sub = new ColEdge[e.length];
    		//reading through rows
    		int amount=0;
    		for (int z= 0; z < e.length; z++) {
    			if (vertex==e[z].u  || vertex==e[z].v) {
                        sub[amount] = e[z];
                        amount++;
               }else{
               	   amount=amount;
               }
            }
         }
		if (e[].u==smallhint())
			smallhint(palette, counterofcolors, history, e[].u);
	}
			
			
		
	
	public int[] bigHint()
	/*Provides the player with more information than the small hint to help them 
	complete the graph. */
	{
		int[] colorVertex=new int array[2];
		int position=0;
			while(vertex==""){ //while not colored
				counterOfColors[
				vertex= // the adjacent of the vertex
				//save the value of the vertex to the colorVertex matrix
			int color= smallhint();
			if (edge.u /**adjacent of vertex**/ != color){
				 colorVertex[0]=color;
			}
			}
			return colorVertex;
	}
	
			
		

