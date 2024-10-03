package Structure;

public class Position {
    private int x;
    private int y;
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Position(int x,int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Position o = (Position) obj;
        return o.getX() == this.getX() && o.getY() == this.getY();
    }
    @Override
    public String toString(){
        return this.x + "," + this.y + "\n";
    }
}
