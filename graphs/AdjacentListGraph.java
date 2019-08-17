// The graph is undirected
import java.util.LinkedList;
import java.util.Queue;

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
	static Queue<Integer> helperQueue = new LinkedList<>();

	public static void bfsTraversal(GraphList graphList) {
		int[] visitedVertices = new int[graphList.vertex];

		// if there is no starting vertex then return
		if (graphList.list[0].size() == 0) return;
		// mark this vertex visited because it's added in the queue
		visitedVertices[0] = 1;

		helperQueue.add(0);

		while (!helperQueue.isEmpty()) {
			int currentVertex = helperQueue.remove();

			System.out.println(currentVertex);

			for(int i = 0; i < graphList.list[currentVertex].size(); i++) {
				int adjacentVertex = graphList.list[currentVertex].get(i);
				if (visitedVertices[adjacentVertex] == 0) {
					// mark the vertex as visited because it's added in the queue
					visitedVertices[adjacentVertex] = 1;
					helperQueue.add(adjacentVertex);
				}
			}
		}
	}

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
        bfsTraversal(graphList);
	}
}

