import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;


public class Bellman {
	private Graph graph;
	private Node sourceNode;
	private List<Vector<Double>> piTable =  new ArrayList<Vector<Double>>();
	private List<Vector<Integer>> rTable =  new ArrayList<Vector<Integer>>();
	static final double inf = 99999;

	
	public Bellman (Graph g) {
		this.graph = g;
	}
	
	public void setSourceNode(Node source) {
		this.sourceNode = source;
	}
	
	public void shortestPath() {
		// Compl�ter
		Vector<Double> lastPiRow = new Vector<>(graph.getNodes().size());

		for(int k = 0; k < graph.getNodes().size(); k++)
		{
			Vector<Double> tempPiRow = lastPiRow;

			Vector<Integer> tempRRow = new Vector<>(graph.getNodes().size());
			if(k == 0)
			{
				//On the first iteration
				for(int j = 0; j < tempPiRow.capacity(); j++)
				{
					if(j == sourceNode.getId())
					{
						tempPiRow.add(0.0);
						tempRRow.add((int)inf);
					}
					else 
					{
						tempPiRow.add(inf);
						tempRRow.add((int)inf);
					}
				}
			}
			else 
			{
				//After first iteration
				for(int j = 0; j < lastPiRow.capacity(); j++ ) 
				{
					if(lastPiRow[j] != inf) 
					{
						List<Edge> outEdges = graph.getOutEdges(graph.getNodeById(j));
						
						for(Edge edge : outEdges)
						{
							int destinationId = edge.getDestination().getId();
							if(lastPiRow[destinationId] > lastPiRow[j]+ edge.getDistance())
							{
								tempPiRow[destinationId]
							}
						}
					}
					
				}
			}
		
			/*
			if(lastPiRow != tempPiRow)
			{
				piTable.add(tempPiRow);
				rTable.add(tempRRow);
				lastPiRow = tempPiRow;
			}
			else
			{
				piTable.add(tempPiRow);
				rTable.add(tempRRow);
				break;
			}
			*/

		}
		
	}
	
	public void  diplayShortestPaths() {
		//Compl�ter
	}

	public void displayTables() {
	 //Compl�ter
	}
}
