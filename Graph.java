# project1-1
DKE Project 1-1 group 28 (graph colouring)
import java.io.*;
import java.util.*;

		class ColEdge
			{
			int u;
			int v;
			}
		
public class Graph
		{
		
		public final static boolean DEBUG = true;
		
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

			int[] c= new int[n]; //array to count the number of connections a vertice has
			int[] color=new int[n]; //array to arrange the vertices into colors (each color is a number, each index is a vertex)
			for (int j=0; j<color.length; j++) //sets every vertex to an available color
				color[j]=1;
			int[][] edge= new int[n][2]; //for edges
			edges(edge, e);
			count(c, e, 0);
			System.out.println("the number of the upperbound connection: " + (assupper(c, 0) +1) );
			System.out.println("chromatic num is: " + greedy(color, e, edge));
			
		}

		public static int[] count(int[] c, ColEdge[] e, int i) //i is for placement in array c
		{
			if (i == e.length) 
				return c; //returns the full array
			for (int j=0; j<e.length; j++) //j for placement in array e
				/* every time a vertice appears in the list it means it has a connection,
					so every time we see the vertex we add +1 (counting another connection)
					to the right placement in the counting array, so, if vertex 3 has another
					connection we add 1 to place 2 (because the array is 0 to n-1 while the 
					vertices are 1 to n) to mark another connection */
				if (e[j].u == i+1 || e[j].v == i+1)
					c[i]++;
			return count(c, e, i+1); 
		}
		
		public static int[][] edges(int[][] edge, ColEdge[] e) //puts each end of the edge in a matrix
		{
			for (int i=0; i<edge[0].length; i++) 
			{
				edge[i][0]=e[i].u; //all u's are stored in 0 
				edge[i][1]=e[i].v; //all v's are stored in 1
			}
			return edge;
		}
		
		public static int assupper(int[] c, int biggest)
		{
			for (int i=0; i<c.length; i++) //finds the vertex with most connections 
				if (c[i]> biggest)
					biggest= c[i];
			return biggest;
		}
		
		public static int greedy(int[] color, ColEdge[] e, int[][] edge)
		{	
			for (int i=0; i<edge[0].length; i++)
			{
				for (int j=0; j<edge[0].length; j++)
				{
					for (int k=1; k<color.length+1; k++)
					{
						if (i!=j && (edge[i][0]==edge[j][0] || edge[i][0]==edge[j][1]) && (color[e[i].v-1]!=k && color[e[i].u-1]!=k))
						{
							color[e[i].u-1]=k;
							break;
						}
						else if (i!=j &&(edge[i][1]==edge[j][0] || edge[i][1]==edge[j][1]) && (color[e[i].u-1]!=k && color[e[i].v-1]!=k))
						{	
							color[e[i].v-1]=k;
							break;
						}
					}
				}
			}
			
			int max=0;
			for (int i=0; i<color.length; i++)
				if (color[i]>max)
					max=color[i];
			
			return max;
		}
}
