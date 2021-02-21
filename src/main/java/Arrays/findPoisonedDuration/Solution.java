package Arrays.findPoisonedDuration;

import java.util.*;
import static org.junit.Assert.*;

public class Solution {

    public int findPoisonedDuration(int[] timeSeries, int duration){
        if (duration == 0) return 0;

        Queue<Integer> poisonedState = new LinkedList<>();
        Map<Integer, Integer> durations = new HashMap<>();
        List<Integer> times = new ArrayList<>();

        for (int i : timeSeries){
            times.add(i);
        }

        // int[] timeSeries = {1,2};
        int totalPoisonedTime = 0;
        for (int i = 0; i < times.size(); i++){
            while (!poisonedState.isEmpty()){
                int state = poisonedState.peek();
                if (times.get(i) - state >= duration){
                    // CASE: state is no longer valid so remove
                    poisonedState.poll();
                    // update poisoned time
                    totalPoisonedTime += durations.getOrDefault(state, 0);
                    // update duration of removed state
                    durations.put(state, 0);
                }
                else {
                    // CASE: state is still valid so update duration and continue
                    durations.put(state, durations.get(state) - (times.get(i) - state));
                    //totalPoisonedTime += durations.getOrDefault(state, 0);
                    break;
                }
            }

            poisonedState.add(times.get(i));
            durations.put(times.get(i), duration);
        }

        // do one last check for remaining states
        while (!poisonedState.isEmpty()){
            int state = poisonedState.poll();
            totalPoisonedTime += durations.getOrDefault(state, 0);
        }

        return totalPoisonedTime;
    }

    public static void main(String[] args){
        simpleTest1();
        simpleTest2();
        simpleTest3();
        simpleTest5();
        simpleTest6();
    }

    public static void simpleTest1(){
        Solution s = new Solution();
        int[] timeSeries = {1,4};
        assertEquals(4, s.findPoisonedDuration(timeSeries, 2));
    }

    public static void simpleTest2(){
        Solution s = new Solution();
        int[] timeSeries = {1,2};
        assertEquals(3, s.findPoisonedDuration(timeSeries, 2));
    }

    public static void simpleTest3(){
        Solution s = new Solution();
        int[] timeSeries = { };
        assertEquals(0, s.findPoisonedDuration(timeSeries, 2));
    }

    public static void simpleTest4(){
        Solution s = new Solution();
        int[] timeSeries = { 1,2,3,4,5};
        assertEquals(6, s.findPoisonedDuration(timeSeries, 2));
    }

    public static void simpleTest5(){
        Solution s = new Solution();
        int[] timeSeries = { 1,2,3,4,5};
        assertEquals(0, s.findPoisonedDuration(timeSeries, 0));
    }

    public static void simpleTest6(){
        Solution s = new Solution();
        int[] timeSeries = { 1,4,7 };
        assertEquals(9, s.findPoisonedDuration(timeSeries, 3));
    }
}