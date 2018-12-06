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
		// Complï¿½ter
		Vector<Double> lastPiRow = new Vector<>(graph.getNodes().size());
		Vector<Double> tempPiRow = new Vector<>(graph.getNodes().size());
		Vector<Integer> tempRRow = new Vector<>(graph.getNodes().size());

		for(int k = 0; k < graph.getNodes().size(); k++)
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
				for(int j = 0; j < lastPiRow.capacity(); j++) 
				{
					//ce qui ont ete deja visite
					if(lastPiRow.get(j) != inf)
					{
						//les arcs adjacents 
						List<Edge> outEdges = graph.getOutEdges(graph.getNodeById(j));
						
						for(Edge edge : outEdges)
						{
							//le sommet adjacent
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
		
			
			if(!(lastPiRow.containsAll(tempPiRow)))
			{
				piTable.add((Vector<Double>)tempPiRow.clone());
				rTable.add((Vector<Integer>)tempRRow.clone());
				lastPiRow = (Vector<Double>)tempPiRow.clone();
			}
			else
			{
				piTable.add(tempPiRow);
				rTable.add(tempRRow);
				break;
			}
			

		}
		
	}
	
	public void  diplayShortestPaths() {
		//Complï¿½ter
		Stack<Node> list_path = new Stack<Node>();
		Node current_node = null;
		String result = "\nDiplayShortestPaths - Les chemins sont :\n";
		
		for(int i = sourceNode.getId() + 1; i <= graph.getNodes().size() - 1; i++)
		{
			current_node = graph.getNodeById(i);
			list_path.push(current_node);
			
			do{
				int nodeId = rTable.get(rTable.size() - 1).get(list_path.peek().getId());
				if(nodeId==(int)inf)
					break;
				
				list_path.push(graph.getNodeById(nodeId));
			}while(list_path.peek() != sourceNode && list_path.peek() != current_node);
			
			if(current_node == sourceNode && list_path.size()==2)
				list_path.clear();
			
			else if(list_path.peek() == current_node){
				System.out.print("\nDiplayShortestPaths - Le graphe contient un circuit de coût négatif :\n [" + current_node.getName() + " - " + current_node.getName() + "] ");
				while(!list_path.isEmpty())
					if(list_path.size()==1)
						System.out.print (list_path.pop().getName());
					else 
						System.out.print (list_path.pop().getName() + " -> ");
				return;
			}
			else{
				result += "[" + sourceNode.getName() + " - " + graph.getNodes().get(i).getName() + "] " + piTable.get(piTable.size() - 1).get(i) + " : " + list_path.pop().getName();
				while(!list_path.isEmpty())
					result += " -> " + list_path.pop().getName();
				result += "\n";
			}
			
		}
		System.out.print(result);
	}

	public void displayTables() {
	 //Complï¿½ter
		String space1 = "      ";
		String space2 = "    ";
		String space3 = "  ";
		
		System.out.print("PITable : \n");
		System.out.print("k"+ space3);
		for(Node node : graph.getNodes())
		{
			System.out.print(node.getName() + space1);
		}
		System.out.println();
		
		
		int p=0;
		for(Vector<Double> cout_tab : piTable)
		{ 
			System.out.print(p++ + space3);
			for(Double val : cout_tab)
			{
				if (val.equals(Graph.inf))
					System.out.print("inf" +  space2);
				else		
					System.out.print(val +  space2);
			}
			System.out.println();
		}
			
		System.out.print("\nRTable : \n");
		System.out.print("k"+ space3);
		for(Node node : graph.getNodes())
		{
			System.out.print(node.getName() + space1);
		}
		System.out.println();
		int t=0;
		for(Vector<Integer> i : rTable)
		{ 
			System.out.print(t++ + space3 );
			for(Integer j : i)
			{
				if (j==(int)inf)
					System.out.print("-"+  space1);
				else		
					System.out.print(graph.getNodeById(j).getName()+ space1);
			}
			System.out.println();
		}
	}
}
