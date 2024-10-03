
package Structure;

import Logic.UserPlayer;
import java.util.ArrayList;

public class NextState {
    public static ArrayList<Node> nextState(Node node) {
        UserPlayer a = node.getState().getUserPlayer();
        ArrayList<Node> ns = new ArrayList<>();
        int currentRow = a.getBunnyRow();
        int currentColumn = a.getBunnyColumn();

        if (a.isValidMove(currentRow - 1, currentColumn)) {
            State state1 = new State(a.copy());
            state1.getUserPlayer().moveBunny(currentRow - 1, currentColumn);

            Node node1 = new Node(state1.copy(), node, "UP");
            ns.add(node1);
        }
        if (a.isValidMove(currentRow + 1, currentColumn)) {
            State state2 = new State(a.copy());
            state2.getUserPlayer().moveBunny(currentRow + 1, currentColumn);

            Node node2 = new Node(state2.copy(), node, "DOWN");
            ns.add(node2);
        }
        if (a.isValidMove(currentRow, currentColumn + 1)) {
            State state3 = new State(a.copy());
            state3.getUserPlayer().moveBunny(currentRow, currentColumn + 1);

            Node node3 = new Node(state3.copy(), node, "RIGHT");
            ns.add(node3);
        }
        if (a.isValidMove(currentRow, currentColumn - 1)) {
            State state4 = new State(a.copy());
            state4.getUserPlayer().moveBunny(currentRow, currentColumn - 1);

            Node node4 = new Node(state4.copy(), node, "LEFT");
            ns.add(node4);
        }
        return ns;
    }
}
