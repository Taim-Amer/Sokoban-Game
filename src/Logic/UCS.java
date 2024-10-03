package Logic;

import Structure.Node;
import Structure.NextState;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;
public class UCS {
    static PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<>((a, b) -> Double.compare(a.getCost(), b.getCost()));
    static HashSet<Node> visited = new HashSet<>();
    static int exploredNodes = 0;
    static int maxDepth = 0;
    public static Node solveUCS(Node initialNode) {
        long startTime = System.nanoTime();
        nodePriorityQueue.add(initialNode);

        while (!nodePriorityQueue.isEmpty()) {
            Node currentNode = nodePriorityQueue.poll();
            if (visited.contains(currentNode)) {
                continue;
            }

            visited.add(currentNode);
            exploredNodes++;
            System.out.println("Explored Nodes: " + exploredNodes);
            System.out.println("Solution Depth: " + currentNode.getDepth());
            System.out.println("Maximum Search Tree Depth: " + maxDepth);
            System.out.println("Cost of Solution: " + currentNode.getCost());
            currentNode.getState().getUserPlayer().board.printBoard();

            if (currentNode.getState().getUserPlayer().isWin()) {
                long endTime = System.nanoTime();
                double executionTime = (endTime - startTime) / 1e6;
                System.out.println("Congratulations! You won the game.");
                printSolutionPath(currentNode);
                System.out.println("Execution Time: " + executionTime + " milliseconds");
                System.out.println("Explored Nodes: " + exploredNodes);
                System.out.println("Solution Depth: " + currentNode.getDepth());
                System.out.println("Maximum Search Tree Depth: " + maxDepth);
                System.out.println("Cost of Solution: " + currentNode.getCost());
                return currentNode;
            }
            if (currentNode.getDepth() > maxDepth) {
                maxDepth = currentNode.getDepth();
            }
            for (Node child : NextState.nextState(currentNode)) {
                if (!visited.contains(child) && !nodePriorityQueue.contains(child)) {
                    nodePriorityQueue.add(child);
                }
            }
        }
        return null;
    }
    private static void printSolutionPath(Node currentNode) {
        Stack<String> actions = new Stack<>();
        while (currentNode != null) {
            String action = currentNode.getAction();
            if (action != null) {
                actions.push(action);
            }
            currentNode = currentNode.getParent();
        }

        System.out.println("Solution Path:");
        while (!actions.isEmpty()) {
            System.out.println('\t' + actions.pop() + '\t');
        }
    }
}
