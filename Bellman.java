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
		
		Vector<Double> tempPiRow = new Vector<>(graph.getNodes().size());

		Vector<Integer> tempRRow = new Vector<>(graph.getNodes().size());

		for(int k = 0; k <= graph.getNodes().size(); k++)
		{
			
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
				buildRow(lastPiRow, tempPiRow, tempRRow);

			}
		
			
			if(!(lastPiRow.containsAll(tempPiRow)))
			{
				Vector<Double> piRow = (Vector<Double>)tempPiRow.clone();
				Vector<Integer> rRow = (Vector<Integer>)tempRRow.clone();
				piTable.add(piRow);
				rTable.add(rRow);
				lastPiRow = piRow;
			}
			else
			{
				piTable.add(tempPiRow);
				rTable.add(tempRRow);
				break;
			}
		}
	}
	
	private void buildRow(Vector<Double> lastPiRow, Vector<Double> tempPiRow, Vector<Integer> tempRRow) {
		for(int j = 0; j < lastPiRow.capacity(); j++) 
		{
			if(lastPiRow.get(j) != inf)
			{
				List<Edge> outEdges = graph.getOutEdges(graph.getNodeById(j));
				
				for(Edge edge : outEdges)
				{
					int destinationId = edge.getDestination().getId();
					if(lastPiRow.get(j) + edge.getDistance() < lastPiRow.get(destinationId))
					{
						tempPiRow.set(destinationId, lastPiRow.get(j) + edge.getDistance());
						tempRRow.set(destinationId, edge.getSource().getId());
					}
				}
			}
		}
	}
	
	public void  diplayShortestPaths() {
		//Compl�ter
		if(rTable.size() == graph.getNodes().size())
		{
			displayNegativePath();
		}
		else
		{
			displayNormalPath();
		}
	}
	
	private void displayNegativePath() {
		
	}
	
	private void displayNormalPath() {
		
	}

	public void displayTables() {
	 //Compl�ter
		displayPiTable();
		displayRTable();
	}
	
	public void displayPiTable() {
		List<Node> gNodes = graph.getNodes();
		
		StringBuilder sb = new StringBuilder();
		sb.append("<<PITable>>:\n\t\t");
		for(int i = 0; i < gNodes.size(); i++)
		{
			sb.append(gNodes.get(i).getName() + "\t");
		}
		sb.append("\n");
		for(int i = 0; i < piTable.size(); i++)
		{
			sb.append(i + "\t:\t");
			for(int j = 0; j < gNodes.size(); j++)
			{
				Double distance = piTable.get(i).get(j);
				if(distance == inf)
				{
					sb.append("inf\t");
				}
				else
				{
					sb.append(distance + "\t");
				}
			}
			sb.append("\n");
		}
		sb.append("\n");
		System.out.print(sb.toString());
	}
	
	public void displayRTable() {
		List<Node> gNodes = graph.getNodes();
		
		StringBuilder sb = new StringBuilder();
		sb.append("<<RTable>>:\nk\t:\t");
		for(int i = 0; i < gNodes.size(); i++)
		{
			sb.append(gNodes.get(i).getName() + "\t");
		}
		sb.append("\n");
		for(int i = 0; i < rTable.size(); i++)
		{
			sb.append(i + "\t:\t");
			for(int j = 0; j < gNodes.size(); j++)
			{
				Integer nodeId = rTable.get(i).get(j);
				if(nodeId == inf)
				{
					sb.append("-\t");
				}
				else
				{
					sb.append(graph.getNodeById(nodeId).getName() + "\t");
				}
			}
			sb.append("\n");
		}
		sb.append("\n");
		System.out.print(sb.toString());
	}
}
