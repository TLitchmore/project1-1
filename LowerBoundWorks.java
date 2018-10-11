import java.io.*;
import java.util.*;

		class ColEdge
			{
			int u;
			int v;
			}
		
public class LowerBound
		{
		
		public final static boolean DEBUG = false;
		
		public final static String COMMENT = "//";
		
		public static void main( String args[] )
			{
			if( args.length < 1 )
				{
				System.out.println("Error! No filename specified.");
				System.exit(0);
				}

				
			String inputfile = args[0];
			
			boolean seen[] = null;
			
			//! n is the number of vertices in the graph
			int n = -1;
			
			//! m is the number of edges in the graph
			int m = -1;
			
			//! e will contain the edges of the graph
			ColEdge e[] = null;
			
			try 	{ 
			    	FileReader fr = new FileReader(inputfile);
			        BufferedReader br = new BufferedReader(fr);

			        String record = new String();
					
					//! THe first few lines of the file are allowed to be comments, staring with a // symbol.
					//! These comments are only allowed at the top of the file.
					
					//! -----------------------------------------
			        while ((record = br.readLine()) != null)
						{
						if( record.startsWith("//") ) continue;
						break; // Saw a line that did not start with a comment -- time to start reading the data in!
						}
	
					if( record.startsWith("VERTICES = ") )
						{
						n = Integer.parseInt( record.substring(11) );					
						if(DEBUG) System.out.println(COMMENT + " Number of vertices = "+n);
						}

					seen = new boolean[n+1];	
						
					record = br.readLine();
					
					if( record.startsWith("EDGES = ") )
						{
						m = Integer.parseInt( record.substring(8) );					
						if(DEBUG) System.out.println(COMMENT + " Expected number of edges = "+m);
						}

					e = new ColEdge[m];	
												
					for( int d=0; d<m; d++)
						{
						if(DEBUG) System.out.println(COMMENT + " Reading edge "+(d+1));
						record = br.readLine();
						String data[] = record.split(" ");
						if( data.length != 2 )
								{
								System.out.println("Error! Malformed edge line: "+record);
								System.exit(0);
								}
						e[d] = new ColEdge();
						
						e[d].u = Integer.parseInt(data[0]);
						e[d].v = Integer.parseInt(data[1]);

						seen[ e[d].u ] = true;
						seen[ e[d].v ] = true;
						
						if(DEBUG) System.out.println(COMMENT + " Edge: "+ e[d].u +" "+e[d].v);
				
						}
									
					String surplus = br.readLine();
					if( surplus != null )
						{
						if( surplus.length() >= 2 ) if(DEBUG) System.out.println(COMMENT + " Warning: there appeared to be data in your file after the last edge: '"+surplus+"'");						
						}
					
					}
			catch (IOException ex)
				{ 
		        // catch possible io errors from readLine()
			    System.out.println("Error! Problem reading file "+inputfile);
				System.exit(0);
				}

			for( int x=1; x<=n; x++ )
				{
				if( seen[x] == false )
					{
					if(DEBUG) System.out.println(COMMENT + " Warning: vertex "+x+" didn't appear in any edge : it will be considered a disconnected vertex on its own.");
					}
				}

			//! At this point e[0] will be the first edge, with e[0].u referring to one endpoint and e[0].v to the other
			//! e[1] will be the second edge...
			//! (and so on)
			//! e[m-1] will be the last edge
			//! 
			//! there will be n vertices in the graph, numbered 1 to n

			//! INSERT YOUR CODE HERE!	
//CODE BEGINS HERE!
		// counter how many direct connections are in our sub matrix
        int count=0;
 /*will return number of connections in between nodes in a subgraph
   subgraph consists of a vertex and to the second degree of separation
   we are looking for cliques, or clusters of nodes most resembling cliques*/
        int sub = subsetMaker(e, count);
        /*for the extreme cases that the algorithm misses. For example, if 
        there are only two nodes or one node in the graph.*/
        if (n==2){
        	sub=2;
}		else if(n==1){
			sub=1;
}
//Returns Lower Bound
        System.out.println(sub);
  }  
    //creation of a method that splits the array into subsets
    public static int subsetMaker(ColEdge[] example, int count) {
    	//start from the first edge and work through the entire graph
    	for(int i=0;i<example.length;i++){
    		ColEdge[] edge=new ColEdge[example.length];
    		edge[i]=example[i];
    		//creation of a new array to store the subset
    		ColEdge[] sub = new ColEdge[example.length];
    		//creating the subset
    		int amount=0;
    		for (int z= 0; z < example.length; z++) {
    			if (edge[i].u ==example[z].u  || edge[i].u==example[z].v) {
                        sub[amount] = example[z];
                        amount++;
               }else if (edge[i].v==example[z].u || edge[i].v == example[z].v){
               	   		sub[amount]=example[z];
               	   		amount++;
               }else{
               	   amount=amount;
            }
            }
   /*takes the values of the subset and searches for connections between them
    the number of connections between them becomes the lower bound */
               for(int y=0;y<amount;y++){
          /*Compare variable allows us to keep track of how many internal 
          connections there are with each subgroup*/
               	   int compare=0;
               	   //u value
               	   int uvalue=sub[y].u;
               	   //v value
               	   int vvalue=sub[y].v;
               //tally keeps track of how many times it appears in the subset
                       int tally=0;
                //q serves as the counter
                       	int q=0;
                       while (q<amount){
                        	if(uvalue==sub[q].u || uvalue==sub[q].v){
                        		tally++;
                        	}
                        	if(vvalue==sub[q].u|| vvalue==sub[q].v){
                        		tally++;
                        	}
         /*if the vertices of the edges each appear once more in the graph, 
         then there is an internal connection*/
                        	if(tally==2){
                        		compare++;
                        		tally=0;
                        	}
                        	q++;
                        }
         /*comparing the total number of internal connections after each 
         subgroup allows us to save memory.*/
                        	if (compare>count){
                        		count=compare;
                        		compare=0;
                       		}else{
                       	   count=count;
                       	   compare=0;
                       	   tally=0;	
                        	}
               }  
               
 }
 return count;
 }
}

