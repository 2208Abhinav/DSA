// The graph is undirected
import java.util.LinkedList;

class GraphList {
	int vertex;
	LinkedList<Integer> list[];

	GraphList(int vertex) {
		this.vertex = vertex;
		this.list = new LinkedList[vertex];

		for(int i = 0; i < vertex; i++) {
			this.list[i] = new LinkedList<Integer>();
		}
	}

	void addEdge(int start, int end) {
		// addFirst takes O(1) time
		list[start].addFirst(end);

		// Add back edge for undirected graph
		list[end].addFirst(start);
	}
}


public class AdjacentListGraph {
	public static void printGraph(GraphList graphList) {
		for(int i = 0; i < graphList.vertex; i++) {
			System.out.print(i + " is connected to: ");
			for(int j = 0; j < graphList.list[i].size(); j++) {
				System.out.print(graphList.list[i].get(j) + " ");
			}
			System.out.println();
		}
	}


	public static void main(String[] args) {
		// graph with 5 vertexes
		GraphList graphList = new GraphList(5);

		// Graph structure:
		/*
            0 --- 1 --- 4
            |    / \    |
            |   /   \   |
            |  /     \  |
            | /       \ |
             2 ------- 3
		*/

        // add 7 edges
        graphList.addEdge(0, 1);
        graphList.addEdge(0, 2);
        graphList.addEdge(1, 2);
        graphList.addEdge(1, 3);
        graphList.addEdge(1, 4);
        graphList.addEdge(2, 3);
        graphList.addEdge(3, 4);

        printGraph(graphList);
	}
}

