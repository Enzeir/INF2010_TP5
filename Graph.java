import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Graph {

	private List<Node> nodes = new ArrayList<Node>(); // Noeuds
	private List<Edge> edges = new ArrayList<Edge>(); // Les arcs
	static final double inf = 99999;
	public Graph() {
		
	}

	public void readFromFile(String filePath,String separtor) throws IOException {
		//compl�ter
		File file = new File(filePath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String[] lineSection = br.readLine().split(separtor);
		
		for(int i = 0; i < lineSection.length; i++)
		{
			nodes.add(new Node(i, lineSection[i]));
		}
		
		for(int i = 0; i < lineSection.length; i++)
		{
			lineSection = br.readLine().split(separtor);
			for(int j = 0; j < lineSection.length; j++)
			{
				if(lineSection[j].contains("inf")) 
				{
					edges.add(new Edge(getNodeById(i), getNodeById(j), inf));
				}
				else
				{
					edges.add(new Edge(getNodeById(i), getNodeById(j), Double.parseDouble(lineSection[j])));
				}
			}
		}
		
		br.close();
	}
	
	public List<Edge> getOutEdges(Node source) {
		List<Edge> outEdges = new ArrayList<>(); 
		// compl�ter
		for(int i = 0; i< edges.size(); i++)
		{
			if(edges.get(i).getSource().getName() == source.getName() 
					&& edges.get(i).getDestination().getName() != source.getName()
					&& edges.get(i).getDistance() != inf)
			{
				outEdges.add(edges.get(i));
			}
		}
		return outEdges;	
	}
	
	public List<Edge> getInEdges(Node dest) {
		List<Edge> inEdges = new ArrayList<>(); 
		// compl�ter
		for(int i = 0; i < edges.size(); i++)
		{
			if(edges.get(i).getDestination().getName() == dest.getName() 
					&& edges.get(i).getSource().getName() != dest.getName()
					&& edges.get(i).getDistance() != inf);
			{
				inEdges.add(edges.get(i));
			}
		}
		return inEdges;		
	}
	
	// Accesseurs 
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public List<Edge> getEdges() {
		return edges;
	}
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	public Node getNodeByName(String name){
		for (Node node : nodes) {
			if(node.getName().equals(name)){
				return node;
			}
		}
		return null;
	}
	
	public Node getNodeById(int id){
		for (Node node : nodes) {
			if(node.getId()==id){
				return node;
			}

		}
		return null;
	}
}
