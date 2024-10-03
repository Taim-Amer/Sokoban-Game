
package Structure;

import java.util.Objects;

public class Node {
    private State state;
    private Node parent;
    private String action;
    public int cost;
    public int heuristic;
    public int depth;
    public Node(State state, Node parent, String action) {
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.cost = parent != null ? parent.getCost() + 1 : 0;
        this.depth = parent != null ? parent.getDepth() + 1 : 0;
    }
    public State getState() {
        return state;
    }
    public Node getParent() {
        return parent;
    }
    public String getAction() {
        return action;
    }
    public int getDepth() {
        return depth;
    }
    public int getCost() {
        return cost;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return ( this.state.equals(node.getState()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, parent, action, cost, depth);
    }
}
