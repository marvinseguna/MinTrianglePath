package Algorithm;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Processor {
	
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private int bestNodeIndex;
	private int parent = -1;
	
	public Integer determine( BiFunction<Integer, Integer, Integer> function, Integer val1, Integer val2 ) {
	    return function.apply( val1, val2 );
	}
	
	public Boolean check( BiFunction<Integer, Integer, Boolean> function, Integer val1, Integer val2 ) {
	    return function.apply( val1, val2 );
	}
	
	public Integer retrieve( Function<Integer, Integer> function, Integer val1 ) {
	    return function.apply( val1 );
	}
	
	// Function verifies if current node only has 1 parent (first and last node ONLY have 1 parent)
	BiFunction<Integer, Integer, Boolean> onlyOneParent = ( val, arr_length ) -> val == 0 || val == arr_length - 1;
	// Determines the best parent by comparing the nodes at the passed indexes
	BiFunction<Integer, Integer, Integer> getBestParent = ( ind1, ind2) -> nodes.get( ind1 ).getSum() > nodes.get( ind2 ).getSum() ? ind2 : ind1;
	// Function determines if node at index is better than the node at bni (Best Node Index)
	Function<Integer, Integer> bestNode = ( val ) -> bestNodeIndex == -1 || nodes.get( bestNodeIndex ).getSum() > val ? nodes.size() - 1 : bestNodeIndex;
	// Retrieves the sum of the parent. If -1, we are still in root, hence no parents. In such case, return 0.
	Function<Integer, Integer> parentSum = ( ind1 ) -> ind1 == -1 ? 0 : nodes.get( ind1 ).getSum();
	
	public String getBestPath()
	{
		if( nodes.isEmpty() ) { return ""; } // if no input was provided, return an empty string
		
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
	
	public void processNodes( String[] arr )
	{
		bestNodeIndex = -1; // for each level, reset this to re-compare with the current nodes level
		
		for( int i = 0; i < arr.length; i++ )
		{
			int value = Integer.parseInt( arr[i] );
			
			// if nodes has only 1 parent, use current index. Else, compare sums of both parents to determine index
			int parentIndex = ( check( onlyOneParent, i, arr.length ) ? 
									parent :
									determine( getBestParent, parent, parent + 1 ) );
			
			// index of next parent must ONLY be incremented if current node has 2 parents
			if( !check( onlyOneParent, i, arr.length ) ) { parent += 1; }
			
			int sumValue = value + retrieve( parentSum, parentIndex ); // add current value with parents' sum
			nodes.add( new Node( value, sumValue, parentIndex ) ); // add current value as a new node in the list
			bestNodeIndex = retrieve( bestNode, sumValue ); // check if current node is better than the current best node
		}
		parent += 1;
	}
}
