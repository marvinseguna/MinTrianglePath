package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class Processor {
	
	private ArrayList<Node> parents = new ArrayList<Node>();
	private Node bestNode = new Node();
	
	public Node getBestNode()
	{
		return bestNode;
	}
	
	private Node workNode( int index, int value )
	{
		Node parentNode = parents.get( index );
		Node node = new Node( parentNode.getTotalValue(), parentNode.getPath() );
		node.merge( value );
		
		if( bestNode == null || bestNode.getTotalValue() > node.getTotalValue() )
		{
			bestNode = node;
		}
		return node;
	}
	
	public void processNodes( String[] arr )
	{
		// re-initialise bestNode such that it is calculated for every level
		bestNode = null;
		
		if( parents.isEmpty() )
		{
			int value = Integer.parseInt( arr[0] );
			parents.add( new Node( value, arr[0] ));
		}
		else
		{
			// Each new node processed will be inserted in this array.
			// After processing, this will be considered as the parents list.
			ArrayList<Node> newParents = new ArrayList<Node>();
			
			for( int i = 0; i < arr.length; i++ )
			{
				int value = Integer.parseInt( arr[i] );
				
				// first and last nodes will always have 1 parent
				if( i == 0 || i == arr.length - 1 )
				{
					// if first element, 0. Else, i-1
					int index = ( i == 0 ? 0 : ( i - 1 ) );
					newParents.add( workNode( index, value ) );
				}
				// other nodes will always have 2 parents
				else
				{
					newParents.add( workNode( i - 1, value ) );
					newParents.add( workNode( i, value ) );
				}
			}
			// assign parents to the current list
			parents = newParents;
		}
	}
	
}
