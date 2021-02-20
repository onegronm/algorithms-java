package dfs;

/**
 * TOPIC: BACKTRACKING
 * algorithmic-technique for solving problems recursively by trying to build a solution incrementally, one piece at a time, removing those solutions that fail to satisfy the constraints of the problem at any point of time
 */

import java.util.*;

import static org.junit.Assert.*;

public class findMinStep {

    /**
     * My implementation is wrong because it fails to use backtracking
     * to select the best possible path
     * @param _board
     * @param _hand
     * @return
     */
    public int findMinStep2(String _board, String _hand) {
        Board board = new Board(_board);
        Hand hand = new Hand(_hand);

        return board.findMinStep(hand, 0);
    }

    /* ANSWER */
    public int findMinStep(String board, String hand) {
        Map<Character,Integer> handMap = new HashMap<>();
        for (int i = 0; i < board.length(); i++){
            handMap.put(board.charAt(i), handMap.getOrDefault(board.charAt(i),0) + 1);
        }
        int result = helper(board, handMap);
        return result == Integer.MAX_VALUE ?  -1 : result;
    }

    private int helper(String board, Map<Character, Integer> handMap) {
        if (board.length() == 0) return 0;
        else if (handMap.size() == 0) return Integer.MAX_VALUE;
        else {
            int tempResult = Integer.MAX_VALUE;
            for (int i = 0; i < board.length(); i++){
                int j = i;
                char requiredCharacter = board.charAt(i);
                while (j < board.length() && requiredCharacter == board.charAt(j)) j++;
                int length = j - i;
                int ballsNeededToCompleteThree = Math.max(3 - length, 0);

                // Part 2: recursion
                if (ballsNeededToCompleteThree == 0 || handMap.getOrDefault(requiredCharacter, 0) >= ballsNeededToCompleteThree){
                    // remove from hand and create new board
                    handMap.put(requiredCharacter, handMap.getOrDefault(requiredCharacter, 0) - ballsNeededToCompleteThree);
                    String newBoard = board.substring(0, i) + board.substring(j);
                    int temp = helper(newBoard, handMap);

                    if(temp != Integer.MAX_VALUE){
                        tempResult = Math.min(tempResult, temp + ballsNeededToCompleteThree);
                    }

                    // put back in hand
                    handMap.put(requiredCharacter, handMap.getOrDefault(requiredCharacter, 0) + ballsNeededToCompleteThree);
                }
            }
            return tempResult;
        }
    }

    public static void main(String[] args){
        /*
        test01();
        test0();
        test1();

         */
        test2();
        /*
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
        test15();

         */
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
        assertEquals(-1, main.findMinStep(board, hand));
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

    public static void test15(){
        String board = "RRWWRRW", hand = "WWRR";
        findMinStep main = new findMinStep();
        assertEquals(2, main.findMinStep(board, hand));
    }
}