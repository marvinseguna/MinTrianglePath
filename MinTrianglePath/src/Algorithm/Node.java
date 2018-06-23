package Algorithm;

import java.util.ArrayList;

public class Node
{
	private int totalValue;
	private ArrayList<Integer> path; // as we do not know the length of path!
	
	public Node()
	{
		totalValue = 0;
		path = new ArrayList<Integer>();
	}
	
	public Node( int totalValue, int value )
	{
		this.totalValue = totalValue;
		path = new ArrayList<Integer>();
		path.add( value );
	}
	
	// used to verify the smallest number
	public int getTotalValue()
	{
		return totalValue;
	}
	
	// merge will increment the totalValue and add the passed argument to path
	public void merge( int value )
	{
		totalValue += value;
		path.add( value );
	}
	
	public String toString()
	{
		String x = "Best Path: Value = " + totalValue + ", Path: ";
		for( int i = 0; i < path.size(); i++ )
		{
			x += path.get( i ).toString() + ","; 
		}
		return x.substring( 0, x.length() - 1 );
	}
}
