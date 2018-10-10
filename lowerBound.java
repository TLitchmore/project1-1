public class LowerBound
{
	public static void main(Strings[] args)
	{
		//creation of example array to test code
		int [][] example = {{1,2},{1,3},{1,4},{2,4},{2,5}};
		
	}
	//creation of a method that splits the array into subsets
	public static int[] subsetMaker(int [][] example)
	{	
		//comes from the method that finds the max number of connections
		int maxConnections = 3;
		//creation of a new MD array to store the subset
		int[][] sub = new int[maxConnections][2];
		//not necessary, just for the test
		a = 1;
		//reading through rows
		for(i = 0; i<example.length; i++)
		{
			//reading through columns
			for(j = 0; j<example[i].length; j++)
			{
				if(example[i][j] == a)
				{
					if(j == 0)
					{
						//if the a value is in the first column, then we want to know the value in the second column, then j = 1
						for(int z = 0; z<maxConnections; z++)
						{
							sub[z][0] = a;
							sub[z][1] = example[i][1];
						}

					}
					else
					{
						// if the a value is in the second column, then we want to know the value in the first column, so j = 0
						for(int z = 0; z<maxConnections; z++)
						{
							sub[z][1] = a;
							sub[z][0] = example[i][0];
						}
					}
				}
				
			}
		}
	}
}
