package Algorithm;

import java.util.ArrayList;

public class Processor {
	
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private int bestNodeIndex;
	private int nextParent = 0;
	
	public String getBestPath()
	{
		int index = bestNodeIndex;
		String path = "";
		while( index != -1 )
		{
			Node node = nodes.get( index );
			path = "+" + node.getOriginal() + path;
			index = node.getParent();
		}
		return "Minimal path is: " + path.substring( 1 ) + " = " + nodes.get( bestNodeIndex ).getSum();
	}
	
	private void setBestNode( int sumValue, int index )
	{
		if( bestNodeIndex == -1 )
		{
			bestNodeIndex = index;
		}
		else
		{
			Node bestNode = nodes.get( bestNodeIndex );
			if( bestNode.getSum() > sumValue )
			{
				bestNodeIndex = index;
			}
		}
		
	}
	
	public void processNodes( String[] arr )
	{
		bestNodeIndex = -1; // for each level, reset this to re-compare with the current nodes level
		
		if( nodes.isEmpty() ) // this means that we are still in the root
		{
			int value = Integer.parseInt( arr[0] );
			nodes.add( new Node( value ) );
			bestNodeIndex = 0;
		}
		else
		{
			for( int i = 0; i < arr.length; i++ )
			{
				int value = Integer.parseInt( arr[i] );
				
				if( i == 0 || i == arr.length - 1 ) // if first or last node in level, it only has 1 parent!
				{
					Node parent = nodes.get( nextParent );
					int sumValue = parent.getSum() + value;
					nodes.add( new Node( value, sumValue, nextParent ) );
					setBestNode( sumValue, nodes.size() - 1 );
				}
				else
				{
					Node parent1 = nodes.get( nextParent );
					Node parent2 = nodes.get( nextParent + 1 );
					
					if( parent1.getSum() > parent2.getSum() )
					{
						int sumValue = parent2.getSum() + value;
						nodes.add( new Node( value, sumValue, nextParent + 1 ) );
						setBestNode( sumValue, nodes.size() - 1 );
					}
					else
					{
						int sumValue = parent1.getSum() + value;
						nodes.add( new Node( value, sumValue, nextParent ) );
						setBestNode( sumValue, nodes.size() - 1 );
					}
					
					nextParent += 1;
				}
			}
			nextParent += 1;
		}
	}
}
