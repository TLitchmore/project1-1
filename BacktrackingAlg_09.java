import java.io.*;
import java.util.*;
import java.lang.Math;

		class ColEdge
			{
			int u;
			int v;
			}
		
public class BacktrackingAlg_09
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
		//Trevina code
		/*Creates the color array value. Subtract 1 from the index value 
		because the color array starts at 0. */
			int[] color = new int[n];
			color[0]=1;
			System.out.println(ChromaticTrevina(e[0].u, color, e, 0, 1));
			/*vertex = e[0].u
			  color = color array
			  array = e(edges) array
			  position = 0
			  count = 1
			*/
			
}
			public static int ChromaticTrevina(int vertex, int[] color, ColEdge[] array, int position, int count)
			{
			int i=0, j=0, max=0; //i for array (e from main), j for color, max for max colors
			boolean run=true; //
		//To backtrack.
		while(color[vertex-1]>0 && position!=0 && position<array.length)
		{
					if (vertex==array[position].u)
						vertex=array[position].v;
					else if (vertex==array[position].v)
						vertex=array[position].u;
					position--;
		}
		//Base Case
		if (count==color.length)
		{
			for (i=0;i<color.length;i++)
				max=Math.max(max,color[i]);
		return (max+1);
		}
		
		//Checks existing colors to see if it can be used. 
		if (vertex==array[position].u)
		{
			i=0;
			while (color[vertex-1]==color[array[position].v-1] && i<color.length)
			{
				color[array[position].v-1]=color[i];
				i++;
			}
			j=0;
			while (color[vertex-1]==color[array[position].v-1])
			{
				color[array[position].v-1]=color[j]+1;
				j++;
			}
		}
		else if (vertex==array[position].v)
		{
			i=0;
			while(color[vertex-1]==color[array[position].u-1] && i<color.length)
			{
				color[array[position].u-1]=color[i];
				i++;
			}
			j=0;
			while (color[vertex-1]==color[array[position].u-1])
			{
				color[array[position].u-1]=color[j]+1;
				j++;
			}
		}
		//Gets the next vertex connected to the current vertex. 
			if (vertex == array[position].v)
			{
				vertex=array[position].u;
				count++;
				position++;
			}
			else if(vertex == array[position].u)
			{
				vertex=array[position].v;
				count++;
				position++;
			}
		//To match value of vertex variable with the value of the vertex in the array. 
			while (run=true && position<array.length-1){
				if (vertex==array[position].u||vertex==array[position].v)
						run=false;
				else
						position++;
			}
			
			//return count;
		return ChromaticTrevina(vertex, color, array, position, count);			
			}
}