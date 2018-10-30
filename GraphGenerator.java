import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
public class GraphGenerator{
	public static void main(String[]args){
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
	
	//printing random array
	printArr(generate(vertices));
	}
	public static void printArr(int[] array)
	{
		for(int i = 0; i<array.length; i++)
		{
			//if println is used, then it will not stay on the same line
			System.out.print(array[i] + " ");
		}
		System.out.println(); //creates a new entered line
		return;
	}
	//method that generates random graph
	public static int[] generate(int vert){
		int[] randArr = new int[vert];
		int counter = (int) Math.random()* vert;
		for(int i = 0; i<randArr.length; i++){
			Arrays.fill(randArr,counter);
			counter++;
		}
		return randArr;	
		
	}
}
	
		