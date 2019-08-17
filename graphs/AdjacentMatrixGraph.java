// The graph is undirected
class GraphMatrix {
	int vertex;
	int matrix[][];

	GraphMatrix(int vertex) {
		this.vertex = vertex;
		this.matrix = new int[vertex][vertex];
	}

	public void addEdge(int start, int end) {
		// add the edge
		matrix[start][end] = 1;

		// fill the symmetric edge box for undirected graph
		matrix[end][start] = 1;
	}
}

public class AdjacentMatrixGraph {
	public static void printGraph(GraphMatrix graphMatrix) {
		for(int i = 0; i < graphMatrix.vertex; i++) {
			System.out.print("Vertex " + i + " is connected to: ");
			for(int j = 0; j < graphMatrix.matrix[i].length; j++) {
				if (graphMatrix.matrix[i][j] == 1) System.out.print(j + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// graph with 5 vertexes
		GraphMatrix graphMatrix = new GraphMatrix(5);

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
        graphMatrix.addEdge(0, 1);
        graphMatrix.addEdge(0, 2);
        graphMatrix.addEdge(1, 2);
        graphMatrix.addEdge(1, 3);
        graphMatrix.addEdge(1, 4);
        graphMatrix.addEdge(2, 3);
        graphMatrix.addEdge(3, 4);

        printGraph(graphMatrix);
	}
}
