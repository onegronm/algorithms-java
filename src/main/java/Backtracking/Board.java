package Backtracking;

import java.util.*;

public class Board {
    List<Ball> ballsOnBoard;

    public Board(String value){
        ballsOnBoard = new ArrayList<Ball>();
        for (char c : value.toCharArray()){
            ballsOnBoard.add(new Ball(c));
        }
    }

    public void removeMatchingColors(List<Ball> balls){
        /* recursive method */
        int matchCount = 0;
        for (int i = 0, j = 0; j < balls.size(); ++j){
            if (balls.get(j).equals(balls.get(i))){
                matchCount++;
                continue;
            }
            else if (j - i >= 3) {
                List<Ball> temp = new ArrayList<Ball>();
                if (i > 0)
                    temp.addAll(balls.subList(0, i));
                temp.addAll(balls.subList(j, balls.size()));

                balls.clear();
                balls.addAll(temp);
                matchCount = 0;

                if (!balls.isEmpty())
                    removeMatchingColors(balls);
            }
            else {
                i = j;
                matchCount = 1;
            }
        }

        if (matchCount >= 3) {
            int start = balls.size() - matchCount;
            balls.removeAll(balls.subList(start, balls.size()));
        }

        //return balls;

        // check if there are any three matching colors next to each other and remove
        // count instances of 3+ consecutive characters
        /*
        int matchCount = 1;
        int total = 0;
        for (int i = 1; i < this.ballsOnBoard.size(); i++){
            if (this.ballsOnBoard.get(i).equals(this.ballsOnBoard.get(i - 1))) {
                matchCount++;
            }
            else if (matchCount >= 3) {
                total++;
                matchCount = 1;
            }
        }

        matchCount = 1;
        int i = 1;
        while (i < this.ballsOnBoard.size()) {
            if (this.ballsOnBoard.get(i).equals(this.ballsOnBoard.get(i - 1))) {
                matchCount++;
            } else if (matchCount >= 3) {
                this.ballsOnBoard.subList(i-matchCount, i).clear();
                //i = i-matchCount+1;
                i = 1;
                matchCount = 1;
                continue;
            } else {
                matchCount = 1;
            }
            i++;
        }

        if (matchCount >= 3) {
            this.ballsOnBoard.removeAll(this.ballsOnBoard.subList(ballsOnBoard.size()-matchCount+1, ballsOnBoard.size()-1));
        }

         */
    }

    public int pickNext() {
        Map<Ball,Integer> characterCount = new HashMap<>();

        // count all characters
        for (Ball b : this.ballsOnBoard){
            if (characterCount.containsKey(b)) {
                int count = characterCount.get(b) + 1;
                characterCount.put(b, count);
            }
            else {
                characterCount.put(b, 1);
            }
        }

        // return the character with smallest count
        Map.Entry<Ball, Integer> min = null;
        for (Map.Entry<Ball, Integer> entry : characterCount.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }

        // return the first occurrence of this character
        if (ballsOnBoard.contains(min.getKey())){
            return ballsOnBoard.indexOf(min.getKey());
        }

        /*
        for (Ball b : ballsOnBoard){
            if (b.color == min.getKey()) return ballsOnBoard.indexOf(b);
        }*/

        return -1;
    }

    public boolean isEmpty(){
        return this.ballsOnBoard.size() == 0;
    }

    public int findMinStep(Hand hand, int result){
        while (!this.isEmpty()){
            this.removeMatchingColors(ballsOnBoard);

            if (this.isEmpty()) break;

            int targetOnBoard = this.pickNext();
            int index = hand.contains(this.ballsOnBoard.get(targetOnBoard));
            if (index > -1){
                ballsOnBoard.add(targetOnBoard, hand.throwBall(index));

                result++;
                result = this.findMinStep(hand, result);
            }
            else if (!hand.isEmpty()){
                result++;
                ballsOnBoard.add(targetOnBoard, hand.throwBall(hand.balls.size()-1));
                result = this.findMinStep(hand, result);
            }
            else break;
        }

        if (!ballsOnBoard.isEmpty()) result = -1;

        return result;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Ball b : ballsOnBoard){
            str.append(b.color);
        }
        return str.toString();
    }
}