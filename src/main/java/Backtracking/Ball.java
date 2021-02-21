package Backtracking;

public class Ball {
    char color;

    public Ball(char c){
        color = c;
    }

    @Override
    public boolean equals(Object obj) {
        Ball other = (Ball)obj;
        return this.color == other.color;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + color;
        return result;
    }
}
