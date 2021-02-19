package dfs;

import java.util.*;

import static org.junit.Assert.*;

public class findMinStep {

    public int findMinStep(String board, String hand) {
        int result = -1;

        if (hand.length() == 0 || board.length() == 0) {
            return -1;
        }
        Map<Character,Integer> characterCount = new HashMap();
        for (int i = 0; i < board.length(); i++){

        }
        // check if there are any three matching colors next to each other
        // count the occurrence of each character
        // pick the character with less occurrence
        // see if there is a matching ball and throw
        // repeat until no balls are left in hand or on board
        // return count


        return result;
    }

    public static void main(String[] args){
        test0();
        test1();
        test2();
    }

    public static void test0(){
        String board = "BWW", hand = "WBB";
        findMinStep main = new findMinStep();
        assertEquals(2, main.findMinStep(board, hand));
    }

    public static void test1(){
        String board = "WRRBBW", hand = "RB";
        findMinStep main = new findMinStep();
        assertEquals(-1, main.findMinStep(board, hand));
    }

    public static void test2(){
        String board = "WWRRBBWW", hand = "WRBRW";
        findMinStep main = new findMinStep();
        assertEquals(2, main.findMinStep(board, hand));
    }
}