package Structure;

import Logic.UserPlayer;
import java.util.Objects;

public class State {
    private UserPlayer userPlayer;
    private Board board ;
    public State(Board board , UserPlayer userPlayer ){
        this.board = new Board(userPlayer.board.getBoard(), userPlayer.board.getFinalBoard());
        this.userPlayer = userPlayer;
        //this.heuristic = getHeuristic();
    }
    public State(UserPlayer userPlayer) {
        this.userPlayer = userPlayer;
    }
    public UserPlayer getUserPlayer(){
        return userPlayer;
    }
    public State copy(){
        return new State(this.board , this.userPlayer.copy());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return (this.userPlayer.equals(state.userPlayer)) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userPlayer);
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
