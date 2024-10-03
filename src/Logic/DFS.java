
package Logic;

import Structure.NextState;
import Structure.Node;
import java.util.ArrayList;
import java.util.Stack;

public class DFS {
    static Stack<Node> nodeStack = new Stack<>();
    static ArrayList<Node> visited = new ArrayList<>();
    static int maxDepth = 0;
    static int exploredNodes = 0;
    public static Node solveDfs(Node node) {
        long startTime = System.nanoTime();
        nodeStack.push(node);

        while (!nodeStack.isEmpty()) {
            Node currentNode = nodeStack.pop();
            if (visited.contains(currentNode)) {
                continue;
            }
            visited.add(currentNode);
            exploredNodes++;

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
                if (!visited.contains(child)) {
                    nodeStack.push(child);
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
