package Algorithm;

public class Node
{
	private int originalValue; // as entered by the user / through file
	private int sumValue; // added sum during the iteration
	private int parent; // index of parent in the maintained list of nodes. Used to get the best path
	
	public Node()
	{
		originalValue = sumValue = 0;
		parent = -1;
	}
	
	public Node( int value )
	{
		originalValue = sumValue = value;
		parent = -1;
	}
	
	public Node( int originalValue, int sumValue, int parent )
	{
		this.originalValue = originalValue;
		this.sumValue = sumValue;
		this.parent = parent;
	}
	
	public int getOriginal()
	{
		return originalValue;
	}
	
	public int getSum()
	{
		return sumValue;
	}
	
	public int getParent()
	{
		return parent;
	}
}
