import java.io.*;
import java.util.*;

		class ColEdge
			{
			int u;
			int v;
			}
		
public class BacktrackingAlg_04
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
			int position=0;
			int[] color = new int[n];
			int chromaticNumber=ChromaticTrevina(e[0].u, color, e, position);
			System.out.println(chromaticNumber);
			
}
			public static int ChromaticTrevina(int vertex, int[] color, ColEdge[] array, int position)
			{
			int i=0;
			int j=0;
			boolean run=true;
		//Checks existing colors to see if it can be used. 
			for (i=0;i<color.length;i++){
			if (vertex==array[position].u){
				if (color[vertex-1]==color[array[position].v-1]){
					color[array[position].v-1]=color[i];
					i++;
				}
			}else if (vertex==array[position].v){
					if (color[vertex-1]==color[array[position].u-1]){
					color[array[position].u-1]=color[i];
					i++;
					}
			}
				
			}
			if(vertex==array[position].u){
			while (color[vertex-1]==color[array[position].v-1]){
					color[array[position].v-1]=color[j]+1;
					j++;
			}
			}
			if(vertex==array[position].v){
				while (color[vertex-1]==color[array[position].u-1]){
					color[array[position].u-1]=color[j]+1;
					j++;
				}
			}
		//Gets the next vertex connected to the current vertex. 
			if (vertex == array[position].v){
				vertex=array[position].u
				position++;
			}else if(vertex == array[position].u){
				vertex=array[position].v;
				position++;
			}
		//Runs through graph for other connectons. 
			while (run=true && position<array.length){
				if (vertex==array[position].u||vertex==array[position].v){
						run=false;
				}else{
						position++;
						run=true;
				}
			}
			return ChromaticTrevina(vertex, color, array, position);
			
			//return ChromaticTrevina(vertex, color, array, counter);			
			}
}