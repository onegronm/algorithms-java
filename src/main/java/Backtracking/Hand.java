package Backtracking;

import java.util.*;

public class Hand {
    List<Ball> balls;
    int size;

    public Hand(String input){
        balls = new ArrayList<Ball>();
        for (char s : input.toCharArray()){
            balls.add(new Ball(s));
            size++;
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int contains(Ball target){
        for (Ball b : balls)
            if (target.color == b.color) {
                return balls.indexOf(b);
            }
        return -1;
    }

    public Ball throwBall(int index){
        Ball b = balls.get(index);
        balls.remove(b);
        size--;
        return b;
    }
}