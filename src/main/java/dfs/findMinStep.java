package dfs;

import static org.junit.Assert.*;

public class findMinStep {

    public int findMinStep(String _board, String _hand) {
        int result = -1;

        Board board = new Board(_board);
        Hand hand = new Hand(_hand);

        return board.findMinStep(hand, 0);
    }

    public static void main(String[] args){
        //test0();
        //test1();
        //test2();
        test3();
        test4();
        test5();
        test6();
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

    public static void test3(){
        String _board = "BBB", _hand = "";
        Board board = new Board(_board);
        Hand hand = new Hand(_hand);
        findMinStep main = new findMinStep();
        board.removeMatchingColors();
        assertEquals("", board.toString());
    }

    public static void test4(){
        String _board = "BBBYYYWWW", _hand = "";
        Board board = new Board(_board);
        Hand hand = new Hand(_hand);
        findMinStep main = new findMinStep();
        board.removeMatchingColors();
        assertEquals("", board.toString());
    }

    public static void test5(){
        String _board = "BBYYYWW", _hand = "";
        Board board = new Board(_board);
        Hand hand = new Hand(_hand);
        findMinStep main = new findMinStep();
        board.removeMatchingColors();
        assertEquals("BBWW", board.toString());
    }

    public static void test6(){
        String _board = "BBYYWWW", _hand = "";
        Board board = new Board(_board);
        Hand hand = new Hand(_hand);
        findMinStep main = new findMinStep();
        board.removeMatchingColors();
        assertEquals("BBYY", board.toString());
    }
}