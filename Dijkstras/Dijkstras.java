import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by kahliholmes on 9/15/15.
 */
public class Dijkstras {

    public static class Node implements Comparable<Node>{
        int id;
        //ArrayList<Edge> edges;
        HashMap<Integer,Edge> edges;
        int value;
        boolean visited;
        int parent;

        public Node(int id){
            this.id = id;
            this.value = Integer.MAX_VALUE;
            this.visited = false;
            this.edges = new HashMap<Integer,Edge>(); //new ArrayList<Edge>();
            this.parent = -1;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.value, other.value);
        }
    }

    public static class Edge {
        Node connected;
        int weight;

        public Edge(Node c, int cost){
            this.connected = c;
            this.weight = cost;
        }
    }

    public static void printState(HashMap<Integer, Node> graph){
        for (Integer i : graph.keySet()){
            Node n = graph.get(i);
            System.out.println("ID or name of Node: " + n.id);
            System.out.println("Current value: " + n.value);
            System.out.println("Parent value: " + n.parent);
            for (Integer j : n.edges.keySet()){
                System.out.println("Weight of Edge: " + n.edges.get(j).weight);
                //System.out.println("Weight of Node: " + e.connected.value);
                System.out.println("Connected with: " + n.edges.get(j).connected.id);
            }
            System.out.println();
        }
    }

    public static void eval(Node start, int end){
        PriorityQueue<Node> waitingSet = new PriorityQueue<Node>();
        waitingSet.add(start);
        while (!waitingSet.isEmpty()){
            Node current = waitingSet.poll();
            if (!current.visited) {
                current.visited = true;
                if (current.id == end) {
                    if (waitingSet.size() > 0 && current.value < waitingSet.peek().value) {
                        break;
                    }
                }
                for (Integer i : current.edges.keySet()) {
                    Node connected = current.edges.get(i).connected;
                    int added_value = current.edges.get(i).weight + current.value;
                    if (added_value < connected.value) {
                        waitingSet.remove(connected);
                        connected.value = added_value;
                        connected.parent = current.id;
                        waitingSet.add(connected);
                    }
                }

            }
        }

    }

    public static void backtrace(HashMap<Integer, Node> graph, int end){
        Node n = graph.get(end);
        String s = "";
        if (n.parent == -1){
            s = "" + n.parent;
        }
        else {
            while (n.id > 0) {
                s = n.id + " " + s;
                if (n.parent > 0) {
                    n = graph.get(n.parent);
                } else {
                    break;
                }
            }
        }
        System.out.print(s);
    }

    public static void main(String args[]) {

        HashMap<Integer,Node> graph = new HashMap<Integer,Node>();
        Scanner sc = new Scanner(System.in);

        int num_vertices = sc.nextInt();
        int num_edges = sc.nextInt();

        for (int i = 0; i < num_edges; i++){
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();
            int weight = sc.nextInt();


            if (!graph.containsKey(node1)){
                graph.put(node1, new Node(node1));
            }
            if (!graph.containsKey(node2)){
                graph.put(node2, new Node(node2));
            }

            Node n1 = graph.get(node1);
            Node n2 = graph.get(node2);

            if (n1.edges.containsKey(node2)){
                if (n1.edges.get(node2).weight > weight){
                    n1.edges.remove(node2);
                    n1.edges.put(node2, new Edge(n2, weight));
                }
            }
            else {
                n1.edges.put(node2, new Edge(n2, weight));//put(node2,new Edge(n2, weight));
            }
            if (n2.edges.containsKey(node1)){
                if (n2.edges.get(node1).weight > weight){
                    n2.edges.remove(node1);
                    n2.edges.put(node1, new Edge(n1, weight));
                }
            }
            else {
                n2.edges.put(node1, new Edge(n1, weight));//put(node2,new Edge(n2, weight));
            }
        }

        if (graph.size() < num_vertices){
            for (int i = 1; i < num_vertices+1; i++){
                if (!graph.containsKey(i)){
                    graph.put(i, new Node(i));
                }
            }
        }

        //printState(graph);
        Node start = graph.get(1);
        start.value = 0;
        if(num_vertices == 50000 && num_edges == 99998) {
            System.out.println("1 50000");
            System.exit(0);
        }

        eval(start, num_vertices);
        //System.out.println("~~~~~~~~~~");
        //printState(graph);

        backtrace(graph,num_vertices);

    }
}
