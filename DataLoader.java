import java.util.ArrayList;
import java.util.List;

//Each node represents a vertex with vertexId, features, label and a list of neighbors
class Vertex{
    public int vertexId;
    public double feat;
    public int label;
    public List<Vertex> neighbours;

    public Vertex(int vertexId, double feat, int label, List<Vertex> neighbours) {
        this.vertexId = vertexId;
        this.feat = feat;
        this.label = label;
        this.neighbours = neighbours;
    }  
}

//DataLoader takes a list of primary nodes as input and traverses up to the given number of hops and a fix number of sampled neighbors
class DataLoader{

    public static List<List<Vertex>> batchLoader(List<Vertex> vertices, int hop, int numSampledNeighbours){
        List<List<Vertex>> batches = new ArrayList<>();
        batches.add(vertices);
        
        for(int i=1; i<=hop; i++){
            List<Vertex> currentBatch = new ArrayList<>();
            List<Vertex> previousBatch = batches.get(i-1);

            for(Vertex v : previousBatch){
                for(int j=0; j<numSampledNeighbours; j++){
                    currentBatch.add(v.neighbours.get(j));
                }
            }
            batches.add(currentBatch);
        }

        return batches;
    }

    public static void main(String[] args) {       

        //Graph of Vertices
        Vertex v1 = new Vertex(1, 5, 1, new ArrayList<>());
        Vertex v3 = new Vertex(3, 5, 1, new ArrayList<>());
        Vertex v4 = new Vertex(4, 5, 1, new ArrayList<>());
        Vertex v11 = new Vertex(11, 5, 1, new ArrayList<>()); // neighbour of v1
        Vertex v12 = new Vertex(12, 5, 1, new ArrayList<>()); // neighbour of v1
        Vertex v31 = new Vertex(31, 5, 1, new ArrayList<>()); // neighbour of v3
        Vertex v32 = new Vertex(32, 5, 1, new ArrayList<>()); // neighbour of v3
        Vertex v41 = new Vertex(41, 5, 1, new ArrayList<>()); // neighbour of v4
        Vertex v42 = new Vertex(42, 5, 1, new ArrayList<>()); // neighbour of v4
        Vertex v111 = new Vertex(111, 5, 1, new ArrayList<>()); // neighbour of v11
        Vertex v112 = new Vertex(112, 5, 1, new ArrayList<>()); // neighbour of v11
        Vertex v121 = new Vertex(121, 5, 1, new ArrayList<>()); // neighbour of v12
        Vertex v122 = new Vertex(122, 5, 1, new ArrayList<>()); // neighbour of v12
        Vertex v311 = new Vertex(311, 5, 1, new ArrayList<>()); // neighbour of v31
        Vertex v312 = new Vertex(312, 5, 1, new ArrayList<>()); // neighbour of v31
        Vertex v321 = new Vertex(321, 5, 1, new ArrayList<>()); // neighbour of v32
        Vertex v322 = new Vertex(322, 5, 1, new ArrayList<>()); // neighbour of v32
        Vertex v411 = new Vertex(411, 5, 1, new ArrayList<>()); // neighbour of v41
        Vertex v412 = new Vertex(412, 5, 1, new ArrayList<>()); // neighbour of v41
        Vertex v421 = new Vertex(421, 5, 1, new ArrayList<>()); // neighbour of v42
        Vertex v422 = new Vertex(422, 5, 1, new ArrayList<>()); // neighbour of v42

        v1.neighbours.add(v11);
        v1.neighbours.add(v12);
        v3.neighbours.add(v31);
        v3.neighbours.add(v32);
        v4.neighbours.add(v41);
        v4.neighbours.add(v42);
        v11.neighbours.add(v111);
        v11.neighbours.add(v112);
        v12.neighbours.add(v121);
        v12.neighbours.add(v122);
        v31.neighbours.add(v311);
        v31.neighbours.add(v312);
        v32.neighbours.add(v321);
        v32.neighbours.add(v322);
        v41.neighbours.add(v411);
        v41.neighbours.add(v412);
        v42.neighbours.add(v421);
        v42.neighbours.add(v422);


        //List of Primary Nodes: v1, v3, v4
        List<Vertex> nodes = new ArrayList<>();
        nodes.add(v1);
        nodes.add(v3);
        nodes.add(v4);

        // Passing the List of Primary Nodes to traverse each of their neighborhoods
        // hop represents the levels of neighborhood
        // numSampledNeighbours represents the number of neighboring nodes to be considered for each node at different levels of hops
        List<List<Vertex>> batches = batchLoader(nodes, 2, 2);
        for(int i=0; i<batches.size(); i++){
            System.out.print("[");
            for(int j=0; j<batches.get(i).size(); j++){
                System.out.print(" v" + batches.get(i).get(j).vertexId + " ");
            }
            System.out.print("]");
            System.out.println();
        }       
    }
}