package Logic;
import Structure.Board;
import Structure.NextState;
import Structure.Node;
import Structure.Position;

import java.util.*;

public class AStar {
    static PriorityQueue<Node> nodeQueue = new PriorityQueue<>(Comparator.comparingDouble(AStar::getTotalCost));
    static HashSet<Node> visited = new HashSet<>();
    public static Node solveAStar(Node node) {
        long startTime = System.nanoTime();
        nodeQueue.add(node);
        int exploredNodes = 0;

        while (!nodeQueue.isEmpty()) {
            Node currentNode = nodeQueue.poll();
            if (visited.contains(currentNode)) {
                continue;
            }

            currentNode.getState().getUserPlayer().board.printBoard();
            visited.add(currentNode);
            exploredNodes++;

            double heuristicValue = getHeuristic(currentNode);
            System.out.println("Heuristic Value: "+ heuristicValue);
            System.out.println("Solution Depth: " + currentNode.getDepth());
            System.out.println("Cost of Solution: " + currentNode.getCost());
            if (currentNode.getState().getUserPlayer().isWin()) {
                long endTime = System.nanoTime();
                double executionTime = (endTime - startTime) / 1e6;
                System.out.println("Congratulations! You won the game.");
                printSolutionPath(currentNode);
                System.out.println("Execution Time: " + executionTime + " milliseconds");
                System.out.println("Explored Nodes: " + exploredNodes);
                System.out.println("Solution Depth: " + currentNode.getDepth());
                System.out.println("Cost of Solution: " + currentNode.getCost());
                System.out.println("Heuristic Value: "+heuristicValue);

                return currentNode;
            }

            for (Node child : NextState.nextState(currentNode)) {
                if (!visited.contains(child) && !nodeQueue.contains(child)) {
                    nodeQueue.add(child);
                }
            }
        }
        return null;
    }
    private static double getTotalCost(Node node) {
        return node.getCost() + getHeuristic(node);
    }
    private static double getHeuristic(Node node) {
    UserPlayer userPlayer = node.getState().getUserPlayer();
    Board board = new Board(node.getState().getUserPlayer().board.getBoard(), node.getState().getUserPlayer().board.getFinalBoard());
    ArrayList<Position> eggs = userPlayer.getEggs();
    ArrayList<Position> targets = userPlayer.getTargets();

    double totalDistance = 0;

    for (Position egg : eggs) {
        if (isInUndesirableLocation(egg, targets , board)) {
            continue;
        }

        double minDistance = Double.MAX_VALUE;

        for (Position target : targets) {
            double distance = getManhattanDistance(egg, target);
            minDistance = Math.min(minDistance, distance);
        }

        totalDistance += minDistance;
    }

    return totalDistance;
}
    private static boolean isInUndesirableLocation(Position egg, ArrayList<Position> targets , Board board) {
        return isCornerPosition(egg , board) && targets.contains(egg);
    }
    private static boolean isCornerPosition(Position position , Board board) {
        return position.getX() == 0 || position.getX() == board.getBoardHeight()-1 || position.getY() == 0 || position.getY() == board.getBoardWidth()-1;
    }
    private static double getManhattanDistance(Position p1, Position p2) {
        return Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY());
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
