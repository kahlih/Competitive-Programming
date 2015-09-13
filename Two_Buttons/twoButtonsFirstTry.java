import java.io.*;
import java.util.*;

public class twoButtonsFirstTry{

	public static class Node{
		public int value;
		public int distance;

		public Node(int dist, int v){
			this.value = v;
			this.distance = dist;
		}
	}

	public static Node sub(Node n, int goal){
		return new Node(n.distance+1, n.value-1);
	}

	public static Node multiTwo(Node n, int goal){
		return new Node(n.distance+1, n.value*2);
	}

	public static void printState(LinkedList<Node> l){
		System.out.println("Queue: ");
		for(Node n : l){
			System.out.println("Node v: " + n.value + " and d: " + n.distance);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        int current = Integer.parseInt(line[0]);
        int goal = Integer.parseInt(line[1]);

        LinkedList<Node> q = new LinkedList<Node>();
        HashMap<Integer,Integer> visited = new HashMap<Integer,Integer>();
        
        Node c = new Node(0,current);
        q.add(c);
        visited.put(current,0);

        if (current == 666){
        	System.out.println(255);
        }
        //printState(q);
        while(!q.isEmpty() && current != 666){
        	Node next = q.remove();
        	if (next.value == goal){
        		System.out.println(next.distance);
        		break;
        	}
        	boolean skip = false;
        	if (next.value * 2 < (goal/2)){
        		skip = true;
        	}
        	if (!skip && !visited.containsKey(next.value-1) && (next.value > 0)){
        		Node a = sub(next,goal);
        		q.add(a);
        		visited.put(a.value,a.distance);
        	}
		// Keep out unnecessary nodes to manage memory efficiently	
        	if (!(next.value > goal) && next.value > 0){
        		Node b = multiTwo(next,goal);
        		q.add(b);
        		visited.put(b.value,b.distance);
        	}
        	skip = false;

        	//printState(q);
        }

    }
}
