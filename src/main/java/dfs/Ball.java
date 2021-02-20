package dfs;

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
}
