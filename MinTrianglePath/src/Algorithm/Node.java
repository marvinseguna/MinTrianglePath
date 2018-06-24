package Algorithm;

import java.util.ArrayList;

public class Node
{
	private int totalValue;
	private String path; // as we do not know the length of path!
	
	public Node()
	{
		totalValue = 0;
		path = "";
	}
	
	public Node( int totalValue, String path )
	{
		this.totalValue = totalValue;
		this.path = path;
	}
	
	public int getTotalValue()
	{
		return totalValue;
	}
	
	public String getPath()
	{
		return path;
	}
	
	// merge will increment the totalValue and add the passed argument to path
	public void merge( int value )
	{
		totalValue += value;
		path += "," + value;
	}
	
	public String toString()
	{
		return "Best Path\nValue: " + totalValue + ", Path: " + path;
	}
}
