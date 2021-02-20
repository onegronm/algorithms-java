package dfs;

import static org.junit.Assert.*;

public class findMinStep {

    public int findMinStep(String _board, String _hand) {
        Board board = new Board(_board);
        Hand hand = new Hand(_hand);

        return board.findMinStep(hand, 0);
    }

    /* ANSWER */
    public int findMinStep2(String _board, String _hand) {
        return -1;
    }

    public static void main(String[] args){
        test01();
        test0();
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
        test8();
        test9();
        test10();
        test11();
        test12();
        test13();
        test14();
    }

    public static void test01(){
        String board = "B", hand = "BB";
        findMinStep main = new findMinStep();
        int result = main.findMinStep(board, hand);
        System.out.println(result);
        assertEquals(2, result);
    }

    public static void test0(){
        String board = "BWW", hand = "WBB";
        findMinStep main = new findMinStep();
        int result = main.findMinStep(board, hand);
        System.out.println(result);
        assertEquals(3, result);
    }

    public static void test1(){
        String board = "WRRBBW", hand = "RB";
        findMinStep main = new findMinStep();
        int result = main.findMinStep(board, hand);
        System.out.println(result);
        assertEquals(-1, result);
    }

    public static void test2(){
        String board = "WWRRBBWW", hand = "WRBRW";
        findMinStep main = new findMinStep();
        int result = main.findMinStep(board, hand);
        System.out.println(result);
        assertEquals(2, result);
    }

    public static void test3(){
        String _board = "BBB", _hand = "";
        Board board = new Board(_board);
        Hand hand = new Hand(_hand);
        findMinStep main = new findMinStep();
        board.removeMatchingColors(board.ballsOnBoard);
        assertEquals("", board.toString());
    }

    public static void test4(){
        String _board = "BBBYYYWWW", _hand = "";
        Board board = new Board(_board);
        Hand hand = new Hand(_hand);
        findMinStep main = new findMinStep();
        board.removeMatchingColors(board.ballsOnBoard);
        assertEquals("", board.toString());
    }

    public static void test5(){
        String _board = "BBYYYWW", _hand = "";
        Board board = new Board(_board);
        Hand hand = new Hand(_hand);
        findMinStep main = new findMinStep();
        board.removeMatchingColors(board.ballsOnBoard);
        assertEquals("BBWW", board.toString());
    }

    public static void test6(){
        String _board = "BBYYWWW", _hand = "";
        Board board = new Board(_board);
        Hand hand = new Hand(_hand);
        findMinStep main = new findMinStep();
        board.removeMatchingColors(board.ballsOnBoard);
        assertEquals("BBYY", board.toString());
    }

    public static void test7(){
        String _board = "BBYYWWW", _hand = "";
        Board board = new Board(_board);
        Hand hand = new Hand(_hand);
        findMinStep main = new findMinStep();
        assertEquals(0, board.pickNext());
    }

    public static void test8(){
        String _board = "BBYYW", _hand = "";
        Board board = new Board(_board);
        Hand hand = new Hand(_hand);
        findMinStep main = new findMinStep();
        assertEquals(4, board.pickNext());
    }

    public static void test9(){
        String _board = "BBYW", _hand = "";
        Board board = new Board(_board);
        Hand hand = new Hand(_hand);
        findMinStep main = new findMinStep();
        assertEquals(3, board.pickNext());
    }

    public static void test10(){
        String _board = "BBYWW", _hand = "";
        Board board = new Board(_board);
        Hand hand = new Hand(_hand);
        findMinStep main = new findMinStep();
        assertEquals(2, board.pickNext());
    }

    public static void test11(){
        String _board = "BYYWW", _hand = "";
        Board board = new Board(_board);
        Hand hand = new Hand(_hand);
        findMinStep main = new findMinStep();
        assertEquals(0, board.pickNext());
    }

    public static void test12(){
        String board = "WWW", hand = "";
        findMinStep main = new findMinStep();
        assertEquals(0, main.findMinStep(board, hand));
    }

    public static void test13(){
        String board = "WWW", hand = "B";
        findMinStep main = new findMinStep();
        assertEquals(0, main.findMinStep(board, hand));
    }

    public static void test14(){
        String board = "BWWW", hand = "B";
        findMinStep main = new findMinStep();
        assertEquals(-1, main.findMinStep(board, hand));
    }
}